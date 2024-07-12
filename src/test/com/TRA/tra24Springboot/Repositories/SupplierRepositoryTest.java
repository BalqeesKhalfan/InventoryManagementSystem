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

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SupplierRepositoryTest {

    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ContactDetailsRepository contactDetailsRepository;


    @BeforeEach
    void  setupSupplier(){
        ContactDetails contactDetails = ContactDetails.builder()
                .email("supplier@xyz.com")
                .phoneNumber("99660889")
                .faxNumber("987654321")
                .address("123 Supplier Street")
                .postalCode("12345")
                .build();
        contactDetailsRepository.save(contactDetails);

        ProductDetails productDetails1 = ProductDetails.builder()
                .name("Laptop")
                .color("Red")
                .price(10.0)
                .countryOfOrigin("USA")
                .size("Medium")
                .build();

        Product product1 = Product.builder()
                .productDetails(productDetails1)
                .category("Mac Device")
                .quantity(100)
                .build();

        ProductDetails productDetails2 = ProductDetails.builder()
                .name("Wach")
                .color("Blue")
                .price(20.0)
                .countryOfOrigin("Germany")
                .size("Large")
                .build();

        Product product2 = Product.builder()
                .productDetails(productDetails2)
                .category("Apple Device")
                .quantity(50)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);

        Supplier supplier = Supplier.builder()
                .companyName("Supplier1")
                .country("Country1")
                .contactDetails(contactDetails)
                .productsOffered(List.of(product1, product2))
                .nextDeliveryTime(new Date())
                .complaints("No complaints")
                .paymentMethods(PaymentType.BANK_TRANSFER)
                .shippingMethods("Air")
                .minimumOrderQuantity(10)
                .build();
        supplierRepository.save(supplier);

    }

    @Test
    void getSupplierById() {
    }

    @Test
    void getSupplierByCompanyName() {
    }

    @Test
    void getSupplierByCountry() {
    }

    @Test
    void getSupplierByMinimumOrderQuantity() {
    }

    @Test
    void findBySupplierByIsActive() {
    }

    @Test
    void findBySupplierByShippingMethods() {
    }

    @Test
    void getSupplierByPaymentMethod() {
    }
}