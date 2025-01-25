package it.unina.authexample.domain.service;

import it.unina.authexample.domain.model.User;
import it.unina.authexample.infrastructure.repository.InMemUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private final InMemUserRepository repository;

  public UserDetailsService(InMemUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) {

    User user = repository.findByEmail(email).orElseThrow(() ->
        new RuntimeException(String.format("User does not exist, email: %s", email)));

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.email())
        .password(user.password())
        .build();
  }
}
