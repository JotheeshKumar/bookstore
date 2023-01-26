package com.joe.bookstore.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joe.bookstore.dto.BookQueryDslDTO;
import com.joe.bookstore.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>, BookRepositorCustom {

	// List<Book> findAllByYearOfPublicationIn(Set<Integer> yop);
	List<Book> findAllByYearOfPublicationInAndBookType(Set<Integer> yop, String bookType);

	List<Book> findAllByYearOfPublicationIn(Set<Integer> yop);

	List<Book> findAllByBookType(String bookType);

	Optional<Book> findById(Integer id);

	// findAllByYearOfPublicationInAndBookType
	
	String rawQuery = "select * from book where year_of_publication IN ?1";

    @Query(nativeQuery = true, value = rawQuery)
    List<Book> findAllBooksByYearOfPublicationIn( Set<Integer> yop);

	

}
