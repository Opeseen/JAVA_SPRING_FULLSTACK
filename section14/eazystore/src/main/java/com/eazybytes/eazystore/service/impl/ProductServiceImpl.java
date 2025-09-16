package com.eazybytes.eazystore.service.impl;

import com.eazybytes.eazystore.dto.ProductDTO;
import com.eazybytes.eazystore.entity.Product;
import com.eazybytes.eazystore.repository.ProductRepository;
import com.eazybytes.eazystore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
  private final ProductRepository productRepository;
  @Override
  public List<ProductDTO> getProducts() {
    return productRepository.findAll().stream()
        .map(this::transformToDTO).collect(Collectors.toList());
  }

  private ProductDTO transformToDTO(Product product){
    ProductDTO productDTO = new ProductDTO();
    BeanUtils.copyProperties(product, productDTO);
    productDTO.setProductId(product.getId());
    return productDTO;
  }
}
