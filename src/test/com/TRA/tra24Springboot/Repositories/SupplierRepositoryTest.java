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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    private  ProductDetailsRepository productDetailsRepository;
    @Autowired
    ContactDetailsRepository contactDetailsRepository;
    private  Integer supplierId;
    private  Integer productId;
    private  Integer productDetailsId;
    private  Integer contactDetailsId;


    @BeforeEach
    void  setupSupplier(){
        ContactDetails contactDetails = ContactDetails.builder()
                .email("supplier@xyz.com")
                .phoneNumber("99660889")
                .faxNumber("987654321")
                .address("123 Supplier Street")
                .postalCode("12345")
                .build();
       contactDetailsId = contactDetailsRepository.save(contactDetails).getId();

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
                productDetailsId=productDetailsRepository.save(productDetails1).getId();
        ProductDetails productDetails2 = ProductDetails.builder()
                .name("Wach")
                .color("Blue")
                .price(20.0)
                .countryOfOrigin("Germany")
                .size("Large")
                .build();
        productDetailsId=productDetailsRepository.save(productDetails2).getId();

        Product product2 = Product.builder()
                .productDetails(productDetails2)
                .category("Apple Device")
                .quantity(50)
                .build();

       productId= productRepository.save(product1).getId();
        productId= productRepository.save(product2).getId();

        Supplier supplier = Supplier.builder()
                .companyName("OXY")
                .country("Oman")
                .contactDetails(contactDetails)
                .productsOffered(List.of(product1, product2))
                .nextDeliveryTime(new Date())
                .complaints("No complaints")
                .paymentMethods(PaymentType.BANK_TRANSFER)
                .shippingMethods("Air")
                .minimumOrderQuantity(10)

                .build();
        supplier.setIsActive(Boolean.TRUE);

       supplierId= supplierRepository.save(supplier).getId();

    }
   @Test
   void  getSupplierById(){
        Supplier supplierById = supplierRepository.getSupplierById(supplierId);
        assertThat(supplierById).isNotNull();
       assertThat(supplierById.getId()).isEqualTo(supplierId);
   }


    @Test
    void getSupplierByCompanyName() {
        List<Supplier> suppliers = supplierRepository.getSupplierByCompanyName("OXY");
        assertThat(suppliers).isNotNull();
        assertThat(suppliers.size()).isGreaterThan(0);

        Supplier supplier = suppliers.get(0);
        assertThat(supplier.getCompanyName()).isEqualTo("OXY");
        assertThat(supplier.getCountry()).isEqualTo("Oman");
    }

    @Test
    void getSupplierByCountry() {
        List<Supplier> suppliers = supplierRepository.getSupplierByCountry("Oman");
        assertThat(suppliers).isNotNull();
        assertThat(suppliers.size()).isGreaterThan(0);
        assertThat(suppliers.get(0).getCompanyName()).isEqualTo("OXY");
    }

    @Test
    void getSupplierByMinimumOrderQuantity() {
        List<Supplier> suppliers = supplierRepository.getSupplierByMinimumOrderQuantity(10);
        assertThat(suppliers).isNotNull();
        assertThat(suppliers.size()).isGreaterThan(0);
        assertThat(suppliers.get(0).getMinimumOrderQuantity()).isEqualTo(10);
    }

    @Test
    void findBySupplierByIsActive() {
        List<Supplier> suppliers = supplierRepository.findBySupplierByIsActive(true);
        assertThat(suppliers).isNotNull();
        assertThat(suppliers.size()).isGreaterThan(0);
        assertThat(suppliers.get(0).getIsActive()).isTrue();
    }

    @Test
    void findBySupplierByShippingMethods() {
        List<Supplier> suppliers = supplierRepository.findBySupplierByShippingMethods("Air");
        assertThat(suppliers).isNotNull();
        assertThat(suppliers.size()).isGreaterThan(0);
        assertThat(suppliers.get(0).getShippingMethods()).isEqualTo("Air");
    }

    @Test
    void getSupplierByPaymentMethod() {
        List<Supplier> suppliers = supplierRepository.getSupplierByPaymentMethod(PaymentType.BANK_TRANSFER);
        assertThat(suppliers).isNotNull();
        assertThat(suppliers.size()).isGreaterThan(0);
        assertThat(suppliers.get(0).getPaymentMethods()).isEqualTo(PaymentType.BANK_TRANSFER);
    }
}