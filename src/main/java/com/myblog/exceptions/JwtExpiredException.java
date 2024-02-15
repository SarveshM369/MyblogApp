package com.myblog.exceptions;

@SuppressWarnings("serial")
public class JwtExpiredException extends RuntimeException{
	public JwtExpiredException(String message) {
        super(message);
    }
}

