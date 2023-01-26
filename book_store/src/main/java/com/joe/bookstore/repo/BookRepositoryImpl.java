package com.joe.bookstore.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.joe.bookstore.dto.BookQueryDslDTO;
import com.joe.bookstore.entity.Book;
import com.joe.bookstore.entity.QBook;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;

public class BookRepositoryImpl implements BookRepositorCustom {

	
	@PersistenceContext
	EntityManager em;
	
	public static QBook qbook =  QBook.book;
	
	@Override
	public List<Book> getAllByQueryDsl(Integer year) {
		System.out.println("query dsl impl");
		JPAQuery<Book> jpaQuery= new JPAQuery<>(em);
		
		QBean<Book> bookQBean = Projections.bean(Book.class, qbook.id,qbook.bookType);
		
		 List<Book> fetch = jpaQuery
				.from(qbook)
				.select(bookQBean)
				.where(qbook.yearOfPublication.eq(year))
				.fetch();
		 
		 System.out.println("all datad query dsl: "+fetch.toString());
		
		 return fetch;
	}
	 @Override
	    public List<BookQueryDslDTO> getAllBooksByQuerDslDto(Integer year) {

	        // query dsl
	        JPAQuery<BookQueryDslDTO> jpaQuery = new JPAQuery<>(em);

	        QBean<BookQueryDslDTO> dslDTOQBean = Projections.bean(BookQueryDslDTO.class,
	                qbook.id,
	                qbook.bookType.as("type")
	        );

	        List<BookQueryDslDTO> books = jpaQuery
	                .select(dslDTOQBean)
	                .from(qbook)
	                .where(qbook.yearOfPublication.eq(year))
	                .fetch();

	        return books;
	    }
	
	
}
