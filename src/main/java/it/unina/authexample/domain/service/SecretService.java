package it.unina.authexample.domain.service;

import it.unina.authexample.domain.model.Secret;
import org.springframework.stereotype.Service;

@Service
public class SecretService {
    public Secret getSecret() {
        return new Secret("Forza Napoli");
    }
}
