package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    private final Date date = new Date();
    private final Date dueDate= DateHelperUtils.addDays(date,1);
    Integer invoiceId;

    @BeforeEach
    void setupInvoice() {
        ProductDetails productDetails = ProductDetails.builder()
                .name("Screen")
                .color("Black")
                .countryOfOrigin("Japan")
                .price(200.0)
                .size("24 inches")
                .expiryDate(date)

                .build();
        productDetails.setCreatedDate(date);
        productDetails.setIsActive(Boolean.TRUE);
        productDetails = productDetailsRepository.save(productDetails);


        ProductDetails productDetails2 = ProductDetails.builder()
                .name("Phone")
                .color("Black")
                .countryOfOrigin("Korea")
                .price(500.0)
                .size("6 inches")
                .expiryDate(date)
                .build();
        productDetails2.setCreatedDate(date);
        productDetails2.setIsActive(Boolean.TRUE);
        productDetails2 = productDetailsRepository.save(productDetails2);

        Product product = Product.builder()
                .productDetails(productDetails)
                .category("Electronics")
                .quantity(50)
                .sku(UUID.randomUUID())
                .build();
        product.setCreatedDate(date);
        product.setIsActive(Boolean.TRUE);
        product = productRepository.save(product);

        Product product2 = Product.builder()
                .productDetails(productDetails2)
                .category("Toys")
                .quantity(5)
                .sku(UUID.randomUUID())
                .build();
        product2.setCreatedDate(date);
        product2.setIsActive(Boolean.TRUE);
        product2 = productRepository.save(product2);

        Invoice invoice1 = Invoice.builder()
                .productList(List.of(product))
                .totalAmount(200.0)
                .paidAmount(100.0)
                .balance(100.0)

                .dueDate(dueDate)

                .build();
        invoice1.setIsActive(Boolean.TRUE);
        invoice1.setCreatedDate(date);

        invoiceId = invoiceRepository.save(invoice1).getId();

        Invoice invoice2 = Invoice.builder()
                .productList(Arrays.asList(product2))
                .totalAmount(300.0)
                .paidAmount(150.0)
                .balance(150.0)
                .dueDate(dueDate)
                .paymentDate(date)
                .build();
        invoice2.setCreatedDate(date);
        invoice2.setIsActive(Boolean.TRUE);


        invoiceId = invoiceRepository.save(invoice2).getId();

    }
    @Test
    void getInvoiceById() {
        Invoice invoiceById = invoiceRepository.findById(invoiceId).orElse(null);
        assertThat(invoiceById).isNotNull();
        assertThat(invoiceById.getId()).isEqualTo(invoiceId);
    }

    @Test
    void getInvoiceByCreatedDate() {
        List<Invoice> invoices = invoiceRepository.getInvoiceByCreatedDate(date);
        assertThat(invoices).isNotNull();
        assertThat(invoices.size()).isGreaterThan(0);
        assertThat(invoices.get(0).getCreatedDate()).isEqualTo(date);

    }

    @Test
    void getInvoiceByDueDate() {
         List<Invoice> invoices = invoiceRepository.getInvoiceByDueDate(dueDate);
         assertThat(invoices).isNotNull();
        assertThat(invoices.size()).isGreaterThan(0);
        assertThat(invoices.get(0).getDueDate()).isEqualTo(dueDate);
    }

    @Test
    void getInvoicesByDueDateBetween() {

        List<Invoice> invoicesDueDateBetween = invoiceRepository.getInvoicesByDueDateBetween(date, dueDate);
        assertThat(invoicesDueDateBetween).isNotNull();
        assertThat(invoicesDueDateBetween.size()).isEqualTo(2);


    }

    @Test
    void getOverdueInvoices() {
        List<Invoice> invoices = invoiceRepository.getOverdueInvoices(date);
        assertThat(invoices).isNotNull();
        assertThat(invoices.size()).isEqualTo(0);
    }

    @Test
    void getInvoicesCreatedBetween() {
        List<Invoice> invoices = invoiceRepository.getInvoicesCreatedBetween(dueDate, date);
        assertThat(invoices).isNotNull();
        assertThat(invoices.size()).isEqualTo(0);
    }

    @Test
    void getPaidInvoicesBetween() {
        List<Invoice> invoices = invoiceRepository.getPaidInvoicesBetween(dueDate, date);
        assertThat(invoices).isNotNull();
        assertThat(invoices.size()).isEqualTo(0);
    }
}