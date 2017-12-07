package com.denis.parserbackend.dto;

public class DtoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DtoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DtoException(Throwable cause) {
		super(cause);
	}

	public DtoException(String message) {
		super(message);
	}

}
