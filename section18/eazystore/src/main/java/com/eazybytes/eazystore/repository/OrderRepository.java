package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Optional
public interface OrderRepository extends JpaRepository<Order, Long> {
  // Fetch orders for a customer, sorted by creation date in desc order
  List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);
  List<Order> findByOrderStatus(String orderStatus);
}
