package com.gritgary.demo.Product.queryhandlers;

import com.gritgary.demo.Product.Model.Product;
import com.gritgary.demo.Product.Model.ProductDTO;
import com.gritgary.demo.Product.ProductRepository;
import com.gritgary.demo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDTO>> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<ProductDTO> productDTOs = productRepository
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();

        return ResponseEntity.ok(productDTOs);
    }
}
