package com.eazybytes.eazystore.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class ProductDTO {
  private Long productId;
  private String name;
  private String description;
  private BigDecimal price;
  private Integer popularity;
  private String imageUrl;
  private Instant createdAt;
}
