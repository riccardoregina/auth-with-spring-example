package it.unina.authexample.domain.repository;

import it.unina.authexample.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    void add(User user);
    Optional<User> findByEmail(String email);
}
