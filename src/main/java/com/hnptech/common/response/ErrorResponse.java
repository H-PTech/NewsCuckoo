package com.hnptech.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

	private final int status;
	private final String message;

	@Builder
	public ErrorResponse(int status, Throwable throwable) {
		this.status = status;
		this.message = throwable.getMessage();
	}
}
