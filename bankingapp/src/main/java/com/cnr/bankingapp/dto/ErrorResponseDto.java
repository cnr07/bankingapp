package com.cnr.bankingapp.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponseDto {
	private final HttpStatus status;
	private final String message;
}
