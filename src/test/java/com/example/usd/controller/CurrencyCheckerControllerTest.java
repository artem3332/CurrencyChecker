package com.example.usd.controller;

import com.example.usd.response.CheckerResponse;
import com.example.usd.service.impl.CurrencyCheckerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(CurrencyCheckerController.class)
class CurrencyCheckerControllerTest {

    @Autowired
    private CurrencyCheckerController currencyCheckerController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyCheckerService currencyCheckerService;

    @Test
    public void checkTest() throws Exception {
        assertThat(currencyCheckerController).isNotNull();

        Mockito.when(currencyCheckerService.check(anyString())).thenReturn(new CheckerResponse("123"));

        this.mockMvc.perform(get("/checker")
                        .queryParam("currency", "RUB"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("123"));

    }

}