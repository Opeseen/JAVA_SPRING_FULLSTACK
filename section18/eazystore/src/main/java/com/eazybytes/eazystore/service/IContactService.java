package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.ContactRequestDTO;
import com.eazybytes.eazystore.dto.ContactResponseDTO;

import java.util.List;

public interface IContactService {
  boolean saveContact(ContactRequestDTO contactRequestDTO);
  List<ContactResponseDTO> getAllOpenMessages();
  void updateMessageStatus(Long contactId, String status);
}
