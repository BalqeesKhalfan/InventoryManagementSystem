package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToOne;
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
public class Supplier extends BaseEntity{

    String companyName;
    String country;

    @OneToOne
    ContactDetails contactDetails;

    @OneToMany
    List<Product> productsOffered;
    Date nextDeliveryTime;

    @OneToMany
    List<Product> expectedProducts;

    String complaints;
    PaymentType paymentMethods;
    String shippingMethods;
    Integer minimumOrderQuantity;

    @OneToMany
    List<Order> orders;

}
