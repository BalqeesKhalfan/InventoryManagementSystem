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
  /**@GetMapping("getByName")
    public Product getProductByName(@RequestParam String productName){
        return productServices.getProductByName(productName);
  }**/
  @GetMapping("getByName")
  public List<Product> getProductsByName(@RequestParam String productName) {
      return productServices.getProductsByName(productName);
  }

}
