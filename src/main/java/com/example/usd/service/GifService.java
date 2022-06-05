package com.example.usd.service;

import com.example.usd.response.GifResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface GifService {

    GifResponse search(String search) throws JsonProcessingException;
}
