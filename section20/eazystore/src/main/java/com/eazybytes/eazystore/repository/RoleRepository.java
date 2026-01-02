package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.Product;
import com.eazybytes.eazystore.entity.Roles;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Optional
public interface RoleRepository extends JpaRepository<Roles, Long> {
  @Cacheable("roles")
  Optional<Roles> findByName(String name);
}
