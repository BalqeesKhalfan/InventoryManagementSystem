package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDetailsRepository extends JpaRepository <ContactDetails,Integer> {
}
