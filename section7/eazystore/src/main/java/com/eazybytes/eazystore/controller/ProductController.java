package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.ProductDTO;
import com.eazybytes.eazystore.entity.Product;
import com.eazybytes.eazystore.repository.ProductRepository;
import com.eazybytes.eazystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {
  private final IProductService iProductService;

  @GetMapping
  public List<ProductDTO> getProducts(){
    List<ProductDTO> productList = iProductService.getProducts();
    return productList;
  }
}
