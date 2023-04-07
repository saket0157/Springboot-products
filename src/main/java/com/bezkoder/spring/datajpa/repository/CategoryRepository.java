package com.bezkoder.spring.datajpa.repository;



	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;

	import com.bezkoder.spring.datajpa.model.Category;

	public interface CategoryRepository  extends JpaRepository<Category, Long> {
		List<Category> findByStock(boolean stock);
		List<Category> findByTypeContaining(String typeS);
	}

