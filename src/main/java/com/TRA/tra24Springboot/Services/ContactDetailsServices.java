package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Repositories.ContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactDetailsServices {
    @Autowired
    ContactDetailsRepository contactDetailsRepository;
     public ContactDetails addContactDetails(ContactDetails contactDetails){
         contactDetails.setAddress("Muscat,Oman");
         contactDetails.setEmail("b.k.malshuraiqia@gmail.com");
         contactDetails.setPhoneNumber("91796242");
         contactDetails.setFaxNumber("jhj9");
         contactDetails.setPostalCode("99-77c");

         return contactDetailsRepository.save(contactDetails);
     }
}
