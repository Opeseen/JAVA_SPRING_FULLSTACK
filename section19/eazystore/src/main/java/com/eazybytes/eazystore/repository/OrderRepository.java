package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Optional
public interface OrderRepository extends JpaRepository<Order, Long> {
  // Fetch orders for a customer, sorted by creation date in desc order
  List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);
  List<Order> findByOrderStatus(String orderStatus);

  // custom queries
  @Query("SELECT o FROM Order o WHERE o.customer=:customer ORDER BY o.createdAt DESC")
  List<Order> findOrderByCustomer(@Param("customer") Customer customer);

  @Query("SELECT o FROM Order o WHERE o.orderStatus=?1")
  List<Order> findOrdersByStatus(String orderStatus);

  @Query(value = "SELECT * FROM orders o WHERE o.customer_id=:customerId ORDER BY o.created_at DESC",
      nativeQuery = true)
  List<Order> findOrderByCustomerWithNativeQuery(@Param("customerId") Long customerId);

  @Query(value = "SELECT * FROM orders o WHERE o.order_status=?1", nativeQuery = true)
  List<Order> findOrdersByStatusWithNativeQuery(String orderStatus);
}
