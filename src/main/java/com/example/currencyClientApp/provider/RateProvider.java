package com.example.currencyClientApp.provider;

import com.example.currencyClientApp.service.ExchangeRates;

import java.util.Optional;

public interface RateProvider {
    Optional<ExchangeRates> fetchRates();
}
