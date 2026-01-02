package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.ProductDTO;
import com.eazybytes.eazystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<List<ProductDTO>> getProducts(){
    System.out.println("Hello Products API");
    List<ProductDTO> productList;
    productList = iProductService.getProducts();
    return ResponseEntity.status(HttpStatus.OK).body(productList) ;
  }
}
