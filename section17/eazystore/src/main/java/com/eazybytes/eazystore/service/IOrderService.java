package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.OrderRequestDTO;

public interface IOrderService {

  void createOrder(OrderRequestDTO orderRequestDTO);
}
