package com.ecom.ProdService.service;

import com.ecom.ProdService.entity.Product;
import com.ecom.ProdService.model.ProductRequest;
import com.ecom.ProdService.model.ProductResponse;
import com.ecom.ProdService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("adding product..");

        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product created");

        return product.getProductId();

    }

    @Override
    public ProductResponse getProductById(long productId) {

        log.info("Get the product info of {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Product with given id not exist"));

        ProductResponse productResponse = new ProductResponse();

        copyProperties(product , productResponse);

        return productResponse;


    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("reducing quantity {} for id :{}" , quantity , productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Product with given id not exist"));

        if (product.getQuantity()<quantity){
            throw new RuntimeException("not suffiencint quantity");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        log.info("product updated successfully");

    }
}
