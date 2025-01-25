package it.unina.authexample.service;

import it.unina.authexample.controller.dto.SignupRequest;
import it.unina.authexample.model.User;
import it.unina.authexample.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    public void signup(SignupRequest request) {
        String email = request.email();
        Optional<User> existingUser = repository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException(String.format("User with the email address '%s' already exists.", email));
        }

        String hashedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.name(), email, hashedPassword);
        repository.add(user);
    }

}
