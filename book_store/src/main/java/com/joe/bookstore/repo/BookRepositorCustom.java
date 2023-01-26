package com.joe.bookstore.repo;

import java.util.List;

import com.joe.bookstore.dto.BookQueryDslDTO;
import com.joe.bookstore.entity.Book;

public interface BookRepositorCustom {
	//System.out.println("query dsl custom");
	public List<Book> getAllByQueryDsl(Integer year);
	
	 public List<BookQueryDslDTO> getAllBooksByQuerDslDto(Integer year);
	 
	 

}
