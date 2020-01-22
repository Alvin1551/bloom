package com.example.common.advice;

import static com.example.common.constants.GeneralConstants.ERR_5001;
import static com.example.common.constants.GeneralConstants.ERR_5001_MSG;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.common.constants.GeneralConstants;
import com.example.common.exception.DownStreamException;
import com.example.common.exception.InvalidInputException;
import com.example.common.exception.InvalidOperationException;
import com.example.common.model.ConsumerError;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Alvin
 *@This class is used to handled all the exceptions
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {
	/**
	 * This method is used handle all the exceptions 
	 * @param request
	 * @param ex
	 * @return ResponseEntity with ConsumerError object and status 500
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ConsumerError> hanleException(HttpServletRequest request, Exception ex) {
		log.error("Exception ", ex);
		return new ResponseEntity<ConsumerError>(
				new ConsumerError(ERR_5001, ERR_5001_MSG),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ConsumerError> hanleInputException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
		log.error("MethodArgumentTypeMismatchException ", ex);
		return new ResponseEntity<ConsumerError>(
				new ConsumerError(GeneralConstants.ERR_4003, GeneralConstants.ERR_4003_MSG),
				HttpStatus.BAD_REQUEST);

	}
	/**
	 * This method is used handle InvalidInputException
	 * @param request
	 * @param ex
	 * @return ResponseEntity with ConsumerError object and status 400
	 */
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ConsumerError> handleInvalidInputException(HttpServletRequest request,
			InvalidInputException ex) {
		return new ResponseEntity<ConsumerError>(ex.getError(), HttpStatus.BAD_REQUEST);

	}
	/**
	 * This method is used handle InvalidOperationException
	 * @param request
	 * @param ex
	 * @return ResponseEntity with ConsumerError object and status 400
	 */
	@ExceptionHandler(InvalidOperationException.class)
	public ResponseEntity<ConsumerError> handleInvalidOperationException(HttpServletRequest request,
			InvalidOperationException ex) {
		return new ResponseEntity<ConsumerError>(ex.getError(), HttpStatus.BAD_REQUEST);

	}
	/**
	 * This method is used handle DownStreamException
	 * @param request
	 * @param ex
	 * @return ResponseEntity with ConsumerError object and status 500
	 */
	@ExceptionHandler(DownStreamException.class)
	public ResponseEntity<ConsumerError> handleDownStreamExecption(HttpServletRequest request,
			DownStreamException ex) {
		return new ResponseEntity<ConsumerError>(ex.getError(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
