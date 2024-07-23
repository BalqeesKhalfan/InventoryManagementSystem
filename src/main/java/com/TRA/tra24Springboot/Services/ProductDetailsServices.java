package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Logging.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductDetailsServices {
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @TrackExecutionTime
    public ProductDetails addProductDetails(ProductDetails productDetails){
        productDetails = new ProductDetails();
        productDetails.setName("Mac device");
        productDetails.setColor("Black");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails.setIsActive(Boolean.TRUE);
        productDetails.setCreatedDate(new Date());
        productDetails.setExpiryDate(new Date());

        return productDetailsRepository.save(productDetails);

    }

}
