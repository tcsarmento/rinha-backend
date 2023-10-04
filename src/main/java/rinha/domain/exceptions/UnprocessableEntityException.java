package rinha.domain.exceptions;

import org.springframework.http.HttpStatus;
import rinha.domain.enumeration.ExceptionMessagesEnum;

public class UnprocessableEntityException extends HttpException {

	private static final long serialVersionUID = 1L;

	public UnprocessableEntityException(final String message) {
		super(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	public UnprocessableEntityException(final ExceptionMessagesEnum exceptionMessagesEnum) {
		super(exceptionMessagesEnum.getCode(), exceptionMessagesEnum.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
}