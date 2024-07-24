package com.TRA.tra24Springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reports")
public class ReportingController {

    @Autowired
    private InventoryController inventory;
    @Autowired
   private ProductController product;

    @Autowired
    private SupplierController supplier;
    @GetMapping("/inventory")
    public String generateInventoryReportWeekly() {
        // Call service method to fetch all products in inventory
        return inventory.reportInventory();
    }

    /** @GetMapping("/inventory")
    public String generateInventoryReport() {
        // Call service method to fetch all products in inventory
        return inventory.reportInventory();
    }

   /** @GetMapping("/product")
    public Product generateProductReport() {
        // Call service method to fetch all products in inventory
        return product.reportProduct();
    }

    /**@GetMapping("/supplier")
    public  Supplier generateSupplierReport(){
        return supplier.reportSupplier();
    }**/

}
