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

    //method to get suppliers
    @GetMapping("get")
    public ResponseEntity<?> getAll() {
        try {
            List<SupplierDTO> suppliers = supplierServices.getSuppliers();
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving suppliers failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getById")
    public ResponseEntity<?> getSupplierById(@RequestParam Integer supplierId) {
        try {
            Supplier supplier = supplierServices.getSupplierById(supplierId);
            return  new ResponseEntity<>(supplier, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("Retrieving Supplier failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getByCompanyName")
    public ResponseEntity<?> getSupplierByCompanyName(@RequestParam String companyName) {
        try {
            List<Supplier> suppliers = supplierServices.getSupplierByCompanyName(companyName);
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving suppliers by company name  failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getByCountry")
    public ResponseEntity<?> getSupplierByCountry(@RequestParam String country) {
        try {
            List<Supplier> suppliers = supplierServices.getSupplierByCountry(country);
            return new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>("Retrieving suppliers by contry name failed!"+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
