package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.LoginRequestDTO;
import com.eazybytes.eazystore.dto.LoginResponseDTO;
import com.eazybytes.eazystore.dto.RegisterRequestDTO;
import com.eazybytes.eazystore.dto.UserDTO;
import com.eazybytes.eazystore.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;
  private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO loginRequestDTO){
    try{
      // no need to call the getter method since using record method for the loginRequestDTO
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
          (loginRequestDTO.username(), loginRequestDTO.password()));
      UserDTO userDTO = new UserDTO();
      var loggedInUser = (User) authentication.getPrincipal();
      userDTO.setName(loggedInUser.getUsername());
      String jwtToken = jwtUtil.generateJwtToken(authentication);
      return new ResponseEntity<>(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(),
          userDTO, jwtToken ), HttpStatus.OK);
    }catch (BadCredentialsException exception){
      return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password");

    }catch (AuthenticationException exception){
      return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication failed");
    }catch (Exception exception){
      return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

  }
  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
    inMemoryUserDetailsManager.createUser(new User(registerRequestDTO.getEmail(),
        passwordEncoder.encode(registerRequestDTO.getPassword()),
        List.of(new SimpleGrantedAuthority("USER"))));
    return new ResponseEntity<>("Registration Successful", HttpStatus.CREATED);
  }

  private ResponseEntity<LoginResponseDTO> buildErrorResponse(HttpStatus status, String message){
    return new ResponseEntity<>(new LoginResponseDTO(message, null, null), status);
  }
}
