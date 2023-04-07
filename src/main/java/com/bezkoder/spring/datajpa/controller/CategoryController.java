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

import com.bezkoder.spring.datajpa.model.Category;
import com.bezkoder.spring.datajpa.repository.CategoryRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/rest")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/categoryp")
	
	public ResponseEntity<List<Category>> getAllCategoryp(@RequestParam(required = false) String type) {
		try {
			List<Category> Categoryp = new ArrayList<Category>();

			if (type == null)
				categoryRepository.findAll().forEach(Categoryp::add);
			else
				categoryRepository.findByTypeContaining(type).forEach(Categoryp::add);

			if (Categoryp.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Categoryp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/categoryp/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
		Optional<Category> tutorialData = categoryRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/categoryp")
	
	public ResponseEntity<Category> createTutorial(@RequestBody Category category) {
		try {
			Category _category = categoryRepository
					.save(new Category(category.getType(), category.getName(), false));
			return new ResponseEntity<>(_category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/categoryp/{id}")
	public ResponseEntity<Category> updateTutorial(@PathVariable("id") long id, @RequestBody Category category) {
		Optional<Category> categoryData = categoryRepository.findById(id);

		if (categoryData.isPresent()) {
			Category _category= categoryData.get();
			_category.setType(category.getType());
			_category.setName(category.getName());
			_category.setStock(category.isStock());
			return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/categoryp/{id}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") long id) {
		try {
			categoryRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/categoryp")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			categoryRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/categoryp/stock")
	public ResponseEntity<List<Category>> findByPublished() {
		try {
			List<Category> categoryp = categoryRepository.findByStock(true);

			if (categoryp.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(categoryp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
