package com.gritgary.demo.Product;

import com.gritgary.demo.Exceptions.ProductNotFoundException;
import com.gritgary.demo.Product.Model.Product;
import com.gritgary.demo.Product.Model.ProductDTO;
import com.gritgary.demo.Product.Model.UpdateProductCommand;
import com.gritgary.demo.Product.commandhandlers.CreateProductCommandHandler;
import com.gritgary.demo.Product.commandhandlers.DeleteProductCommandHandler;
import com.gritgary.demo.Product.commandhandlers.UpdateProductCommandHandler;
import com.gritgary.demo.Product.queryhandlers.GetAllProductsQueryHandler;
import com.gritgary.demo.Product.queryhandlers.GetProductQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GetAllProductsQueryHandler getAllProductsQueryHandler;
    @Autowired
    private GetProductQueryHandler getProductQueryHandler;
    @Autowired
    private CreateProductCommandHandler createProductCommandHandler;
    @Autowired
    private UpdateProductCommandHandler updateProductCommandHandler;
    @Autowired
    private DeleteProductCommandHandler deleteProductCommandHandler;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getAllProductsQueryHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        return getProductQueryHandler.execute(id);
    }

    @PostMapping
    public ResponseEntity creatProduct(@RequestBody Product product) {
        return createProductCommandHandler.execute(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        UpdateProductCommand command = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        return deleteProductCommandHandler.execute(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(value = "description") String description){
        return ResponseEntity.ok(productRepository.findByDescriptionContaining(description));
    }
}
