package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  ProductDetailsRepository productDetailsRepository;
     private  Integer productId;
    private  Integer productDetailsId;
    @BeforeEach
    void setUp() {
        ProductDetails details1 = ProductDetails.builder()
                .name("Springfield Elementary")
                .color("Red")
                .price(10.0)
                .countryOfOrigin("USA")
                .size("Large")
                .expiryDate(new Date())
                .description("Educational Institution")

                .build();
        details1.setCreatedDate(new Date());
        details1.setIsActive(Boolean.TRUE);
       productDetailsId= productDetailsRepository.save(details1).getId();

        ProductDetails details2 = ProductDetails.builder()
                .name("Sohar")
                .color("Blue")
                .price(15.0)
                .countryOfOrigin("Oman")
                .size("Medium")
                .expiryDate(new Date())

                .description("Business Center")

                .build();
        details2.setCreatedDate(new Date());
        details2.setIsActive(Boolean.TRUE);
        productDetailsId =productDetailsRepository.save(details2).getId();
        Product product1 = Product.builder()

                .productDetails(details1)
                .quantity(100)
                .category("Education")
                .sku(UUID.randomUUID())

                .build();
        product1.setIsActive(Boolean.TRUE);
        product1.setCreatedDate(new Date());
        productId =productRepository.save(product1).getId();

        Product product2 = Product.builder()

                .productDetails(details2)
                .quantity(200)
                .category("Business")
                .sku(UUID.randomUUID())

                .build();

        product2.setIsActive(Boolean.TRUE);
        product2.setCreatedDate(new Date());
       productId= productRepository.save(product2).getId();
    }

    @Test
    void getByProductName() {
        List<Product> products = productRepository.findByProductName("Springfield Elementary");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Springfield Elementary", products.get(0).getProductDetails().getName());


    }

    @Test
    void findByProductName() {
        List<Product> products = productRepository.findByProductName("Sohar");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Sohar", products.get(0).getProductDetails().getName());
    }

    @Test
    void findByProductcolor() {
        List<Product> products = productRepository.findByProductcolor("Blue");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Blue", products.get(0).getProductDetails().getColor());

    }

    @Test
    void findByProductPrice() {
        List<Product> products = productRepository.findByProductPrice(15.0);
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(15.0, products.get(0).getProductDetails().getPrice());
    }

    @Test
    void getProductById() {

        Product productById = productRepository.findById(productId).orElse(null);
        assertNotNull(productById);
        assertThat(productById.getId()).isEqualTo(productId);
    }

    @Test
    void findByProductByCountry() {
        List<Product> products = productRepository.findByProductByCountry("Oman");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Oman", products.get(0).getProductDetails().getCountryOfOrigin());
    }

    @Test
    void findByProductBySize() {
        List<Product> products = productRepository.findByProductBySize("Large");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Large", products.get(0).getProductDetails().getSize());
    }

    @Test
    void findByProductByCategory() {
        List<Product> products = productRepository.findByProductByCategory("Education");
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Education", products.get(0).getCategory());
    }

    @Test
    void findByProductByIsActive() {
        List<Product> productsAvailability = productRepository.findByProductByIsActive(Boolean.TRUE);
        assertThat(productsAvailability).isNotNull();
        assertThat(productsAvailability.size()).isEqualTo(2);
        assertThat(productsAvailability.get(0).getIsActive()).isEqualTo(Boolean.TRUE);
    }

    @Test
    void getProductByQuantity() {
        List<Product> products = productRepository.getProductByQuantity(200);
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(200, products.get(0).getQuantity());
    }
}