package com.jrb.products.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "es obligatorio")
	private String name;
	
	@NotBlank(message = "es obligatorio")
	private String type;
	
	@NotNull
	@Min(value = 1, message = "debe tener un valor mayor o igual a 1")
	@Max(value = 9999, message = "debe tener un valor menor o igual a 9999")
	private int price;

	public Product() {
		super();
	}

	public Product(String name, String type, int price) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
	}

}		