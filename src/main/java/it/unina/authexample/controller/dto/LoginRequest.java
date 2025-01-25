package it.unina.authexample.controller.dto;

public record LoginRequest(
    String email,
    String password) {

}
