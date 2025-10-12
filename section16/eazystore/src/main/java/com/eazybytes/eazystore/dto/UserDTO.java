package com.eazybytes.eazystore.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@ToString
public class UserDTO {
  private Long userId;
  private String name;
  private String email;
  private BigDecimal mobileNumber;
  private String roles;
}
