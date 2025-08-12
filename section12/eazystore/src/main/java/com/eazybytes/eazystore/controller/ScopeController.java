package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.scopes.ApplicationScopeBean;
import com.eazybytes.eazystore.scopes.RequestScopeBean;
import com.eazybytes.eazystore.scopes.SessionScopeBean;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scope")
@RequiredArgsConstructor
public class ScopeController {

  private final RequestScopeBean requestScopeBean;
  private final SessionScopeBean sessionScopeBean;
  private final ApplicationScopeBean applicationScopeBean;

  @GetMapping("/request")
  public ResponseEntity<String> testRequestScope(){
    requestScopeBean.setUserName("John Doe");
    return new ResponseEntity<>(requestScopeBean.getUserName(), HttpStatus.OK);
  }

  @GetMapping("/session")
  public ResponseEntity<String> testSessionScope(){
    sessionScopeBean.setUserName("John Doe");
    return new ResponseEntity<>(sessionScopeBean.getUserName(), HttpStatus.OK);
  }

  @GetMapping("/application")
  public ResponseEntity<Integer> testApplicationScope(){
    applicationScopeBean.incrementVisitorCount();
    return new ResponseEntity<>(applicationScopeBean.getVisitorCount(), HttpStatus.OK);
  }

  @GetMapping("/test")
  public ResponseEntity<Integer> testScope(){
    return new ResponseEntity<>(applicationScopeBean.getVisitorCount(), HttpStatus.OK);
  }
}
