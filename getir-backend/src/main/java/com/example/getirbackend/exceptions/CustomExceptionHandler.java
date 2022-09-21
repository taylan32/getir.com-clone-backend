package com.example.getirbackend.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BusinessError> handleBusinessExceptions(BusinessException exception) {
		return new ResponseEntity<BusinessError>(new BusinessError(HttpStatus.BAD_REQUEST, "example.com/probs/business",
				"Business exception", exception.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> handleValidaitonException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationExceptions = new HashMap<>();

		for (FieldError error : exceptions.getBindingResult().getFieldErrors()) {
			validationExceptions.put(error.getField(), error.getDefaultMessage());
		}

		return new ResponseEntity<ValidationError>(new ValidationError(HttpStatus.BAD_REQUEST,
				"example.com/prons/validations", "Validaiton exception(s)", "Validation error.", validationExceptions),
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<NotFoundError> handleNotFoundException(NotFoundException exception) {
		return new ResponseEntity<NotFoundError>(
				new NotFoundError(HttpStatus.NOT_FOUND, "example.com/probs/notfound", "Not found", exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleAuthException(Exception exception) {
		return new ResponseEntity<ApiError>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "example.com/probs/internal",
				"Internal Server Error", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
