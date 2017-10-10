package com.sri.sampleboot.web.exception;

public class BookIdMissmatchException extends RuntimeException {

	public BookIdMissmatchException() {
		super();
	}

	public BookIdMissmatchException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BookIdMissmatchException(final String message) {
		super(message);
	}

	public BookIdMissmatchException(final Throwable cause) {
		super(cause);
	}
}
