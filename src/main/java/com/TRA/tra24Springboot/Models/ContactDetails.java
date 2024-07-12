package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetails extends BaseEntity {

    String email;
    String phoneNumber;
    String faxNumber;
    String address;
    String postalCode;

}
