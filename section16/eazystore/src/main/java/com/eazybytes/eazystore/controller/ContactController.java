package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.ContactRequestDTO;
import com.eazybytes.eazystore.dto.ProductDTO;
import com.eazybytes.eazystore.service.IContactService;
import com.eazybytes.eazystore.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contacts")
public class ContactController {
  private final IContactService iContactService;

  @PostMapping
  public ResponseEntity<String> saveContact(@Valid @RequestBody ContactRequestDTO contactRequestDTO) {
    iContactService.saveContact(contactRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body("Request Processed successfully...");
  }
}
