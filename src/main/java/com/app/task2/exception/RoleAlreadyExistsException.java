package com.app.task2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class RoleAlreadyExistsException extends RuntimeException
{
private static final long serialVersionUID = 1L;
	
	
	public RoleAlreadyExistsException(String msg)
	{
		super(msg);
	}
}
