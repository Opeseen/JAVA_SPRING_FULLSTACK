package com.eazybytes.eazystore.dto;

import java.math.BigDecimal;

public record OrderItemResponseDTO(String productName, Integer quantity,
                                   BigDecimal price, String imageUrl) {
}
