package com.eazybytes.eazystore.service.impl;

import com.eazybytes.eazystore.dto.ContactRequestDTO;
import com.eazybytes.eazystore.entity.Contact;
import com.eazybytes.eazystore.repository.ContactRepository;
import com.eazybytes.eazystore.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


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
  private Contact transformToEntity(ContactRequestDTO contactRequestDTO){
    Contact contact = new Contact();
    BeanUtils.copyProperties(contactRequestDTO, contact);
    return  contact;
  }
}
