package it.unina.authexample.controller.dto;

public record SignupRequest(
    String name,

    String email,

    String password) {

}
