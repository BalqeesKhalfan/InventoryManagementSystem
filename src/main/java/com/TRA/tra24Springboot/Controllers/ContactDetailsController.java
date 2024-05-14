package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.ContactDetailsDTO;
import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Services.ContactDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contact_details")
public class ContactDetailsController {

    @Autowired
    ContactDetailsServices contactDetailsServices;

    @GetMapping("add")
    public ContactDetails addContactDetails(ContactDetails contactDetails) {
        return contactDetailsServices.addContactDetails(contactDetails);
    }

    @PostMapping("delete")
    public String deleteContactDetails(@RequestParam Integer id){
        contactDetailsServices.deleteContactDetails(id);
        return "Success";
    }

    @PutMapping("update")
    public ContactDetails updateContactDetails(@RequestBody ContactDetails contactDetails) {
        return contactDetailsServices.updateContactDetails(contactDetails);
    }

    @GetMapping("get")
    public ContactDetailsDTO reportCotactDetails(ContactDetails contactDetails){
        return contactDetailsServices.reportCotactDetails(contactDetails);
    }
}
