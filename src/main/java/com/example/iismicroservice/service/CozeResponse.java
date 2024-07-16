package com.example.iismicroservice.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CozeResponse {
    @JsonProperty("response")
    private String response;

    // Getters and setters
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "CozeResponse{" +
                "response='" + response + '\'' +
                '}';
    }
}
