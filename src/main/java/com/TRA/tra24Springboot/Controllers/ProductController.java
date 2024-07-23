package com.TRA.tra24Springboot.Controllers;


import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Logging.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Services.MailingService;
import com.TRA.tra24Springboot.Services.ProductServices;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductServices productServices;

    @Autowired
    MailingService mailingService;
    @Autowired
    SlackService slackService;

    @PostMapping("add")
    @TrackExecutionTime
    public Product addProduct(@RequestBody Product product) {

        return productServices.addProduct(product);
    }

    @PostMapping("delete")
    @TrackExecutionTime
    public String deleteProduct(@RequestParam String productName) {
        productServices.deleteProduct(productName);
        return "Success";
    }

    @PostMapping("deleteById")
    @TrackExecutionTime
    public <T> ResponseEntity<T> deleteProductById(@RequestParam Integer productId) throws Exception {
        try {
            String result = productServices.deleteProductById(productId);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting faild! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @PutMapping("update")
    @TrackExecutionTime
    public <T> ResponseEntity<T> updateProduct(@RequestParam Integer id, @RequestParam Integer quantity) {
        try {
            String result = productServices.updateProductQuantity(id, quantity);

            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("updating Faild!" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("get")
    @TrackExecutionTime
    public <T> ResponseEntity<T> getProducts() throws Exception {
        mailingService.sendSimpleMail();
        return new ResponseEntity(productServices.getProduct(), HttpStatus.OK);
    }

    /**
     * @GetMapping("getDto") public List<ProductDTO> getProduct(){
     * <p>
     * return productServices.getProduct();
     * }
     **/
    @GetMapping("getById")
    @TrackExecutionTime
    public ResponseEntity<?> getProductById(@RequestParam Integer productId) {
        try {
            Product product = productServices.getProductById(productId);

            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving product failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByName")
    @TrackExecutionTime
    public ResponseEntity<?> getProductsByName(@RequestParam String productName) {
        try {
            List<Product> products = productServices.getProductsByName(productName);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by name failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByColor")
    @TrackExecutionTime
    public ResponseEntity<?> getProductsByColor(@RequestParam String color) {
        try {
            List<Product> products = productServices.getProductsByColor(color);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by color failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getByPrice")
    @TrackExecutionTime
    public ResponseEntity<?> getProductsByPrice(@RequestParam Double price) {
        try {
            List<Product> products = productServices.getProductsByPrice(price);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by price failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getByCountry")
    @TrackExecutionTime
    public ResponseEntity<?> getProductByCountry(@RequestParam String country) {
        try {
            List<Product> products = productServices.getProductByCountry(country);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by country failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getBySize")
    @TrackExecutionTime
    public ResponseEntity<?> getProductBySize(@RequestParam String size) {
        try {
            List<Product> products = productServices.getProductBySize(size);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by size failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByCategory")
    @TrackExecutionTime
    public ResponseEntity<?> getProductByCategory(@RequestParam String category) {
        try {
            List<Product> products = productServices.getProductByCategory(category);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by category failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByIsActive")
    @TrackExecutionTime
    public ResponseEntity<?> getProductByIsActive(@RequestParam Boolean isActive) {
        try {
            List<Product> products = productServices.getProductByIsActive(isActive);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by isActive failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   /** @GetMapping("/checkStock")
    public List<Product> getLowStockReport() {
        List<Product> lowStockProducts = productServices.getLowStockProducts();
        if (!lowStockProducts.isEmpty()) {
            slackService.sendMessage("practice", "Low stock alert: " + lowStockProducts.size() + " products with low stock.");
        }
        return lowStockProducts;
    }**/
   @Scheduled(cron = "0 0 9/6 * * *")
   @GetMapping("lowStock")
   @TrackExecutionTime
   public List<Product> lowStockCheck() {
       List<Product> lowStockProducts = productServices.getLowStockProducts();
       if (!lowStockProducts.isEmpty()) {
           StringBuilder messageBuilder = new StringBuilder();
           messageBuilder.append("-------------------------\nLow stock alert:\n-------------------------\n");
           for (Product product : lowStockProducts) {
               messageBuilder.append("Product ID: ").append(product.getId())
                       .append(" | Product Name: ").append(product.getProductDetails().getName())
                       .append(" | Quantity: ").append(product.getQuantity())
                       .append("\n------------------------------------------------------------\n");
           }
           slackService.sendMessage("balqees", messageBuilder.toString());
       }
       return lowStockProducts;
   }

    @GetMapping("word")
    @TrackExecutionTime
    public String sayhi(@RequestParam String word){
        return word;
    }
}


