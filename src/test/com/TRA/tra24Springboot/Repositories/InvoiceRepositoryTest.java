package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
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
class InvoiceRepositoryTest {
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    @BeforeEach
    void setupInvoice() {
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
        Invoice invoice1 = Invoice.builder()
                .productList(List.of(product))
                .totalAmount(200.0)
                .paidAmount(100.0)
                .balance(100.0)

                .dueDate(DateHelperUtils.addDays(new Date(), 30))

                .build();
        invoice1.setIsActive(Boolean.TRUE);
        invoice1.setCreatedDate(new Date());

        invoiceRepository.save(invoice1);

        Invoice invoice2 = Invoice.builder()
                .productList(List.of(product))
                .totalAmount(300.0)
                .paidAmount(150.0)
                .balance(150.0)
                .dueDate(DateHelperUtils.addDays(new Date(), 20))
                .build();
        invoice2.setCreatedDate(DateHelperUtils.addDays(new Date(), -10));
        invoice2.setIsActive(Boolean.TRUE);

        invoiceRepository.save(invoice2);

    }
    @Test
    void getInvoiceByCreatedDate() {
    }

    @Test
    void getInvoiceByDueDate() {
    }

    @Test
    void getInvoicesByDueDateBetween() {
    }

    @Test
    void getOverdueInvoices() {
    }

    @Test
    void getInvoicesCreatedBetween() {
    }

    @Test
    void getPaidInvoicesBetween() {
    }
}