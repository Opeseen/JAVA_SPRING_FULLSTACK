package com.eazybytes.eazystore.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ContactRequestDTO {
  private String name;
  private String email;
  private String mobileNumber;
  private String message;
}
