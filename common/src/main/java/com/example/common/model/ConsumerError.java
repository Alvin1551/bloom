package com.example.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alvin
 *	This class is used for creating custom error message
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerError {

    private String errorCode;
    private String userMessage;

   
}
