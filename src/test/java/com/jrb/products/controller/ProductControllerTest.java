package com.jrb.products.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jrb.products.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	@Test
	@DisplayName("Verificar que la lista de productos no se retorne vacia")
	void findAllProductsTest() {
	}
	
	@Test
	@DisplayName("Verificar que la busqueda del producto por ID se ejecute de forma correcta")
	void findById() throws Exception {
		mockMvc.perform(get("/api/v1/productos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Verificar que el registro de un producto se ejecute correctamente")
	void saveProduct() {
		
	}
	
	@Test
	@DisplayName("Verificar que la actualizacion de un producto se ejecute correctamente")
	void updateProduct() {
		
	}
	
	@Test
	@DisplayName("Verificar que la eliminación de un producto se ejecute correctamente")
	void deleteProducto() {
		
	}
		
}