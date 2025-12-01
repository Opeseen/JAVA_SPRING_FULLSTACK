package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.OrderRequestDTO;
import com.eazybytes.eazystore.dto.OrderResponseDTO;
import com.eazybytes.eazystore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
  private final IOrderService iOrderService;

  @PostMapping
  public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequest){
    iOrderService.createOrder(orderRequest);
    return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<OrderResponseDTO>> loadCustomerOrder(){
    return new ResponseEntity<>(iOrderService.getCustomerOrder(), HttpStatus.OK);
  }
}
