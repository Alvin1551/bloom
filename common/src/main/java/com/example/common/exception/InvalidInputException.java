package com.example.common.exception;

import com.example.common.model.ConsumerError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alvin
 * This exception is used when there is invalid input
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InvalidInputException extends RuntimeException {
	private ConsumerError error;
	private static final long serialVersionUID = 7551012729879722845L;
	

}
