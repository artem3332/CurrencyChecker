package com.example.usd.controller;


import com.example.usd.response.CheckerResponse;
import com.example.usd.service.impl.CurrencyCheckerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/checker")
@RequiredArgsConstructor
public class CurrencyCheckerController {

    private final CurrencyCheckerService usdService;

    @GetMapping
    public ResponseEntity<CheckerResponse> check(@RequestParam String currency) throws IOException {
        return ResponseEntity.ok(usdService.check(currency));
    }
}
