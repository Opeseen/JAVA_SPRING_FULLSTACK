package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.constants.ApplicationConstants;
import com.eazybytes.eazystore.dto.ContactResponseDTO;
import com.eazybytes.eazystore.dto.OrderResponseDTO;
import com.eazybytes.eazystore.dto.ResponseDTO;
import com.eazybytes.eazystore.entity.Order;
import com.eazybytes.eazystore.service.IContactService;
import com.eazybytes.eazystore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
  private final IOrderService iOrderService;
  private final IContactService iContactService;

  @GetMapping("/orders")
  public ResponseEntity<List<OrderResponseDTO>> getAllPendingOrders(){
    return new ResponseEntity<>(iOrderService.getAllPendingOrders(), HttpStatus.OK);
  }

  @PatchMapping("/orders/{orderId}/confirm")
  public ResponseEntity<ResponseDTO> confirmOrder(@PathVariable Long orderId){
    Order confirmedOrder = iOrderService.updatedOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CONFIRMED);
    return new ResponseEntity<>(
        new ResponseDTO("200","Order #" + confirmedOrder.getOrderId() + " has been approved."),
        HttpStatus.OK
    );
  }

  @PatchMapping("/orders/{orderId}/cancel")
  public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable Long orderId){
    Order cancelledOrder = iOrderService.updatedOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CANCELLED);
    return new ResponseEntity<>(
        new ResponseDTO("200", "Order #" + cancelledOrder.getOrderId() + " has been cancelled."),
        HttpStatus.OK
    );
  }

  @GetMapping("/messages")
  public ResponseEntity<List<ContactResponseDTO>> getAllOpenMessages(){
    return new ResponseEntity<>(iContactService.getAllOpenMessages(), HttpStatus.OK);
  }

  @PatchMapping("/messages/{contactId}/close")
  public ResponseEntity<ResponseDTO> closeMessage(@PathVariable Long contactId){
    iContactService.updateMessageStatus(contactId, ApplicationConstants.CLOSED_MESSAGE);
    return new ResponseEntity<>(new ResponseDTO("200", "Contact #" + contactId + " has been closed."), HttpStatus.OK);
  }
}
