package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.ContactRequestDTO;
import com.eazybytes.eazystore.dto.ProductDTO;
import com.eazybytes.eazystore.service.IContactService;
import com.eazybytes.eazystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contacts")
public class ContactController {
  private final IContactService iContactService;

  @PostMapping
  public String saveContact(@RequestBody ContactRequestDTO contactRequestDTO) throws InterruptedException {
    boolean isSaved = iContactService.saveContact(contactRequestDTO);
    if(isSaved){
      return "Request Processed successfully...";
    }
    return "An error occurred, Please try again...";
  }
}
