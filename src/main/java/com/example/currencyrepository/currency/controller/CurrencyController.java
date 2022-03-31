package com.example.currencyrepository.currency.controller;

import com.example.currencyrepository.currency.model.Currency;
import com.example.currencyrepository.currency.model.CurrencyName;
import com.example.currencyrepository.currency.service.CurrencyService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyService.createCurrency(currency);
    }

    @GetMapping("/minprice/{currency}")
    public Currency getCurrencyWithMinPrice(@PathVariable CurrencyName currency) {
        return currencyService.getCurrencyWithMinPrice(currency);
    }

    @GetMapping("/maxprice/{currency}")
    public Currency getCurrencyWithMaxPrice(@PathVariable CurrencyName currency) {
        return currencyService.getCurrencyWithMaxPrice(currency);
    }

    @GetMapping()
    public Page<Currency> getCryptocurrencies(@RequestParam CurrencyName name,
                                              @RequestParam int page,
                                              @RequestParam int size) {

        return currencyService.getCryptocurrencies(name, page, size);
    }

    @GetMapping("/csv")
    public ResponseEntity<InputStreamResource> getReport() throws IOException {
        InputStream inputStream = currencyService.generateReport();
        InputStreamResource body = new InputStreamResource(inputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(body);
    }
}
