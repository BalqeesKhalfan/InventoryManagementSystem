package com.TRA.tra24Springboot.Repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SupplierRepositoryTest {

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