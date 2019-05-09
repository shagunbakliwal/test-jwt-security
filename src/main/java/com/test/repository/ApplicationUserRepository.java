package com.test.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.domain.ApplicationUser;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {
	ApplicationUser findByUsername(String username);
}