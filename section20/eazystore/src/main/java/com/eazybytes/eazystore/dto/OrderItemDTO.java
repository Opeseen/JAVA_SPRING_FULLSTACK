package com.eazybytes.eazystore.dto;

import java.math.BigDecimal;

public record OrderItemDTO(Long productId, Integer quantity, BigDecimal price) {
}
