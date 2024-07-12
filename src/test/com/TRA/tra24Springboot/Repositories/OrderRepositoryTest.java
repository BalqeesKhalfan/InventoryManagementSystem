package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.*;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class OrderRepositoryTest {
    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    private UUID sku;
    private Date date;

    @BeforeEach
    void  setupOrder() {
        ProductDetails productDetails = ProductDetails.builder()
                .name("Screen")
                .color("Black")
                .countryOfOrigin("Japan")
                .price(200.0)
                .size("24 inches")

                .build();
        productDetails.setIsActive(Boolean.TRUE);
        productDetailsRepository.save(productDetails);

        sku = UUID.randomUUID();

        Product product = Product.builder()
                .productDetails(productDetails)
                .category("Electronics")
                .quantity(50)
                .sku(sku)
                .build();
        product.setIsActive(Boolean.TRUE);
        productRepository.save(product);

        date = new Date();
        Order order = Order.builder()
                .productsOrdered(List.of(product))
                .categoryName("Electronics")
                .paymentType(PaymentType.BANK_TRANSFER)
                .status(OrderStatus.COMPLETED)
                .paymentStatus(PaymentStatus.PAID)
                .orderDate(new Date())
                .dueDate(DateHelperUtils.addDays(date, 7))
                .build();
        order.setIsActive(Boolean.TRUE);
        orderRepository.save(order);

    }



    @Test
    void getOrderByStatus() {
        List<Order> orders = orderRepository.getOrderByStatus(OrderStatus.COMPLETED);
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isGreaterThan(0);
        assertThat(orders.get(0).getStatus()).isEqualTo(OrderStatus.COMPLETED);

    }

    @Test
    void getOrderByPaymentStatus() {
        List<Order> orders = orderRepository.getOrderByPaymentStatus(PaymentStatus.PAID);
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isGreaterThan(0);

        assertThat(orders.get(0).getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
    }

    @Test
    void getOrderByPaymentType() {
        List<Order> orders = orderRepository.getOrderByPaymentType(PaymentType.BANK_TRANSFER);
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isGreaterThan(0);
        assertThat(orders.get(0).getPaymentType()).isEqualTo(PaymentType.BANK_TRANSFER);

    }

    @Test
    void getOrderByCategoryName() {
        List<Order> orderCategory = orderRepository.getOrderByCategoryName("Electronics");
        assertThat(orderCategory).isNotNull();
        assertThat(orderCategory.size()).isEqualTo(1);
        assertThat(orderCategory.get(0).getCategoryName()).isEqualTo("Electronics");
    }

    @Test
    void findByOrderByIsActive() {

    }
}