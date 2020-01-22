package com.example.common.exception;

import com.example.common.model.ConsumerError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DownStreamException extends RuntimeException {

	private ConsumerError error;

	private static final long serialVersionUID = 3552963811322766637L;

}
