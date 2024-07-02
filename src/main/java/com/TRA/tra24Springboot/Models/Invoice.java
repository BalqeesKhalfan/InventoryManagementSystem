package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Invoice extends  BaseEntity{
    @OneToMany
    List<Product> productList;
    Double totalAmount;
    Double paidAmount;
    Double balance;
    Date dueDate;

}
