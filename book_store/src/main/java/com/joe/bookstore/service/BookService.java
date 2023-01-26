package com.joe.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joe.bookstore.commom.Error;
import com.joe.bookstore.commom.APIResponse;
import com.joe.bookstore.commom.BadRequestException;
import com.joe.bookstore.dto.AuthorDTO;
import com.joe.bookstore.dto.BookDTO;
import com.joe.bookstore.dto.BookQueryDslDTO;
import com.joe.bookstore.entity.Author;
import com.joe.bookstore.entity.Book;
import com.joe.bookstore.entity.BookAuthor;
import com.joe.bookstore.repo.BookAuthorRepository;
import com.joe.bookstore.repo.BookRepository;
import com.joe.bookstore.validator.BookValidator;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookAuthorRepository bookauthorRepository;

	@Autowired
	private BookValidator bookValidator;

	// Get
	public List<Book> getBooks(Set<Integer> yop, String bookType) {

		List<Book> bookList = new ArrayList<>();

		if (yop == null && bookType == null) {
			bookRepository.findAll().forEach(book -> bookList.add(book));
			return bookList;
		}

		else if (yop != null && bookType == null) {
			return bookRepository.findAllByYearOfPublicationIn(yop);
		} else if (yop == null && bookType != null) {
			return bookRepository.findAllByBookType(bookType);
		}

		else {
			return bookRepository.findAllByYearOfPublicationInAndBookType(yop, bookType);
			// return bookRepository.findAllByYearOfPublicationIn(yop);
		}

	}

	public BookDTO getOneBook(Integer id) {
		List<BookAuthor> bookAuthors = null;
		Optional<Book> bookOptional = bookRepository.findById(id);
		System.out.println(bookOptional.isPresent());
		Book book = bookOptional.get();
		System.out.println(book.toString());

		if (true) {
			bookAuthors = bookauthorRepository.findAllByBookId(id);
		}

		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setName(book.getName());
		bookDTO.setBookType(book.getBookType());
		bookDTO.setYearOfPublication(book.getYearOfPublication());
		bookDTO.setDesc(book.getDesc());
		return bookDTO;

	}

	public BookDTO getOneBook(Integer id, Boolean authorData) {
		List<BookAuthor> bookAuthors = null;
		Optional<Book> bookOptional = bookRepository.findById(id);
		System.out.println(bookOptional.isPresent());
		Book book = bookOptional.get();
		System.out.println(book.toString());

		if (authorData) {
			bookAuthors = bookauthorRepository.findAllByBookId(id);
		}

		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setName(book.getName());
		bookDTO.setBookType(book.getBookType());
		bookDTO.setYearOfPublication(book.getYearOfPublication());
		bookDTO.setDesc(book.getDesc());

		List<AuthorDTO> authorDTOList = new ArrayList<>();
		if (bookAuthors != null) {
			for (BookAuthor bookAuthor : bookAuthors) {
				Author author = bookAuthor.getAuthor();
				AuthorDTO authorDTO = new AuthorDTO();
				authorDTO.setId(author.getId());
				authorDTO.setGender(author.getGender());
				authorDTO.setName(author.getName());

				authorDTOList.add(authorDTO);
			}

			bookDTO.setAuthors(authorDTOList);
		}
		return bookDTO;

	}

	public Book createBook(Book book) {

		// validation
		List<Error> error = bookValidator.validateCreateBook(book);
		// if not success
		if (error.size() > 0) {
			throw new BadRequestException("Bad request", error);
		}

		// success
		return bookRepository.save(book);
	}

	public Book updateBook(Book incomingBook) {
		return bookRepository.save(incomingBook);
	}

	public String deleteById(Integer bookId) {
		bookRepository.deleteById(bookId);

		return "Deleted Successfully";
	}

	// raw query - get books
	public List<Book> getBooksByRawQuery(Set<Integer> yop) {

		// APIResponse apiResponse = new APIResponse();

		// db call List<Book> bookList =
		return bookRepository.findAllBooksByYearOfPublicationIn(yop);

		// return bookList;
		// set data
		//		BookData bookData = new BookData();
		//		bookData.setBooks(bookList);
		//
		//		// set api response
		//		apiResponse.setData(bookData);
		//
		//		return apiResponse;
	}

	public APIResponse getBookByQueryDsl(Integer year) {
		System.out.println("query dsl service ");
		APIResponse apiResponse = new APIResponse();

		//List<Book> bookList = bookRepository.getAllByQueryDsl(year);
		 List<BookQueryDslDTO> bookQueryDslDTOS = bookRepository.getAllBooksByQuerDslDto(year);
		// System.out.println("all datad query dsl: "+bookList.toString());
		apiResponse.setData(bookQueryDslDTOS);
		return apiResponse;
	}

}
