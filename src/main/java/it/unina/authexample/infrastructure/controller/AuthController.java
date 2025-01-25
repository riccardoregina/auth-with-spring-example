
package it.unina.authexample.infrastructure.controller;

import it.unina.authexample.infrastructure.controller.dto.LoginRequest;
import it.unina.authexample.infrastructure.controller.dto.LoginResponse;
import it.unina.authexample.infrastructure.controller.dto.SignupRequest;
import it.unina.authexample.infrastructure.util.JwtUtil;
import it.unina.authexample.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  public AuthController(AuthenticationManager authenticationManager, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<Void> signup(@RequestBody SignupRequest requestDto) {
    userService.signup(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping(value = "/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
    } catch (BadCredentialsException e) {
      throw e;
    }

    String token = JwtUtil.generateToken(request.email());
    return ResponseEntity.ok(new LoginResponse(request.email(), token));
  }

}