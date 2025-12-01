package com.eazybytes.eazystore.service.impl;

import com.eazybytes.eazystore.dto.PaymentIntentRequestDTO;
import com.eazybytes.eazystore.dto.PaymentIntentResponseDTO;
import com.eazybytes.eazystore.service.IPaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {

  @Override
  public PaymentIntentResponseDTO createPaymentIntent(PaymentIntentRequestDTO requestDTO) {
    try {
      PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
        .setAmount(requestDTO.amount())
        .setCurrency(requestDTO.currency())
        .addPaymentMethodType("card")
        .build();
      PaymentIntent paymentIntent = PaymentIntent.create(params);
      return new PaymentIntentResponseDTO(paymentIntent.getClientSecret());
    } catch (StripeException e) {
      throw new RuntimeException("Failed to create payment intent",e);
    }
  }
}
