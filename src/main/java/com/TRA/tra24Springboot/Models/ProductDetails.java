package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails extends BaseEntity{

    String name;
    String countryOfOrigin;
    Date expiryDate;
    String size;
    String color;
    String description;
    Double price;

}
