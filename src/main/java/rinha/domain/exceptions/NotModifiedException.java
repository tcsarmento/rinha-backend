package rinha.domain.exceptions;

import org.springframework.http.HttpStatus;

import rinha.domain.enumeration.ExceptionMessagesEnum;

public class NotModifiedException extends HttpException {

	private static final long serialVersionUID = 1L;
	
	public NotModifiedException(final String message) {
		super(message, HttpStatus.NOT_MODIFIED);
	}

	public NotModifiedException(final ExceptionMessagesEnum exceptionMessagesEnum) {
		super(exceptionMessagesEnum.getCode(), exceptionMessagesEnum.getMessage(), HttpStatus.NOT_MODIFIED);
	}

}