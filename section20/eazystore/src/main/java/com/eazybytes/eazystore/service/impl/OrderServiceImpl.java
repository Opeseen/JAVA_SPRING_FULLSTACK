package com.eazybytes.eazystore.service.impl;

import com.eazybytes.eazystore.constants.ApplicationConstants;
import com.eazybytes.eazystore.dto.OrderItemResponseDTO;
import com.eazybytes.eazystore.dto.OrderRequestDTO;
import com.eazybytes.eazystore.dto.OrderResponseDTO;
import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.entity.Order;
import com.eazybytes.eazystore.entity.OrderItem;
import com.eazybytes.eazystore.entity.Product;
import com.eazybytes.eazystore.exception.ResourceNotFoundException;
import com.eazybytes.eazystore.repository.OrderRepository;
import com.eazybytes.eazystore.repository.ProductRepository;
import com.eazybytes.eazystore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final ProfileServiceImpl profileService;

  @Override
  public void createOrder(OrderRequestDTO orderRequest) {
    Customer customer = profileService.getAuthenticatedCustomer();
    // Create Order
    Order order = new Order();
    order.setCustomer(customer);
    BeanUtils.copyProperties(orderRequest, order);
    order.setOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);
    // Map OrderItems
    List<OrderItem> orderItems = orderRequest.items().stream().map(item -> {
      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      Product product = productRepository.findById(item.productId())
          .orElseThrow(() -> new ResourceNotFoundException("Product", "ProductID",
              item.productId().toString()));
      orderItem.setProduct(product);
      orderItem.setQuantity(item.quantity());
      orderItem.setPrice(item.price());
      return orderItem;
    }).collect(Collectors.toList());
    order.setOrderItems(orderItems);
    orderRepository.save(order);

  }

  @Override
  public List<OrderResponseDTO> getCustomerOrder() {
    Customer customer = profileService.getAuthenticatedCustomer();
    List<Order> orders = orderRepository.findOrderByCustomerWithNativeQuery(customer.getCustomerId());
    return orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
  }

  @Override
  public List<OrderResponseDTO> getAllPendingOrders() {
    List<Order> orders = orderRepository.findOrdersByStatusWithNativeQuery(ApplicationConstants.ORDER_STATUS_CREATED);
    return orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
  }

  @Override
  public void updatedOrderStatus(Long orderId, String orderStatus){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    orderRepository.updateOrderStatus(orderId, orderStatus, email, Instant.now());
  }

  /**
   * Map Order entity to OrderResponseDto
   */
  private OrderResponseDTO mapToOrderResponseDTO(Order order) {
    // Map Order Items
    List<OrderItemResponseDTO> itemDTOs = order.getOrderItems().stream()
        .map(this::mapToOrderItemResponseDTO)
        .collect(Collectors.toList());
    return new OrderResponseDTO(order.getOrderId()
        , order.getOrderStatus(), order.getTotalPrice(), order.getCreatedAt().toString()
        , itemDTOs);
  }

  /**
   * Map OrderItem entity to OrderItemResponseDto
   */
  private OrderItemResponseDTO mapToOrderItemResponseDTO(OrderItem orderItem) {
    return new OrderItemResponseDTO(
        orderItem.getProduct().getName(), orderItem.getQuantity(),
        orderItem.getPrice(), orderItem.getProduct().getImageUrl());
  }
}
