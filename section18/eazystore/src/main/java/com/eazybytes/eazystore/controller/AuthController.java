package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.*;
import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.entity.Roles;
import com.eazybytes.eazystore.repository.CustomerRepository;
import com.eazybytes.eazystore.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;
  private final CustomerRepository customerRepository;
  private final CompromisedPasswordChecker compromisedPasswordChecker;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO loginRequestDTO){
    try{
      // no need to call the getter method since using record method for the loginRequestDTO
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
          (loginRequestDTO.username(), loginRequestDTO.password()));
      UserDTO userDTO = new UserDTO();
      var loggedInUser = (Customer) authentication.getPrincipal();
      BeanUtils.copyProperties(loggedInUser, userDTO);
      userDTO.setRoles(authentication.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")
      ));
      if (loggedInUser.getAddress() != null) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(loggedInUser.getAddress(), addressDTO);
        userDTO.setAddress(addressDTO);
      }
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
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
    CompromisedPasswordDecision decision = compromisedPasswordChecker.check(registerRequestDTO.getPassword());
    if(decision.isCompromised()){
      return new ResponseEntity<>(Map.of("password", "choose a strong password"), HttpStatus.BAD_REQUEST);
    }
    Optional<Customer>  existingCustomer = customerRepository.findByEmailOrMobileNumber(registerRequestDTO.getEmail(),
        registerRequestDTO.getMobileNumber());
    if(existingCustomer.isPresent()){
      Map<String, String> errors = new HashMap<>();
      Customer customer = existingCustomer.get();
      if(customer.getEmail().equalsIgnoreCase(registerRequestDTO.getEmail())){
        errors.put("email", "Email is already registered");
      }
      if(customer.getMobileNumber().equals(registerRequestDTO.getMobileNumber())){
        errors.put("mobileNumber", "Mobile number is already registered");
      }
      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    Customer customer = new Customer();
    BeanUtils.copyProperties(registerRequestDTO, customer);
    customer.setPasswordHash(passwordEncoder.encode(registerRequestDTO.getPassword()));
    Roles roles = new Roles();
    roles.setName("ROLE_USER");
    customer.setRoles(Set.of(roles));
    customerRepository.save(customer);
    return new ResponseEntity<>("Registration Successful", HttpStatus.CREATED);
  }

  private ResponseEntity<LoginResponseDTO> buildErrorResponse(HttpStatus status, String message){
    return new ResponseEntity<>(new LoginResponseDTO(message, null, null), status);
  }
}
