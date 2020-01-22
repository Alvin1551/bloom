package com.example.common.exception;

import com.example.common.model.ConsumerError;

/**
 * @author Alvin
 * This exception is used when there is invalid operation passed in the request parameter
 *
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InvalidOperationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4958867696830819075L;
	private ConsumerError error;
	

}
