package com.joe.bookstore.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.joe.bookstore.entity.Author;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer>{

	Page<Author> findAllByOrderByIdDesc(Pageable pageable);
	
}
