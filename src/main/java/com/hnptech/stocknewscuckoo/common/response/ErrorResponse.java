package com.hnptech.stocknewscuckoo.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

	private final int status;
	private final String message;

	public ErrorResponse(HttpStatus status, Throwable throwable) {
		this.status = status.value();
		this.message = throwable.getMessage();
	}

	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public ErrorResponse(HttpStatus status, String message) {
		this.status = status.value();
		this.message =message;
	}
}
