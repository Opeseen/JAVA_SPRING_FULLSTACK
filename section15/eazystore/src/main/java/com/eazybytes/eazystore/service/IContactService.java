package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.ContactRequestDTO;
import com.eazybytes.eazystore.dto.ProductDTO;

import java.util.List;

public interface IContactService {
  boolean saveContact(ContactRequestDTO contactRequestDTO);
}
