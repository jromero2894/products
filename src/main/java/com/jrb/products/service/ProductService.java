package com.jrb.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrb.products.model.Product;
import com.jrb.products.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Product> findById(Long id){
		return productRepository.findById(id);
	}
	
	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	@Transactional
	public Optional<Product> update(Long id, Product product) {
		
		Optional<Product> productOptional = this.findById(id);
		if(productOptional.isPresent()) {
			Product productBD = productOptional.orElseThrow();
			productBD.setName(product.getName());
			productBD.setType(product.getType());
			productBD.setPrice(product.getPrice());
			return Optional.of(productRepository.save(productBD));
		}
		
		return productOptional;		
	}

	@Transactional
	public void remove(Long id) {
		productRepository.deleteById(id);
	}
	
}