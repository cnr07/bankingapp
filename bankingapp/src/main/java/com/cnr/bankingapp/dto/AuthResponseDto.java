package com.cnr.bankingapp.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponseDto {
	@JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("message")
    private String message;
}
