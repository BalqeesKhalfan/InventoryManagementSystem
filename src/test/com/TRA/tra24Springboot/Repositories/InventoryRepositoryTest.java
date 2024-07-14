package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InventoryRepositoryTest {
    @Autowired
     ProductRepository productRepository;
    @Autowired
      SupplierRepository supplierRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @BeforeEach
    void  setupInventory() {
        ProductDetails productDetails = ProductDetails.builder()
                .name("Screen")
                .color("Black")
                .countryOfOrigin("Japan")
                .price(200.0)
                .size("24 inches")
                .expiryDate(new Date())

                .build();
        productDetails.setCreatedDate(new Date());
        productDetails.setIsActive(Boolean.TRUE);
        productDetailsRepository.save(productDetails);
        Product product = Product.builder()
                .productDetails(productDetails)
                .category("Electronics")
                .quantity(50)
                .sku(UUID.randomUUID())

                .build();
        product.setCreatedDate(new Date());

        productRepository.save(product);

        Supplier supplier = Supplier.builder()
                .companyName("TechSupplier")
                .country("Japan")
                .contactDetails(ContactDetails.builder()
                        .email("contact@techsupplier.com")
                        .phoneNumber("123-456-7890")
                        .build())
                .build();
        supplierRepository.save(supplier);

        Inventory inventory = Inventory.builder()
                .products(List.of(product))
                .location("Warehouse 1")
                .admin("Admin Name")
                .supplier(List.of(supplier))
                .phoneNumber("123-456-7890")
                .openingHours("09:00")
                .closingHours("17:00")
                .build();
        inventory.setIsActive(Boolean.TRUE);
        inventory.setCreatedDate(new Date());
        inventoryRepository.save(inventory);
    }
    @Test
    void getInventoryById() {
    }

    @Test
    void getInventoryByAvailability() {
    }

    @Test
    void getInventoryByLocation() {
    }

    @Test
    void getInventoryByAdminName() {
    }
}