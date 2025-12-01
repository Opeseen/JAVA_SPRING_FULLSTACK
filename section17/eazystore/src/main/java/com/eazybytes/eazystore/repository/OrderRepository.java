package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Optional
public interface OrderRepository extends JpaRepository<Order, Long> {
}
