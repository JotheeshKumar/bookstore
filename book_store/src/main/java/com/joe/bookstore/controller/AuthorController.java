package com.joe.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joe.bookstore.commom.APIResponse;
import com.joe.bookstore.dto.RequestMeta;
import com.joe.bookstore.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private RequestMeta requestMeta;
	
	@GetMapping("/all")
	public APIResponse getAllAuthors( Pageable pageable) {
		
		System.out.println("data check ----------------: "+requestMeta.getEmailId());
		
		//@SortDefault(sort = "id", direction = Direction.DESC)
		return authorService.getAuthors(pageable); 
		
	}
	
	@GetMapping("/namedAll")
	public APIResponse getAllAuthorsWithNamedQuery(Pageable pageable) {
		
		return authorService.getAuthorsWithNamedQuery(pageable); 
		
	}
	
}
