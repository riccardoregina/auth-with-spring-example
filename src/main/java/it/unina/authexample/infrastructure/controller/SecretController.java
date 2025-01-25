package it.unina.authexample.infrastructure.controller;

import it.unina.authexample.infrastructure.controller.dto.SecretResponse;
import it.unina.authexample.domain.service.SecretService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/services", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecretController {
    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @GetMapping(value = "/secret")
    public ResponseEntity<SecretResponse> secret() {
        var secret = secretService.getSecret();
        return ResponseEntity.ok(new SecretResponse(secret.secret(), "Be happy with this."));
    }
}
