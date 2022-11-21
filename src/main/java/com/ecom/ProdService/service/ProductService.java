package com.ecom.ProdService.service;

import com.ecom.ProdService.model.ProductRequest;
import com.ecom.ProdService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
