package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Inventory extends BaseEntity {

    @OneToMany
    List<Product> products;
    String location;
    String manager; //TODO: Update once user class created
    String admin;
    List<String> workers; //TODO: Update user class created
    @OneToMany
    List<Supplier> supplier;//TODO: Update once supplier class created
    String phoneNumber;
    String openingHours;
    String closingHours;



}
