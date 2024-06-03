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
    public <T> ResponseEntity<T>updateProduct(@RequestParam Integer id, @RequestParam Integer quantity) {
        try{
            String result = productServices.updateProductQuantity(id,quantity);
            return (ResponseEntity<T>) new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return (ResponseEntity<T>) new ResponseEntity<>("updating Faild!"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("get")
    public <T>ResponseEntity<T> getProducts(){
        return new ResponseEntity(productServices.getProduct(),HttpStatus.OK);
    }

  /**  @GetMapping("getDto")
    public List<ProductDTO> getProduct(){

        return productServices.getProduct();
    }**/
   @GetMapping("getById")
   public ResponseEntity<?> getProductById(@RequestParam Integer productId) {
       try {
           Product product = productServices.getProductById(productId);
           return new ResponseEntity<>(product, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>("Retrieving product failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   @GetMapping("getByName")
    public ResponseEntity<?>  getProductsByName(@RequestParam String productName) {
       try {
           List<Product> products = productServices.getProductsByName(productName);
           return new ResponseEntity<>(products, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>("Retrieving products by name failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @GetMapping("getByColor")
    public ResponseEntity<?> getProductsByColor(@RequestParam String color) {
       try {
           List<Product> products = productServices.getProductsByColor(color);
           return  new ResponseEntity<>(products,HttpStatus.OK);
       }catch (Exception e ){
           return new ResponseEntity<>("Retrieving products by color failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
    @GetMapping("getByPrice")
    public ResponseEntity<?> getProductsByPrice(@RequestParam Double price) {
       try {
           List<Product> products = productServices.getProductsByPrice(price);
           return new ResponseEntity<>(products,HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>("Retrieving products by price failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
    @GetMapping("getByCountry")
    public ResponseEntity<?> getProductByCountry(@RequestParam String country) {
       try {
           List<Product> products = productServices.getProductByCountry(country);
           return  new ResponseEntity<>(products,HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>("Retrieving products by country failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

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
