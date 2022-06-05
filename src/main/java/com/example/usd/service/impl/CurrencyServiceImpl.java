package com.example.usd.service.impl;

import com.example.usd.response.CurrencyResponse;
import com.example.usd.service.CurrencyService;
import com.example.usd.service.IWeatherClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${course_service.usd.url}")
    private String url;

    @Value("${course_service.app_id}")
    private String appId;

    @Override
    public CurrencyResponse historical(LocalDate localDate) throws JsonProcessingException {
        UriComponents build = UriComponentsBuilder.fromHttpUrl(String.format(url, localDate))
                .queryParam("app_id", appId).build();

        IWeatherClient iWeatherClient = Feign.builder().target(IWeatherClient.class,build.toUriString());
        return  new ObjectMapper().readValue(iWeatherClient.get(), new TypeReference<>() {
        });
    }
}
