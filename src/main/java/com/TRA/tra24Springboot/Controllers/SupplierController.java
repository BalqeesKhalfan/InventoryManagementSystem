package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Services.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierServices supplierServices;


    @PostMapping("add")
    public Supplier addSupplier(Supplier supplier) {

        return supplierServices.addSupplier(supplier);
    }

    @PutMapping("update")
    public <T> ResponseEntity<T> updateSupplier(@RequestParam Integer id, @RequestParam Integer quantity) {
        try {
            String result = supplierServices.updateMinimumOrderQuantity(id, quantity);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("updating Faild!" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public <T> ResponseEntity<T> deleteSupplier(@PathVariable Integer id) {
        try {
            String result = supplierServices.remove(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting Supplier  Faild!" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //UPDATE SUPPLIER

    /**
     * @PutMapping("update") public Supplier updateSupplier(@RequestBody Supplier supplierUpdating) {
     * <p>
     * <p>
     * ContactDetails pd = supplierUpdating.getContactDetails();
     * pd.setUpdatedDate(new Date());
     * <p>
     * supplierUpdating.setContactDetails(pd);
     * supplierUpdating.setUpdatedDate(new Date());
     * <p>
     * globalSupplier= supplierUpdating;
     * return supplierRepository.save(supplierUpdating);
     * }
     * // delete
     * @PostMapping("delete/{id}") public String deleteSupplier(@PathVariable Integer id){
     * <p>
     * if(globalSupplier.getId().equals(id)){
     * globalSupplier.setIsActive(Boolean.FALSE);
     * System.out.println(globalSupplier.toString());
     * <p>
     * }
     * return "Success!";
     * }
     **/

    //method to get suppliers
    @GetMapping("get")
    public List<SupplierDTO> getAll() {
        return supplierServices.getSuppliers();
    }

    @GetMapping("getById")
    public Supplier getSupplierById(@RequestParam Integer supplierId) {
        return supplierServices.getSupplierById(supplierId);
    }

    @GetMapping("getByCompanyName")
    public List<Supplier> getSupplierByCompanyName(@RequestParam String companyName) {
        return supplierServices.getSupplierByCompanyName(companyName);
    }

    @GetMapping("getByCountry")
    public List<Supplier> getSupplierByCountry(@RequestParam String country) {
        return supplierServices.getSupplierByCountry(country);
    }

    @GetMapping("getByMinQuantity")
    public List<Supplier> getSupplierByMinimumOrderQuantity(@RequestParam Integer minimumOrderQuantity) {
        return supplierServices.getSupplierByMinimumOrderQuantity(minimumOrderQuantity);
    }

    @GetMapping("getByIsActive")
    public List<Supplier> getSupplierByIsActive(@RequestParam Boolean isActive) {
        return supplierServices.getSupplierByIsActive(isActive);
    }

    @GetMapping("getByShippingMethods")
    public List<Supplier> findBySupplierByShippingMethods(@RequestParam String shippingMethods) {
        return supplierServices.findBySupplierByShippingMethods(shippingMethods);
    }

    @GetMapping("getByPayment")
    public List<Supplier> getSupplierByPaymentMethod(@RequestParam PaymentType paymentMethods) {
        return supplierServices.getSupplierByPaymentMethod(paymentMethods);
    }


}
