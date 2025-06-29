package currencyClientApp;

import org.springframework.stereotype.Service;

import java.util.Optional;

public interface RateProvider {
    Optional<ExchangeRates> fetchRates();
}
