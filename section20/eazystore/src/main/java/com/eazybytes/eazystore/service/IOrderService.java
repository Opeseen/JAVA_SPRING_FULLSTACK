package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.OrderRequestDTO;
import com.eazybytes.eazystore.dto.OrderResponseDTO;

import java.util.List;

public interface IOrderService {

  void createOrder(OrderRequestDTO orderRequestDTO);
  List<OrderResponseDTO> getCustomerOrder();
  List<OrderResponseDTO> getAllPendingOrders();
  void updatedOrderStatus(Long orderId, String orderStatus);
}
