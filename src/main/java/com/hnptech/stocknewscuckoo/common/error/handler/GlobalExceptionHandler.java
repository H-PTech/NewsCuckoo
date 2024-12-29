package com.hnptech.stocknewscuckoo.common.error.handler;

import com.hnptech.stocknewscuckoo.common.response.ErrorResponse;
import com.hnptech.stocknewscuckoo.common.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler({NoHandlerFoundException.class})
	public ResponseEntity<?> handleNotFoundException(Exception e) {
		return generateResponse(e.getMessage());
	}

	private ResponseEntity<ApiResult<?>> generateResponse(String message) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return new ResponseEntity<>(ApiResult.failure(new ErrorResponse(HttpStatus.NOT_FOUND,message)), httpHeaders,
				HttpStatus.NOT_FOUND);
	}
}
