package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ContactDetailsDTO;
import com.TRA.tra24Springboot.Logging.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Repositories.ContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class ContactDetailsServices {
    @Autowired
    ContactDetailsRepository contactDetailsRepository;
    @TrackExecutionTime
     public ContactDetails addContactDetails(ContactDetails contactDetails){
         contactDetails.setAddress("Muscat,Oman");
         contactDetails.setEmail("b.k.malshuraiqia@gmail.com");
         contactDetails.setPhoneNumber("91796242");
         contactDetails.setFaxNumber("jhj9");
         contactDetails.setPostalCode("99-77c");
         contactDetails.setAddress("Muscat, 124 rd");
         contactDetails.setIsActive(Boolean.TRUE);
         Arrays.asList(contactDetails);

         return contactDetailsRepository.save(contactDetails);
     }
    @TrackExecutionTime
    public String deleteContactDetails(Integer id){
        ContactDetails contactDetails = contactDetailsRepository.findById(id).get();

        if(contactDetails.getId().equals(id)){
            contactDetails.setIsActive(Boolean.FALSE);
            System.out.println(contactDetails.toString());
        }
        return "Success";
    }
    @TrackExecutionTime
    public ContactDetails updateContactDetails(ContactDetails contactDetails) {

        String email = contactDetails.getEmail();
        String phoneNumber= contactDetails.getPhoneNumber();
        String fax = contactDetails.getFaxNumber();
        String address = contactDetails.getAddress();
        String postal = contactDetails.getPostalCode();
        contactDetails.setEmail(email);
        contactDetails.setPhoneNumber(phoneNumber);
        contactDetails.setFaxNumber(fax);
        contactDetails.setAddress(address);
        contactDetails.setPostalCode(postal);
        contactDetails.setUpdatedDate(new Date());

        return contactDetailsRepository.save(contactDetails);
    }

    @TrackExecutionTime
    public ContactDetailsDTO reportCotactDetails(ContactDetails contactDetails){
        return  ContactDetailsDTO.convertToDTO(contactDetails);
    }


}
