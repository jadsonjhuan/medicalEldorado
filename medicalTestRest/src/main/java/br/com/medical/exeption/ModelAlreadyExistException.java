package br.com.medical.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ModelAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ModelAlreadyExistException(String msgError) {
		// TODO Auto-generated constructor stub
		super(msgError);
	}
}
