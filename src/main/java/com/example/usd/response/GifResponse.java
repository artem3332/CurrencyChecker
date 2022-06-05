package com.example.usd.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GifResponse(List<Data> data) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Data(Map<String, Image> images) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Image(String url) {

        }
    }
}
