package com.example.jpa.notice.model;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseError {
	private String field;
	private String message;
	
	public static ResponseError of(ObjectError e) {
		return ResponseError.builder()
		.field(((FieldError)e).getField())
		.message(e.getDefaultMessage())
		.build();
	}

}
