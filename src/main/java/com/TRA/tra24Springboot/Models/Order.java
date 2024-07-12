package com.TRA.tra24Springboot.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @OneToMany
    List<Product> productsOrdered;
    String categoryName;
    Date orderDate;

    @Enumerated(EnumType.STRING)
    OrderStatus status;
    String description;
    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    PaymentType paymentType;
    Date dueDate;
}
