package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.PaymentIntentRequestDTO;
import com.eazybytes.eazystore.dto.PaymentIntentResponseDTO;
import com.eazybytes.eazystore.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

  private final IPaymentService iPaymentService;

  @PostMapping("/create-payment-intent")
  public ResponseEntity<PaymentIntentResponseDTO> createPaymentIntent(@RequestBody PaymentIntentRequestDTO paymentRequest ){
    PaymentIntentResponseDTO response = iPaymentService.createPaymentIntent(paymentRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
