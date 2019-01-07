package com.vladwoguer.exception;

public class AuthorizationException extends Exception {
	
	private static final long serialVersionUID = 4013253775031702084L;

	public AuthorizationException() {
		super("Invalid credentials");
	}
}
