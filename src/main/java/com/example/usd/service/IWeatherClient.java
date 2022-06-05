package com.example.usd.service;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "name")
public interface IWeatherClient {


    @RequestLine("GET")
    String get();


}