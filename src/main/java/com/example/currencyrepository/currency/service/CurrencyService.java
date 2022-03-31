package com.example.currencyrepository.currency.service;

import com.example.currencyrepository.currency.exception.CurrencyNotFoundException;
import com.example.currencyrepository.currency.model.Currency;
import com.example.currencyrepository.currency.data.CurrencyRepository;
import com.example.currencyrepository.currency.model.CurrencyName;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CurrencyService {
    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency createCurrency(Currency currency) {
        currencyRepository.save(currency);
        return currency;
    }

    public Currency getCurrencyWithMinPrice(CurrencyName currency) {
        return currencyRepository.findFirstByCurrencyOrderByPriceAsc(currency)
                .orElseThrow(() -> new CurrencyNotFoundException());
    }

    public Currency getCurrencyWithMaxPrice(CurrencyName currency) {
        return currencyRepository.findFirstByCurrencyOrderByPriceDesc(currency)
                .orElseThrow(() -> new CurrencyNotFoundException());
    }

    public Page<Currency> getCryptocurrencies(CurrencyName name, int page, int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "price"));
        return currencyRepository.findByCurrency(name, paging);
    }

    public InputStream generateReport() throws IOException {
        String fileName = "my.csv";

        try (Writer wr = new FileWriter(fileName)) {
            CSVPrinter printer = new CSVPrinter(
                    wr, CSVFormat.MONGODB_CSV.withHeader("Currency", "Min price", "Max price")
            );

            CurrencyName[] values = CurrencyName.values();

            for (CurrencyName curr : values) {
                BigDecimal currMin = getCurrencyWithMinPrice(curr).getPrice();
                BigDecimal currMax = getCurrencyWithMaxPrice(curr).getPrice();

                printer.print(curr.name());
                printer.print(currMin);
                printer.print(currMax);
                printer.println();
            }
        }

        return new FileInputStream(fileName);
    }
}
