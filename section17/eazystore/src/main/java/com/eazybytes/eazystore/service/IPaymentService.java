package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.PaymentIntentRequestDTO;
import com.eazybytes.eazystore.dto.PaymentIntentResponseDTO;

public interface IPaymentService {
  PaymentIntentResponseDTO createPaymentIntent(PaymentIntentRequestDTO requestDTO);
}
