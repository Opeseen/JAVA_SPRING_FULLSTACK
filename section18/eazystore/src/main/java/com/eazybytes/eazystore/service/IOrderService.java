package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.OrderRequestDTO;
import com.eazybytes.eazystore.dto.OrderResponseDTO;
import com.eazybytes.eazystore.entity.Order;

import java.util.List;

public interface IOrderService {

  void createOrder(OrderRequestDTO orderRequestDTO);
  List<OrderResponseDTO> getCustomerOrder();
  List<OrderResponseDTO> getAllPendingOrders();
  Order updatedOrderStatus(Long orderId, String orderStatus);
}
