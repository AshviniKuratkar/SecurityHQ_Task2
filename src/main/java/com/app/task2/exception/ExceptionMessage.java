package com.app.task2.exception;

public class ExceptionMessage extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public ExceptionMessage(String msg)
	{
		super(msg);
	}
}
