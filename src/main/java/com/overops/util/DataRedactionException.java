package com.overops.util;

public class DataRedactionException extends RuntimeException {

	private static final long serialVersionUID = 3064406863292282746L;

	public DataRedactionException() {
		super();
	}

	public DataRedactionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataRedactionException(String message) {
		super(message);
	}

	public DataRedactionException(Throwable cause) {
		super(cause);
	}	
}