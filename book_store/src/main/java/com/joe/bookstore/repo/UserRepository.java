package com.joe.bookstore.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joe.bookstore.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);

}
