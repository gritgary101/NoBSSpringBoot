package com.gritgary.demo.Product.commandhandlers;

import com.gritgary.demo.Command;
import com.gritgary.demo.Exceptions.ProductNotValidException;
import com.gritgary.demo.Product.Model.Product;
import com.gritgary.demo.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandHandler implements Command<Product, ResponseEntity> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity execute(Product product) {

        validateProduct(product);

        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void validateProduct(Product product){
        if(StringUtils.isBlank(product.getName())){
            throw new ProductNotValidException("Product Name cannot be empty.");
        }

        if (StringUtils.isBlank(product.getDescription())){
            throw new ProductNotValidException("Description cannot be empty.");
        }

        if(product.getPrice() <= 0.0){
            throw new ProductNotValidException("Price cannot be negative");
        }

        if (product.getQuantity() <= 0){
            throw new ProductNotValidException("Quantity cannot be negative");
        }
    }
}
