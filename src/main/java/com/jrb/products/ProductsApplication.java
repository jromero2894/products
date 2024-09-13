package com.jrb.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jrb.products.model.Product;
import com.jrb.products.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProductsApplication implements CommandLineRunner{

	@Autowired
	private ProductService productService;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.productService.save(new Product("Galletas", "Alimentos", 20));
		this.productService.save(new Product("Coca-Cola", "Bebidas", 25));
		this.productService.save(new Product("Mouse", "Electronicos", 400));
	}

	@Bean
	public WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedMethods("*")
						.allowedOrigins("*")
						.allowedHeaders("*")
						.allowedOriginPatterns("*");
			}
		};
	}
}