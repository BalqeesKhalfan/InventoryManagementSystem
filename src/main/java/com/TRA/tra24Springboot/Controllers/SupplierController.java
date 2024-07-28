package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Services.SupplierReportService;
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
    @Autowired
    SupplierReportService reportService;
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
    @PostMapping("delete")

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
            reportService.createSupplierReport();
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

    public ResponseEntity<?> getSupplierByMinimumOrderQuantity(@RequestParam Integer minimumOrderQuantity) {
        try {
            List<Supplier> suppliers = supplierServices.getSupplierByMinimumOrderQuantity(minimumOrderQuantity);
            return  new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving suppliers ByMinimumOrderQuantity faild "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByIsActive")

    public ResponseEntity<?> getSupplierByIsActive(@RequestParam Boolean isActive) {
        try {
            List<Supplier> suppliers = supplierServices.getSupplierByIsActive(isActive);
            return  new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving suppliers by isActive failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getByShippingMethods")

    public ResponseEntity<?> findBySupplierByShippingMethods(@RequestParam String shippingMethods) {
        try{
            List<Supplier> suppliers = supplierServices.findBySupplierByShippingMethods(shippingMethods);
            return  new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving suppliers by Shipping Method ! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByPayment")

    public ResponseEntity<?> getSupplierByPaymentMethod(@RequestParam PaymentType paymentMethods) {
        try {
            List<Supplier> suppliers = supplierServices.getSupplierByPaymentMethod(paymentMethods);
            return  new ResponseEntity<>(suppliers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving suppliers by Payment Type  ! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
