package br.com.medical.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessUnauthorizedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessUnauthorizedException(String msgError) {
		// TODO Auto-generated constructor stub
		super(msgError);
	}
	
}
