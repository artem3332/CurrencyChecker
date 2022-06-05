package com.example.usd.service.impl;

import com.example.usd.response.CurrencyResponse;
import com.example.usd.response.GifResponse;
import com.example.usd.service.CurrencyService;
import com.example.usd.service.GifService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class CurrencyCheckerServiceTest {

    @Mock
    private CurrencyService currencyService;

    @Mock
    private GifService gifService;

    @InjectMocks
    private CurrencyCheckerService currencyCheckerService;

    @Test
    public void richCheckTest() throws Exception {

        when(gifService.search(anyString())).thenReturn(new GifResponse(List.of(new GifResponse.Data(Map.of("original", new GifResponse.Data.Image("123"))))));
        when(currencyService.historical(any(LocalDate.class)))
                .thenReturn(currencyResponse(60.00), currencyResponse(55.00));

        currencyCheckerService.check("RUB");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(gifService).search(captor.capture());

        assertEquals("rich", captor.getValue());
    }

    @Test
    public void brokeCheckTest() throws Exception {

        when(gifService.search(anyString())).thenReturn(new GifResponse(List.of(new GifResponse.Data(Map.of("original", new GifResponse.Data.Image("123"))))));
        when(currencyService.historical(any(LocalDate.class)))
                .thenReturn(currencyResponse(60.00), currencyResponse(65.00));

        currencyCheckerService.check("RUB");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(gifService).search(captor.capture());

        assertEquals("broke", captor.getValue());
    }

    private CurrencyResponse currencyResponse(Double value) {
        CurrencyResponse response = new CurrencyResponse();
        response.setRates(Map.of("RUB", value));
        return response;
    }

}