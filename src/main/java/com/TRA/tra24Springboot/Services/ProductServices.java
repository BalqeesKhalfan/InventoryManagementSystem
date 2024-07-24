package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.AOP.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailsServices productDetailsServices;

    public Product addProduct(Product product) {


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
    @TrackExecutionTime
    public String deleteProduct(String productName) {
        Product productFromDb = productRepository.getByProductName(productName);
        productFromDb.setIsActive(Boolean.FALSE);
        productRepository.save(productFromDb);
        return "Success";
    }
    @TrackExecutionTime
    public String deleteProductById(Integer productId) throws Exception {
        try {
            Product product = productRepository.getProductById(productId);
            if (product == null) {
                throw new Exception("Product With ID : " + productId + "is not found");
            }
            product.setIsActive(Boolean.FALSE);
            System.out.println(product.toString());
            productRepository.save(product);
            return "Product Deleted Successfully";

        } catch (Exception e) {
            throw new Exception("Failed to delete Product : " + e.getMessage());
        }
    }
    @TrackExecutionTime
    public String updateProductQuantity(Integer productId, Integer quantity) throws Exception {
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
    @TrackExecutionTime
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    @TrackExecutionTime
    public List<ProductDTO> getProduct() throws Exception {
        try {
            List<Product> products = productRepository.findAll();
            if (products.isEmpty()) {
                throw new Exception("No products found");
            }
            return ProductDTO.convertToDTO(products);
        } catch (Exception e) {
            throw new Exception("Failed to retrieve products: " + e.getMessage(), e);
        }
    }
    @TrackExecutionTime
    public Product getProductById(Integer productId) throws Exception {
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
    @TrackExecutionTime
    public List<Product> getProductsByName(String productName) throws Exception {
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
    @TrackExecutionTime
    public List<Product> getProductsByColor(String color) throws Exception {

        try {
            List<Product> products = productRepository.findByProductcolor(color);
            if (products.isEmpty()) {
                throw new Exception("No products found with color: " + color);
            }
            return products;

        } catch (Exception e) {
            throw new Exception("Failed to retrieve products With color: " + e.getMessage(), e);
        }

    }
    @TrackExecutionTime
    public List<Product> getProductsByPrice(Double price) throws Exception {
        try {
            List<Product> products = productRepository.findByProductPrice(price);
            if (products.isEmpty()) {
                throw new Exception("No products found with price: " + price);
            }
            return products;
        } catch (Exception e) {
            throw new Exception("Failed to retrieve products With price: " + e.getMessage(), e);
        }

    }
    @TrackExecutionTime
    public List<Product> getProductByCountry(String country) throws Exception {
        try {
            List<Product> products = productRepository.findByProductByCountry(country);
            if (products.isEmpty()) {
                throw new Exception("No products found with country: " + country);
            }
            return products;

        } catch (Exception e) {
            throw new Exception("Failed to retrieve products With country: " + e.getMessage(), e);
        }

    }
    @TrackExecutionTime
    public List<Product> getProductBySize(String size) throws Exception {
        try {
            List<Product> products = productRepository.findByProductBySize(size);
            if (products.isEmpty()) {
                throw new Exception("No products found with Size: " + size);
            }
            return products;

        } catch (Exception e) {
            throw new Exception("Failed to retrieve products With size : " + e.getMessage(), e);
        }
    }
    @TrackExecutionTime
    public List<Product> getProductByCategory(String category) throws Exception {
        try {
            List<Product> products = productRepository.findByProductByCategory(category);
            if (products.isEmpty()) {
                throw new Exception("No products found with category: " + category);
            }
            return products;

        } catch (Exception e) {
            throw new Exception("Failed to retrieve products With category : " + e.getMessage(), e);
        }
    }
    @TrackExecutionTime
    public List<Product> getProductByIsActive(Boolean isActive) throws Exception {
        try {
            List<Product> products = productRepository.findByProductByIsActive(isActive);
            if (products.isEmpty()) {
                throw new Exception("No products found with isActive: " + isActive);
            }
            return products;

        } catch (Exception e) {
            throw new Exception("Failed to retrieve products With isActive : " + e.getMessage(), e);

        }

    }

    /**
     * public List<Product> getLowStockProducts() {
     * List<Product> products = productRepository.findAll();
     * List<Product> lowStockProducts = new ArrayList<>();
     * for (Product product : products) {
     * if (product.getQuantity() < 50) {
     * lowStockProducts.add(product);
     * }
     * }
     * return lowStockProducts;
     * }
     **/
    @TrackExecutionTime
    public List<Product> getProductByQuantity(Integer quantity) {
        return productRepository.getProductByQuantity(quantity);
    }
    @TrackExecutionTime
    public List<Product> getLowStockProducts() {
        List<Product> products = productRepository.findAll();
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product != null && product.getQuantity() != null) {
                if (product.getQuantity() < 5) {
                    lowStockProducts.add(product);
                }
            }
        }
        return lowStockProducts;
    }
}
