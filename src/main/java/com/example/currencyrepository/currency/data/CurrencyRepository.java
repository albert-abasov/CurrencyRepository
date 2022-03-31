package com.example.currencyrepository.currency.data;

import com.example.currencyrepository.currency.model.Currency;
import com.example.currencyrepository.currency.model.CurrencyName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String> {

    Optional<Currency> findFirstByCurrencyOrderByPriceAsc(CurrencyName currency);

    Optional<Currency> findFirstByCurrencyOrderByPriceDesc(CurrencyName currency);

    Page<Currency> findByCurrency(CurrencyName name, Pageable pageable);
}
