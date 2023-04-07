package com.bezkoder.spring.datajpa.controller;



	import java.util.ArrayList;
	import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;

	import com.bezkoder.spring.datajpa.model.Product;
	import com.bezkoder.spring.datajpa.repository.ProductRepository;

	@CrossOrigin(origins = "http://localhost:8081")
	@RestController
	@RequestMapping("/rest")
	public class ProductController {

		@Autowired
		ProductRepository productRepository;

		@GetMapping("/products")
		
		public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
			try {
				List<Product> products = new ArrayList<Product>();

				if (name == null)
					productRepository.findAll().forEach(products::add);
				else
					productRepository.findBynameContaining(name).forEach(products::add);

				if (products.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(products, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@GetMapping("/Products/{id}")
		public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
			Optional<Product> productData = productRepository.findById(id);

			if (productData.isPresent()) {
				return new ResponseEntity<>(productData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		@PostMapping("/products")
		
		public ResponseEntity<Product> createProduct(@RequestBody Product product) {
			try {
				int Id;
				Product _product = productRepository
						.save(new Product());
				return new ResponseEntity<>(_product, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@PutMapping("/products/{id}")
		public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product tutorial) {
			Optional<Product> productData = productRepository.findById(id);

			if (productData.isPresent()) {
				Product _product = productData.get();
				_product.setName(_product.getName());
				_product.setModel(_product.getModel());
				_product.setColour(_product.getColour());
				_product.setPrice(_product.getPrice());
				return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		@DeleteMapping("/products/{id}")
		public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
			try {
				productRepository.deleteAll();
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		@DeleteMapping(  )
		public ResponseEntity<HttpStatus> deleteAllTutorials() {
			try {
				productRepository.deleteAll();
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

		@GetMapping("/products/price")
		public ResponseEntity<List<Product>> findByPublished() {
			try {
			List<Product> products = productRepository.findByPrice(0);

				if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(products, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}

