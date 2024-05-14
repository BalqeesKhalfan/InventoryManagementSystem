package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository <Product,Integer> {
    @Query("SELECT p from Product p WHERE p.productDetails.name =:productName")
    Product getByProductName( @Param("productName") String productName);

    @Query("SELECT p from Product p WHERE p.id =:productId")
    Product getProductById( @Param("productId") Integer ProductId );


}
