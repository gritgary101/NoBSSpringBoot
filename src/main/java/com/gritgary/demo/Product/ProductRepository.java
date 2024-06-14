package com.gritgary.demo.Product;

import com.gritgary.demo.Product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Query write our own SQL statement (直接在这里写SQL）
    @Query("select p from Product p where p.description like %:description%")
    List<Product> customQueryMethod(@Param(value = "description") String description);

    //spring Data JPA to have spring generate it for us
    List<Product> findByDescriptionContaining(String keyword);

}
