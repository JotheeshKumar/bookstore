package com.joe.bookstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joe.bookstore.commom.APIResponse;
import com.joe.bookstore.data.BookData;
import com.joe.bookstore.dto.BookDTO;
import com.joe.bookstore.entity.Book;
import com.joe.bookstore.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/all")
	public Iterable<Book> findAllBooks(@RequestParam(value = "yearOfPublications", required = false) Set<Integer> yop,
			@RequestParam(value = "bookType", required = false) String bookType) {

		return bookService.getBooks(yop, bookType);

	}

	@GetMapping("/{id}")
	public BookDTO findBookById(@PathVariable("id") Integer id,
			@RequestParam(value = "authorData", required = false) Boolean authorData) {
		Optional<Boolean> aData = Optional.ofNullable(authorData);
		if (aData.isPresent()) {
			return bookService.getOneBook(id, authorData);
		} else {
			return bookService.getOneBook(id);
		}

	}

	@PostMapping("/")
	public Book findBookById(@RequestBody Book book) {

		return bookService.createBook(book);

	}

	@PutMapping("/update/{id}")
	public Book updateBook(@RequestBody Book incomingBook) {
		return bookService.updateBook(incomingBook);
	}

	@DeleteMapping("/{bookId}")
	public String deleteBookById(@PathVariable Integer bookId) {
		return bookService.deleteById(bookId);
	}

	@GetMapping("/queryDsl/books")
	public APIResponse getBookByQueryDsl(@RequestParam(value = "year") Integer year) {
		System.out.println("query dsl controller");
		return bookService.getBookByQueryDsl(year);
	}
	
	@GetMapping("/raw/")
	public APIResponse getBooksByRawQuery(@RequestParam(value = "yop") Set<Integer> yop) {
		List<Book> booksList = bookService.getBooksByRawQuery(yop);
		APIResponse apiResponse = new APIResponse();
		BookData bookData = new BookData();
		bookData.setBooks(booksList);
		apiResponse.setData(bookData);

		return apiResponse;
	}

}
