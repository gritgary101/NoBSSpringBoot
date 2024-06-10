package com.gritgary.demo.Product.queryhandlers;

import com.gritgary.demo.Exceptions.ProductNotFoundException;
import com.gritgary.demo.Product.Model.Product;
import com.gritgary.demo.Product.Model.ProductDTO;
import com.gritgary.demo.Product.ProductRepository;
import com.gritgary.demo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, ProductDTO> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            //throw an exception
            throw new ProductNotFoundException();
        }
        return ResponseEntity.ok(new ProductDTO(product.get()));
    }
}
