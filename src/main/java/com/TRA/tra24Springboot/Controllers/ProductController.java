package com.TRA.tra24Springboot.Controllers;


import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @PostMapping("deleteById")
    public <T> ResponseEntity<T> deleteProductById(@RequestParam Integer productId ) throws Exception{
        try {
            String result = productServices.deleteProductById(productId);
            return(ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e)
        {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting faild! "+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @PutMapping("update")
    public String updateProduct(@RequestParam Integer id, @RequestParam Integer quantity) {
        return productServices.updateProductQuantity(id, quantity);
    }

    @GetMapping("get")
    public <T> ResponseEntity<T> getProducts(){

        return new ResponseEntity(productServices.getProduct(), HttpStatus.OK) ;
    }

    @GetMapping("getDto")
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
