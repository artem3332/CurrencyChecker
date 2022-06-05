package com.example.usd.response;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyResponse {

    private String disclaimer;
    private String license;
    private String timestamp;
    private String base;
    private Map<String,Double> rates;


}
