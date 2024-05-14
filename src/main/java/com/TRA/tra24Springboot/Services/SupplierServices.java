package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class SupplierServices {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    OrderServices orderServices;
    @Autowired
    ContactDetailsServices contactDetailsServices;
    public Supplier addSupplier(Supplier supplier){
        //ContactDetails contactDetails = new ContactDetails();

        supplier.setCompanyName("Example Supplier");
        supplier.setCountry("Oman");
        supplier.setIsActive(Boolean.TRUE);
        supplier.setPaymentMethods("CASH");
        supplier.setShippingMethods("AirShipping ");
        supplier.setNextDeliveryTime(new Date());
        supplier.setComplaints("high ");
        supplier.setMinimumOrderQuantity("6");
        supplier.setCreatedDate(new Date());
        Order order = orderServices.createOrder(supplier.getOrders().get(0));
        supplier.setOrders(Arrays.asList(order));
        ContactDetails contactDetails = contactDetailsServices.addContactDetails(supplier.getContactDetails());
        supplier.setContactDetails(contactDetails);
        return supplierRepository.save(supplier);
    }

    public String updateMinimumOrderQuantity(Integer supplierId, String quantity){
        Supplier supplierFromDb = supplierRepository.getSupplierById(supplierId);
      //  Supplier supplier = supplierRepository.getById(supplierId);
        supplierFromDb.setMinimumOrderQuantity(quantity);
        supplierFromDb.setUpdatedDate(new Date());

        supplierRepository.save(supplierFromDb);
        return "updated successfully";
    }

    public String remove(Integer supplierId){
        Supplier supplierFromDb = supplierRepository.getSupplierById(supplierId);
        supplierFromDb.setIsActive(Boolean.FALSE);
        System.out.println(supplierFromDb.toString());
        supplierRepository.save(supplierFromDb);
        return "Removed Successfully";
    }

}
