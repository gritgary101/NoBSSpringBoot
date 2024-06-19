package com.gritgary.demo.Product.commandhandlers;

import com.gritgary.demo.Command;
import com.gritgary.demo.Exceptions.ProductNotFoundException;
import com.gritgary.demo.Product.Model.Product;
import com.gritgary.demo.Product.Model.ProductDTO;
import com.gritgary.demo.Product.Model.UpdateProductCommand;
import com.gritgary.demo.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    @CachePut(value = "productCache", key = "#command.getId()")
    public ResponseEntity execute(UpdateProductCommand command) {
        Optional<Product> optionalProduct = productRepository.findById(command.getId());
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }
        Product product = command.getProduct();
        product.setId(command.getId());
        productRepository.save(product);
        return ResponseEntity.ok(new ProductDTO(product));
    }
}
