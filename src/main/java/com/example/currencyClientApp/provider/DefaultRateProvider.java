package com.example.currencyClientApp.provider;

import com.example.currencyClientApp.provider.RateProvider;
import com.example.currencyClientApp.service.ExchangeRates;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

@Service
public class DefaultRateProvider implements RateProvider {

    @Value("${openexchangerates.api-key}")
    private String apiKey;

    @Override
    public Optional<ExchangeRates> fetchRates() {
        String url = "https://openexchangerates.org/api/latest.json?app_id=" + apiKey;

        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 && response.body() != null && !response.body().isEmpty()) {
                ExchangeRates rates = new Gson().fromJson(response.body(), ExchangeRates.class);
                return Optional.ofNullable(rates).filter(r -> r.rates != null);
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while fetching exchange rates", e);
        }

        return Optional.empty();
    }
}