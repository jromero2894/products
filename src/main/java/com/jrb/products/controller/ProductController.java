package com.jrb.products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.products.model.Product;
import com.jrb.products.service.ProductService;
import com.jrb.products.validation.EntityValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductController {

	@Autowired
	private ProductService productService; 
	
	@Autowired
	private EntityValidation entityValidation;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> products = productService.findAll();
		return (products.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Product>> findById(@PathVariable Long id){
		Optional<Product> product = productService.findById(id);
		return (product.isPresent()) ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){
		if(result.hasFieldErrors()) {
			return entityValidation.validate(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Product product, BindingResult result){
		if(result.hasFieldErrors()) {
			return entityValidation.validate(result);
		}
		Optional<Product> productBD = productService.findById(id);
		return (productBD.isPresent()) ? ResponseEntity.status(HttpStatus.OK).body(productService.update(id, product)) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id){
		Optional<Product> product = productService.findById(id);
		if(product.isPresent()) {
			productService.remove(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}		
		return ResponseEntity.notFound().build();
	}
	
}