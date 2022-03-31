package com.example.currencyrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CurrencyRepositoryApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CurrencyRepositoryApplication.class, args);
    }
}