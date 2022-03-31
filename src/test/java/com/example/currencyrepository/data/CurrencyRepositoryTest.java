package com.example.currencyrepository.data;

import com.example.currencyrepository.currency.data.CurrencyRepository;
import com.example.currencyrepository.currency.model.Currency;
import com.example.currencyrepository.currency.model.CurrencyName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
public class CurrencyRepositoryTest {
    @Autowired
    private CurrencyRepository currencyRepository;
    private Logger logger = LoggerFactory.getLogger(CurrencyRepository.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    public void createCurrency() throws ParseException {
        Currency currency = new Currency(
                "id", CurrencyName.BTC, BigDecimal.valueOf(0.111),
                LocalDateTime.parse("2022-03-30T06:45:00", formatter)
        );

        currencyRepository.save(currency);
        Currency fetched = currencyRepository.findById("id").get();

        logger.info("Found saved currency: {}", fetched);
        Assertions.assertEquals(currency, fetched);
    }
}
