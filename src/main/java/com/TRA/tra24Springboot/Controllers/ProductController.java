package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.ContactDetailsDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import com.TRA.tra24Springboot.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {


     @Autowired
    ProductServices productServices;

    @PostMapping("add")
    public Product addProduct(@RequestBody Product product){

        return productServices.addProduct(product);
    }
    @PostMapping("delete")
    public String deleteProduct(@RequestParam String productName ){
       productServices.deleteProduct(productName);
       return "Success";
    }
    @PostMapping("deleteId")
    public String deleteProductById(@RequestParam Integer productId ){
        productServices.deleteProductById(productId);
        return "Success";
    }


    @PutMapping("update")
    public String updateProduct(@RequestParam Integer id, @RequestParam Integer quantity) {
        return productServices.updateProductQuantity(id, quantity);
    }

    @GetMapping("get")
    public List<ProductDTO> getProduct(){

        return productServices.getProduct();
    }
   @GetMapping("getById")
    public Product getProductById(@RequestParam Integer productId){
        return productServices.getProductById(productId);
  }
  @GetMapping("getByName")
  public List<Product> getProductsByName(@RequestParam String productName) {
      return productServices.getProductsByName(productName);
  }
    @GetMapping("getByColor")
    public List<Product> getProductsByColor(@RequestParam String color) {
        return productServices.getProductsByColor(color);
    }
    @GetMapping("getByPrice")
    public List<Product> getProductsByPrice(@RequestParam Double price) {
        return productServices.getProductsByPrice(price);
    }
    @GetMapping("getByCountry")
    public List<Product> getProductByCountry(@RequestParam String country) {
        return productServices.getProductByCountry(country);
    }
    @GetMapping("getBySize")
    public List<Product> getProductBySize(@RequestParam String size) {
        return productServices.getProductBySize(size);
    }
    @GetMapping("getByCategory")
    public List<Product> getProductByCategory(@RequestParam String category) {
        return productServices.getProductByCategory(category);
    }

    @GetMapping("getByIsActive")
    public List<Product> getProductByIsActive(@RequestParam Boolean isActive) {
        return productServices.getProductByIsActive(isActive);
    }

}
