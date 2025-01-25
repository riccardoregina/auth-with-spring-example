package it.unina.authexample.infrastructure.repository;

import it.unina.authexample.domain.model.User;
import it.unina.authexample.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public void add(User user) {
        if(!users.containsKey(user.email())) {
            users.put(user.email(), user);
        }
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }
}
