package currencyClientApp;

import com.example.currencyClientApp.service.CurrencyService;
import com.example.currencyClientApp.service.ExchangeRates;
import com.example.currencyClientApp.provider.RateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurrencyServiceTest {

    private CurrencyService currencyService;
    private RateProvider mockRateProvider;

    @BeforeEach
    void setUp() {
        mockRateProvider = mock(RateProvider.class);

        ExchangeRates rates = new ExchangeRates();
        rates.base = "USD";
        rates.rates = Map.of(
                "USD", 1.0,
                "EUR", 0.85,
                "JPY", 110.0
        );

        when(mockRateProvider.fetchRates()).thenReturn(Optional.of(rates));

        currencyService = new CurrencyService(mockRateProvider);
    }

    @Test
    void testUsdToEurConversion() {
        Optional<Double> result = currencyService.convert("USD", "EUR", 100.0);
        assertTrue(result.isPresent());
        assertEquals(85.0, result.get());
    }

    @Test
    void testEurToUsdConversion() {
        Optional<Double> result = currencyService.convert("EUR", "USD", 85.0);
        assertTrue(result.isPresent());
        assertEquals(100.0, result.get());
    }

    @Test
    void testSameCurrencyConversion() {
        Optional<Double> result = currencyService.convert("USD", "USD", 50.0);
        assertTrue(result.isPresent());
        assertEquals(50.0, result.get());
    }

    @Test
    void testInvalidCurrencyCode() {
        Optional<Double> result = currencyService.convert("ABC", "USD", 100.0);
        assertTrue(result.isEmpty());
    }

    @Test
    void testNullCurrencyCode() {
        Optional<Double> result = currencyService.convert(null, "EUR", 100.0);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAvailableCurrencies() {
        Set<String> currencies = currencyService.getAvailableCurrencies();
        assertEquals(3, currencies.size());
        assertTrue(currencies.contains("USD"));
        assertTrue(currencies.contains("EUR"));
        assertTrue(currencies.contains("JPY"));
    }
}