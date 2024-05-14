package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository <ProductDetails,Integer> {
}
