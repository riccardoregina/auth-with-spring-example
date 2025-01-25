package it.unina.authexample.infrastructure.controller.dto;

public record SignupRequest(
    String name,

    String email,

    String password) {

}
