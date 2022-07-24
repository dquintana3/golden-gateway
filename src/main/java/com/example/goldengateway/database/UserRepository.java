package com.example.goldengateway.database;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    @Override
    Optional<User> findById(String username);
}
