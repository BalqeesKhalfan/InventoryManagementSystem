package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    Supplier globalSupplier = new Supplier();


    @PostMapping("add")
    public Supplier addSupplier(@RequestBody Supplier supplier){


        ContactDetails contactDetails = new ContactDetails();
        supplier.setId(1);
        supplier.setCompanyName("Example Supplier");
        supplier.setCountry("Oman");
        supplier.setIsActive(Boolean.TRUE);
        supplier.setPaymentMethods("CASH");
        supplier.setShippingMethods("AirShipping ");

        supplier.setNextDeliveryTime(new Date());
        supplier.setComplaints("high ");
        supplier.setMinimumOrderQuantity("6");


        supplier.setContactDetails(contactDetails);
        contactDetails.setAddress("Muscat,Oman");
        contactDetails.setEmail("b.k.malshuraiqia@gmail.com");
        contactDetails.setPhoneNumber("91796242");
        contactDetails.setFaxNumber("jhj9");
        contactDetails.setPostalCode("99-77c");

       // supplier.setCreatedDate(new Date());

        globalSupplier = supplier;

        return supplier;
    }
    //UPDATE SUPPLIER
    @PutMapping("update")
    public Supplier updateSupplier(@RequestBody Supplier supplierUpdating) {


        ContactDetails pd = supplierUpdating.getContactDetails();
        pd.setUpdatedDate(new Date());

        supplierUpdating.setContactDetails(pd);
        supplierUpdating.setUpdatedDate(new Date());

        globalSupplier= supplierUpdating;
        return supplierUpdating;
    }
    // delete
    @PostMapping("delete/{id}")
    public String deleteSupplier(@PathVariable Integer id){

        if(globalSupplier.getId().equals(id)){
            globalSupplier.setIsActive(Boolean.FALSE);
            System.out.println(globalSupplier.toString());

        }
        return "Success!";
    }


}
