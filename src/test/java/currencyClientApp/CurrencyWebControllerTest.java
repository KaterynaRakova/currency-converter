package currencyClientApp;

import com.example.currencyClientApp.service.CurrencyService;
import com.example.currencyClientApp.controller.CurrencyWebController;
import com.example.currencyClientApp.service.ExchangeRates;
import com.example.currencyClientApp.provider.RateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurrencyWebControllerTest {

    private CurrencyWebController controller;

    @BeforeEach
    void setUp() {

        RateProvider mockProvider = mock(RateProvider.class);

        ExchangeRates rates = new ExchangeRates();
        rates.base = "USD";
        rates.rates = Map.of(
                "USD", 1.0,
                "EUR", 0.85,
                "JPY", 110.0
        );

        when(mockProvider.fetchRates()).thenReturn(Optional.of(rates));

        CurrencyService currencyService = new CurrencyService(mockProvider);
        controller = new CurrencyWebController(currencyService);
    }

    @Test
    void testShowFormWithValidData() {
        Model model = new ConcurrentModel();

        String view = controller.showForm("USD", "EUR", 100.0, "85.0", null, model);

        assertEquals("converter", view);
        assertEquals("USD", model.getAttribute("from"));
        assertEquals("EUR", model.getAttribute("to"));
        assertEquals(100.0, model.getAttribute("amount"));
        assertEquals(85.0, (Double)model.getAttribute("convertedAmount"));
        assertNull(model.getAttribute("error"));
    }

    @Test
    void testShowFormWithError() {
        Model model = new ConcurrentModel();

        String view = controller.showForm("USD", "XXX", 100.0, null, "Invalid input", model);

        assertEquals("converter", view);
        assertEquals("Invalid input", model.getAttribute("error"));
    }

    @Test
    void testProcessConversionSuccess() {
        String redirect = controller.processConversion("USD", "EUR", 100.0);
        assertEquals("redirect:/convert?from=USD&to=EUR&amount=100.0&result=85.0", redirect);

    }

    @Test
    void testProcessConversionFailure() {
        String redirect = controller.processConversion("USD", "XXX", 100.0);
        assertTrue(redirect.contains("redirect:/convert?error=Invalid"));
        assertTrue(redirect.contains("from=USD"));
        assertTrue(redirect.contains("to=XXX"));
    }
}