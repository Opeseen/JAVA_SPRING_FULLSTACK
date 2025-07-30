package com.eazybytes.eazystore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter @Setter
@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, name = "product_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private Integer popularity;

  @Column(name = "image_url")
  private String imageUrl;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(nullable = false, name = "created_at")
  private Instant createdAt;

  @Column(nullable = false, name = "created_by")
  private String createdBy;

  @Column(name = "updated_at")
  @ColumnDefault("NULL")
  private Instant updatedAt;

  @Column(name = "updated_by")
  @ColumnDefault("NULL")
  private String updatedBy;

}
