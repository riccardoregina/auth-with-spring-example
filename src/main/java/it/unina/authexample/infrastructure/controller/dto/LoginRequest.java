package it.unina.authexample.infrastructure.controller.dto;

public record LoginRequest(
    String email,
    String password) {

}
