package com.example.usd.service;

import com.example.usd.response.CurrencyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;

public interface CurrencyService {

     CurrencyResponse historical(LocalDate localDate) throws JsonProcessingException;

}
