package com.joe.bookstore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joe.bookstore.commom.Error;
import com.joe.bookstore.entity.Book;

@Component
public class BookValidator {

	
	public List<Error> validateCreateBook(Book book) {
		
		List<Error> errors = new ArrayList<>();
        if(book.getName() == null){
            Error error = new Error("name", "book name is null");
            errors.add(error);
        }

        // yop
        if(book.getYearOfPublication() == null){
            Error error = new Error("yop", "yop is null");
            errors.add(error);
        }

        // book type
        if(book.getBookType() == null){
            errors.add(new Error("bookType", "bookType is null"));
        }
        
        System.out.println(errors.toString());

        return errors;
		
	}
}
