package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {



    @Autowired
    private InventoryController inventory;
    @Autowired ProductController product;

    @Autowired
    private SupplierController supplier;

    @GetMapping("/inventory")
    public String generateInventoryReport() {
        // Call service method to fetch all products in inventory
        return inventory.reportInventory();
    }

    @GetMapping("/product")
    public Product generateProductReport() {
        // Call service method to fetch all products in inventory
        return product.reportProduct();
    }

    @GetMapping("/supplier")
    public  Supplier generateSupplierReport(){
        return supplier.reportSupplier();
    }

}
