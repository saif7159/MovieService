package com.movie.catalogue.Exception;

import javax.validation.ConstraintViolationException;

//import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.movie.catalogue.Exception.ErrResponce;
import com.movie.catalogue.Exception.ExceptionMessage;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionController {
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<ErrResponce> handleAllExceptions(Exception exception) {
//		ErrResponce errorresp = new ErrResponce(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
//		return new ResponseEntity(errorresp, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = MovieNotFoundException.class)
	public ResponseEntity<ErrResponce> userNotFound(MovieNotFoundException exception) {
		ErrResponce errorresp = new ErrResponce(HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return new ResponseEntity<ErrResponce>(errorresp, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<ErrResponce> numberFormat(NumberFormatException exception) {
		ErrResponce errorresp = new ErrResponce(HttpStatus.BAD_REQUEST.value(), exception.getMessage().replaceFirst(".*For input string: ", "Pease enter the number"));
		return new ResponseEntity<ErrResponce>(errorresp, HttpStatus.BAD_REQUEST);
	}
		
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	@ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
	public ResponseEntity<ErrResponce> maintainence(InvalidDataAccessResourceUsageException exception) {
		ErrResponce errorresp = new ErrResponce(HttpStatus.SERVICE_UNAVAILABLE.value(), ExceptionMessage.DATABASE.getMessage());
		return new ResponseEntity<ErrResponce>(errorresp, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ResponseStatus(value = HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<ErrResponce> notEmpty(ConstraintViolationException exception) {
		ErrResponce errorresp = new ErrResponce(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value(), ExceptionMessage.NOTEMPTY.getMessage());
		return new ResponseEntity<ErrResponce>(errorresp, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
	}
}
