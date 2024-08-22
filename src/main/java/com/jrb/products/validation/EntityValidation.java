package com.jrb.products.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class EntityValidation {

	public ResponseEntity<Object> validate(BindingResult result){
		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(e -> {
			errors.put(e.getField(), "El campo: " + e.getField() + " " + e.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
	
}