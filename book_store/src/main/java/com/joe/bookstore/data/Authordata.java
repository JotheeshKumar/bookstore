package com.joe.bookstore.data;

import java.util.List;

import com.joe.bookstore.commom.PaginationMeta;
import com.joe.bookstore.entity.Author;

public class Authordata {
	List<Author> author;
	
	PaginationMeta meta;

	public PaginationMeta getMeta() {
		return meta;
	}

	public void setMeta(PaginationMeta meta) {
		this.meta = meta;
	}

	public List<Author> getAuthor() {
		return author;
	}

	public void setAuthor(List<Author> author) {
		this.author = author;
	}
}
