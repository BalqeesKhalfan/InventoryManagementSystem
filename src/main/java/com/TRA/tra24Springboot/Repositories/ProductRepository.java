package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product,Integer> {
    @Query("SELECT p from Product p WHERE p.productDetails.name =:productName")
    Product getByProductName( @Param("productName") String productName);
    @Query("SELECT p from Product p WHERE p.productDetails.name =:productName")
    List<Product> findByProductName(@Param("productName") String productName);

    @Query("SELECT p from Product p WHERE p.productDetails.color =:color")
    List<Product> findByProductcolor(@Param("color") String color);
    @Query("SELECT p from Product p WHERE p.productDetails.price =:price")
    List<Product> findByProductPrice(@Param("price") Double price);

    @Query("SELECT p from Product p WHERE p.id =:productId")
    Product getProductById( @Param("productId") Integer ProductId );
    @Query("SELECT p from Product p WHERE p.productDetails.countryOfOrigin =:country")
    List<Product> findByProductByCountry( @Param("country") String country );

    @Query("SELECT p from Product p WHERE p.productDetails.size =:size")
    List<Product> findByProductBySize( @Param("size") String size );

   /** @Query("SELECT p from Product p WHERE p.productDetails.productName =:productName")
    ProductDTO getBYProduct(@Param("productName") String productName);**/


}
