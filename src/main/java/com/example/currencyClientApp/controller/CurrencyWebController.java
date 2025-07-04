package com.example.currencyClientApp.controller;

import com.example.currencyClientApp.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CurrencyWebController {

    private final CurrencyService currencyService;

    public CurrencyWebController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    @GetMapping("/convert")
    public String showForm(@RequestParam(required = false) String from,
                           @RequestParam(required = false) String to,
                           @RequestParam(required = false) Double amount,
                           @RequestParam(required = false) String result,
                           @RequestParam(required = false) String error,
                           Model model) {

        model.addAttribute("currencies", currencyService.getAvailableCurrencies());
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("amount", amount);

        if (result != null) {
            try {
                double parsedResult = Double.parseDouble(result);
                model.addAttribute("convertedAmount", parsedResult);
            } catch (NumberFormatException e) {
            }
        }

        if (error != null) {
            model.addAttribute("error", error);
        }

        return "converter";
    }

    @PostMapping("/convert")
    public String processConversion(@RequestParam String from,
                                    @RequestParam String to,
                                    @RequestParam Double amount) {

        Optional<Double> result = currencyService.convert(from, to, amount);

        if (result.isPresent()) {
            return "redirect:/convert?from=" + from +
                    "&to=" + to +
                    "&amount=" + amount +
                    "&result=" + result.get();

        } else {
            return "redirect:/convert?error=Invalid+input.+Please+check+currency+and+try+again." +
                    "&from=" + from + "&to=" + to + "&amount=" + amount;
        }
    }

}