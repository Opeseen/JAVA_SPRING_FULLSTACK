package com.eazybytes.eazystore.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequestDTO(BigDecimal totalPrice, String paymentId, String paymentStatus, List<OrderItemDTO> items) {
}
