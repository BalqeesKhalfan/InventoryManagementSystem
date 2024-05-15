package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {
   /* Integer productId;
    String productName;
    Double price;*/
    ProductDetailsDTO productDetailsDTO;
    public static ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductDetailsDTO(ProductDetailsDTO.convertToDTO(product.getProductDetails()));

        return productDTO;
    }
    public static List<ProductDTO> convertToDTO(List<Product> productList) {
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product productObjectFromDatabase: productList) {

            ProductDTO dto = convertToDTO(productObjectFromDatabase);
            productDTOS.add(dto);
        }
        return productDTOS;
    }


}
