package com.joe.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Service;

import com.joe.bookstore.commom.APIResponse;
import com.joe.bookstore.commom.PaginationMeta;
import com.joe.bookstore.data.Authordata;
import com.joe.bookstore.entity.Author;
import com.joe.bookstore.repo.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public APIResponse getAuthors( Pageable pageable) {

		APIResponse apiResponse = new APIResponse();
		Authordata authorData = new Authordata();
		
		
		Page<Author> authorPage= 	authorRepository.findAll(pageable);
		PaginationMeta authorPaginationMeta = PaginationMeta.createPagination(authorPage);
		List<Author> AuthorContent = authorPage.getContent();
		authorData.setAuthor(AuthorContent);
		authorData.setMeta(authorPaginationMeta);
		
		apiResponse.setData(authorData);
		return apiResponse;
	}

	public APIResponse getAuthorsWithNamedQuery(Pageable pageable) {
		
		APIResponse apiResponse = new APIResponse();
		Authordata authorData = new Authordata();
		
		
		Page<Author> authorPage= 	authorRepository.findAllByOrderByIdDesc(pageable);
		PaginationMeta authorPaginationMeta = PaginationMeta.createPagination(authorPage);
		List<Author> AuthorContent = authorPage.getContent();
		authorData.setAuthor(AuthorContent);
		authorData.setMeta(authorPaginationMeta);
		
		apiResponse.setData(authorData);
		return apiResponse;
	}

}
