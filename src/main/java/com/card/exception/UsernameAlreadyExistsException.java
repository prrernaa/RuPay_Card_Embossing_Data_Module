package com.card.exception;

import org.springframework.ui.Model;

public class UsernameAlreadyExistsException extends RuntimeException{
	 public UsernameAlreadyExistsException(String message) {
	        super(message);
	    }

	public UsernameAlreadyExistsException(Model attribute) {
		// TODO Auto-generated constructor stub
	}

	

}
