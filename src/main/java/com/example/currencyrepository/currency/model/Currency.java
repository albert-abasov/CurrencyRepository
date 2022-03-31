package com.example.currencyrepository.currency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "currencies")
public class Currency {
    @Id
    private String id;
    private CurrencyName currency;
    private BigDecimal price;
    private LocalDateTime createdAt;

    public Currency(CurrencyName currency, BigDecimal price) {
        this.currency = currency;
        this.price = price;
    }
}
