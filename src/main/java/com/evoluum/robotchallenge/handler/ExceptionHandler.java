package com.evoluum.robotchallenge.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.evoluum.robotchallenge.exceptions.BorderOutException;
import com.evoluum.robotchallenge.exceptions.InvalidCommandException;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler({ BorderOutException.class,
			InvalidCommandException.class })
	public ResponseEntity<JsonResponse> handleRunTimeException(Exception e, HttpServletRequest request) {

		 return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	private class JsonResponse {
		String message;

		public JsonResponse() {
		}

		public JsonResponse(String message) {
			super();
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

}
