package com.TRA.tra24Springboot.Repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InvoiceRepositoryTest {

    
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