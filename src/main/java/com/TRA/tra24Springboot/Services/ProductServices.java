package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailsServices productDetailsServices;

    public Product addProduct(Product product){


        ProductDetails productDetails = productDetailsServices.addProductDetails(product.getProductDetails());
        /**ProductDetails productDetails = productDetailsServices.addProductDetails(product.getProductDetails());
       **/
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics Devices ");
         product.setQuantity(78);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        return productRepository.save(product);

    }
    public String deleteProduct(String productName){
          Product productFromDb = productRepository.getByProductName(productName);
          productFromDb.setIsActive(Boolean.FALSE);
          productRepository.save(productFromDb);
        return "Success";
    }
    public String deleteProductById(Integer productId) throws Exception{
       try {
           Product product =productRepository.getProductById(productId);
           if (product == null){
               throw new Exception("Product With ID : "+ productId + "is not found");
           }
           product.setIsActive(Boolean.FALSE);
           System.out.println(product.toString());
           productRepository.save(product);
           return "Product Deleted Successfully";

       }catch (Exception e){
           throw  new Exception("Failed to delete Product : "+ e.getMessage());
       }
    }

    public String updateProductQuantity(Integer productId, Integer quantity) throws Exception{
        try {
            Product productFromDb = productRepository.getProductById(productId);
            if (productFromDb == null) {
                throw new Exception("Product with ID: " + productId + " is not found.");
            }

            productFromDb.setQuantity(quantity);
            productFromDb.setUpdatedDate(new Date());

            productRepository.save(productFromDb);
            return "Updated Successfully";
        } catch (Exception e) {
            throw new Exception("Failed to update product quantity: " + e.getMessage(), e);
        }
    }
    public Product updateProduct(Product product)
    {
        return productRepository.save(product);
    }

    public List<ProductDTO> getProduct(){
        List <Product> products = productRepository.findAll();

        return ProductDTO.convertToDTO(products);
    }
   public Product getProductById(Integer productId)throws Exception {
       try {
           Product product = productRepository.getProductById(productId);
           if (product == null) {
               throw new Exception("Product with ID: " + productId + " is not found.");
           }
           return product;
       } catch (Exception e) {
           throw new Exception("Failed to retrieve product: " + e.getMessage(), e);
       }
   }
   public List<Product> getProductsByName(String productName)  throws Exception{
       try {
           List<Product> products = productRepository.findByProductName(productName);
           if (products.isEmpty()) {
               throw new Exception("No products found with name: " + productName);
           }
           return products;
       } catch (Exception e) {
           throw new Exception("Failed to retrieve products by name: " + e.getMessage(), e);
       }
   }
    public List<Product> getProductsByColor(String color) throws Exception{

        try {
            List<Product> products = productRepository.findByProductcolor(color);
            if (products.isEmpty()) {
                throw new Exception("No products found with color: " + color);
            }
            return products;

        }catch (Exception e){
            throw new Exception("Failed to retrieve products With color: " + e.getMessage(), e);
        }

    }

    public List<Product> getProductsByPrice(Double price) {
        return productRepository.findByProductPrice(price);
    }
    public List<Product>getProductByCountry(String country) {
        return productRepository.findByProductByCountry(country);
    }
    public List<Product>getProductBySize(String size) {
        return productRepository.findByProductBySize(size);
    }
    public List<Product>getProductByCategory(String category) {
        return productRepository.findByProductByCategory(category);
    }
    public List<Product>getProductByIsActive(Boolean isActive) {
        return productRepository.findByProductByIsActive(isActive);
    }
}
