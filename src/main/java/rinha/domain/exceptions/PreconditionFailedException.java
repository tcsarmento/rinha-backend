package rinha.domain.exceptions;

import org.springframework.http.HttpStatus;

import rinha.domain.enumeration.ExceptionMessagesEnum;

public class PreconditionFailedException extends HttpException {

	private static final long serialVersionUID = 1L;

	public PreconditionFailedException(final String message) {
		super(message, HttpStatus.PRECONDITION_FAILED);
	}

	public PreconditionFailedException(final ExceptionMessagesEnum exceptionMessagesEnum) {
		super(exceptionMessagesEnum.getCode(), exceptionMessagesEnum.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}
}