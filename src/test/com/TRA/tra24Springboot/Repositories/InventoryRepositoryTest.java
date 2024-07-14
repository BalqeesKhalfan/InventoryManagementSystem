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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Autowired
    ContactDetailsRepository contactDetailsRepository;
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
        product.setIsActive(Boolean.TRUE);
        productRepository.save(product);

        ContactDetails contactDetails = ContactDetails.builder()
                .email("supplier@xyz.com")
                .phoneNumber("99660889")
                .faxNumber("987654321")
                .address("123 Supplier Street")
                .postalCode("12345")
                .build();
        contactDetailsRepository.save(contactDetails);

        Supplier supplier = Supplier.builder()
                .companyName("OXY")
                .country("Oman")
                .contactDetails(contactDetails)
                .productsOffered(List.of(product))
                .nextDeliveryTime(new Date())
                .complaints("No complaints")
                .paymentMethods(PaymentType.BANK_TRANSFER)
                .shippingMethods("Air")
                .minimumOrderQuantity(10)

                .build();
        supplier.setIsActive(Boolean.TRUE);

        supplierRepository.save(supplier);

        Inventory inventory = Inventory.builder()
                .products(List.of(product))
                .location("Oman")
                .admin("Ahmed")
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
       List<Inventory> inventories = inventoryRepository.getInventoryByAvailability(true);
       assertThat(inventories).isNotNull();
       assertThat(inventories.size()).isGreaterThan(0);
       assertThat(inventories.get(0).getIsActive()).isTrue();
   }


    @Test
    void getInventoryByLocation() {
        List<Inventory> inventories = inventoryRepository.getInventoryByLocation("Oman");
        assertThat(inventories).isNotNull();
        assertThat(inventories.size()).isEqualTo(1);
        assertThat(inventories.get(0).getLocation()).isEqualTo("Oman");
    }

    @Test
    void getInventoryByAdminName() {
        List<Inventory> inventories = inventoryRepository.getInventoryByAdminName("Ahmed");
        assertThat(inventories).isNotNull();
        assertThat(inventories.size()).isEqualTo(1);
        assertThat(inventories.get(0).getAdmin()).isEqualTo("Ahmed");
    }
}