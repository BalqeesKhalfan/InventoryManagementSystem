package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.ProductDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDetailsDTO {
    Integer Id;
    String productName;
    String size;
    String color;
    Double price;

    public static ProductDetailsDTO convertToDTO(ProductDetails productDetails) {
        ProductDetailsDTO productDetailsDTO= new ProductDetailsDTO();
        productDetailsDTO.setId(productDetails.getId());
        productDetailsDTO.setProductName(productDetails.getName());
        productDetailsDTO.setSize(productDetails.getSize());
        productDetailsDTO.setColor(productDetails.getColor());
        productDetailsDTO.setPrice(productDetails.getPrice());

        return productDetailsDTO;
    }
    public static List<ProductDetailsDTO> convertToDTO(List<ProductDetails> productDetailsList) {
        List<ProductDetailsDTO> productDetailsDTOs = new ArrayList<>();

        for (ProductDetails productDetailsObjectFromDatabase: productDetailsList) {

            ProductDetailsDTO dto = convertToDTO(productDetailsObjectFromDatabase);
            productDetailsDTOs.add(dto);
        }
        return productDetailsDTOs;
    }


}
