package com.example.usd.service.impl;

import com.example.usd.response.GifResponse;
import com.example.usd.service.GifService;
import com.example.usd.service.IWeatherClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GifServiceImpl implements GifService {


    @Value("${gif_service.search.url}")
    private String url;


    @Value("${gif_service.api_key}")
    private String apiKey;


    @Override
    public GifResponse search(String search) throws JsonProcessingException {

        UriComponents build = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api_key", apiKey)
                .queryParam("limit",50)
                .queryParam("q",search)
                .build();

        IWeatherClient iWeatherClient = Feign.builder().target(IWeatherClient.class,build.toUriString());

        return new ObjectMapper().readValue(iWeatherClient.get(), new TypeReference<>() {
        });

    }
}
