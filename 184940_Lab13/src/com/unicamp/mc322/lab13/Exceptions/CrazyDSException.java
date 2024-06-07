package com.unicamp.mc322.lab13.Exceptions;

public class CrazyDSException extends Exception{
	//Exceção personalizada exigida pelo enunciado
	public CrazyDSException() {
		super();
	}
	public CrazyDSException(String message) {
		super(message);
	}
	public CrazyDSException(Throwable cause) {
		super(cause);
	}
	public CrazyDSException(String message,Throwable cause) {
		super(message,cause);
	}
	
}
