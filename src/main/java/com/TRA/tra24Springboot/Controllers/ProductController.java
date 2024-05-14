package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import com.TRA.tra24Springboot.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
   @Autowired
    ProductRepository productRepository;
   @Autowired
    ProductDetailsRepository productDetailsRepository;

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
  /**
    @PostMapping("delete/{id}")
    public String deleteProduct(@PathVariable Integer id){

            if(globalProduct.getId().equals(id)){
                globalProduct.setIsActive(Boolean.FALSE);
                System.out.println(globalProduct.toString());

        }
        return "Success!";
    }

    @PutMapping("update")
    public Product updateProduct(@RequestBody Product userProduct){


        ProductDetails pd = userProduct.getProductDetails();
        pd.setUpdatedDate(new Date());

        userProduct.setProductDetails(pd);
        userProduct.setUpdatedDate(new Date());

        globalProduct = userProduct;
        return productRepository.save(globalProduct);
    }
    @GetMapping("get")
    public  Product reportProduct(){
        return  productRepository.save(globalProduct);
    }**/
}
