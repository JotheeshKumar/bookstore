package com.joe.bookstore.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joe.bookstore.entity.BookAuthor;

@Repository
public interface BookAuthorRepository extends CrudRepository<BookAuthor, Integer> {

	List<BookAuthor> findAllByBookId(Integer id);

}
