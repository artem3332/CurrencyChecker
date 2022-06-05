package com.example.usd.service.impl;

import com.example.usd.response.CheckerResponse;
import com.example.usd.response.GifResponse;
import com.example.usd.response.CurrencyResponse;
import com.example.usd.service.CurrencyService;
import com.example.usd.service.GifService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyCheckerService {

    private final CurrencyService currencyService;
    private final GifService gifService;

    public CheckerResponse check(String currency) throws IOException {

        CurrencyResponse current = currencyService.historical(LocalDate.now());
        CurrencyResponse lastDay = currencyService.historical(LocalDate.now().minusDays(1));

        return isHigher(current, lastDay, currency)
                ? toResponse(gifService.search("rich"))
                : toResponse(gifService.search("broke"));
    }

    private boolean isHigher(CurrencyResponse current, CurrencyResponse lastDay, String currency) {
        return current.getRates().get(currency) > lastDay.getRates().get(currency);
    }

    private CheckerResponse toResponse(GifResponse response) {
        return new CheckerResponse(getGetRandomGif(response));
    }

    private String getGetRandomGif(GifResponse response){
        int index = new Random().nextInt(response.data().size());
        return response.data().get(index).images().get("original").url();
    }
}
