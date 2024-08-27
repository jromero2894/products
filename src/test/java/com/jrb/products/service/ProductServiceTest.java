package com.jrb.products.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jrb.products.model.Product;

@SpringBootTest
public class ProductServiceTest {
	
	@Autowired
	private ProductService productService;
	
	@Test
	@DisplayName("Verificar que la lista de productos no se retorne vacia")
	void findAllProductsTest() {
		List<Product> products = productService.findAll();
		assertFalse(products.isEmpty());
		assertEquals(3, products.size());
	}
	
	@Test
	@DisplayName("Verificar que la busqueda del producto por ID se ejecute de forma correcta")
	void findById() {
		Optional<Product> product = productService.findById(1L);
		assertTrue(product.isPresent());
		assertThrows(NoSuchElementException.class, product::orElseThrow);
	}
	
	@Test
	@DisplayName("Verificar que el registro de un producto se ejecute correctamente")
	void saveProduct() {
		
		productService.save(new Product("Pollo", "Alimentos", 70));	
		
		List<Product> productos = productService.findAll();
		assertNotNull(productos
				.stream()
				.filter(p -> p.getName().equals("Pollo"))
				.findFirst());
	}
	
	@Test
	@DisplayName("Verificar que la actualizacion de un producto se ejecute correctamente")
	void updateProduct() {
		
		Product product = productService.save(new Product("Arroz", "Alimentos", 20));	
		assertEquals(4, product.getId());
		
		product.setName("Trigo");
		product.setPrice(40);
		Optional<Product> productoUpdate = productService.update(product.getId(), product);
		
		assertEquals("Trigo", productoUpdate.orElseThrow().getName());
		assertEquals(40, productoUpdate.orElseThrow().getPrice());
	}
	
	@Test
	@DisplayName("Verificar que la eliminación de un producto se ejecute correctamente")
	void deleteProducto() {
		
		Long productId = 1L;
		Optional<Product> product = productService.findById(productId);
		assertTrue(product.isPresent());
		
		productService.remove(productId);
	
		assertThrows(NoSuchElementException.class, () -> productService.findById(productId).orElseThrow());
	}
	
}