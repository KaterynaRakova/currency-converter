package currencyClientApp;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class CurrencyService {

    private final RateProvider rateProvider;

    public CurrencyService(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    public Optional<Double> convert(String from, String to, double amount) {
        Optional<ExchangeRates> ratesOpt = rateProvider.fetchRates();

        if (ratesOpt.isPresent()) {
            ExchangeRates rates = ratesOpt.get();
            Map<String, Double> rateMap = rates.rates;
            if (!isValidCurrency(from, rateMap) || !isValidCurrency(to, rateMap)) {
                return Optional.empty();
            }

            double rateFrom = from.equalsIgnoreCase(rates.base) ? 1.0 : rateMap.getOrDefault(from.toUpperCase(), -1.0);
            double rateTo = to.equalsIgnoreCase(rates.base) ? 1.0 : rateMap.getOrDefault(to.toUpperCase(), -1.0);

            if (rateFrom <= 0 || rateTo <= 0) return Optional.empty();

            double usdAmount = amount / rateFrom;
            double converted = usdAmount * rateTo;

            return Optional.of(Math.round(converted * 100.0) / 100.0);
        }

        return Optional.empty();
    }

    public Set<String> getAvailableCurrencies() {
        return rateProvider.fetchRates()
                .map(r -> r.rates.keySet())
                .orElse(Collections.emptySet());
    }

    private boolean isValidCurrency(String code, Map<String, Double> rateMap) {
        return code != null && code.matches("[A-Za-z]{3}") && rateMap.containsKey(code.toUpperCase());
    }
}