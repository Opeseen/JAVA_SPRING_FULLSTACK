package com.eazybytes.eazystore.service.impl;

import com.eazybytes.eazystore.constants.ApplicationConstants;
import com.eazybytes.eazystore.dto.ContactRequestDTO;
import com.eazybytes.eazystore.dto.ContactResponseDTO;
import com.eazybytes.eazystore.entity.Contact;
import com.eazybytes.eazystore.exception.ResourceNotFoundException;
import com.eazybytes.eazystore.repository.ContactRepository;
import com.eazybytes.eazystore.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {
  private final ContactRepository contactRepository;

  @Override
  public boolean saveContact(ContactRequestDTO contactRequestDTO) {
    Contact contact = transformToEntity(contactRequestDTO);
    contactRepository.save(contact);
    return true;
  }

  @Override
  public List<ContactResponseDTO> getAllOpenMessages() {
    List<Contact> contacts = contactRepository.findByStatus(ApplicationConstants.OPEN_MESSAGE);
    return contacts.stream().map(this::mapToContactResponseDTO).collect(Collectors.toList());
  }

  @Override
  public void updateMessageStatus(Long contactId, String status) {
    Contact contact = contactRepository.findById(contactId).orElseThrow(
        () -> new ResourceNotFoundException("Contact", "contactId", contactId.toString())
    );
    contact.setStatus(status);
    contactRepository.save(contact);
  }

  private ContactResponseDTO mapToContactResponseDTO(Contact contact) {
    ContactResponseDTO responseDTO = new ContactResponseDTO(
        contact.getContactId(),
        contact.getName(),
        contact.getEmail(),
        contact.getMobileNumber(),
        contact.getMessage(),
        contact.getStatus()
    );
    return responseDTO;
  }

  private Contact transformToEntity(ContactRequestDTO contactRequestDTO){
    Contact contact = new Contact();
    BeanUtils.copyProperties(contactRequestDTO, contact);
    contact.setStatus(ApplicationConstants.OPEN_MESSAGE);
    return  contact;
  }
}
