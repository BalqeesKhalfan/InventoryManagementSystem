package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private Inventory globalInventoryItem = new Inventory();
    @PostMapping("receive")
    public Inventory receiveStock(@RequestBody Inventory inventoryItem) {
        inventoryItem.setId(1);
        inventoryItem.setCreatedDate(new Date());
        inventoryItem.setIsActive(Boolean.TRUE);
        inventoryItem.setOpeningHours("8am");
        inventoryItem.setClosingHours("6pm");

        globalInventoryItem = inventoryItem;
        return inventoryItem;
    }
   /** @PostMapping("receive")
    public Inventory receiveStock(int productId, List<Product> quantityReceived) {
        Inventory inventoryProduct = new Inventory();
        // Simulating receipt of stock
        inventoryProduct.setId(productId);
        inventoryProduct.setProducts(quantityReceived);
        inventoryProduct.setCreatedDate(new Date());
        inventoryProduct.setUpdatedDate(new Date());
        inventoryProduct.setLocation("Oman");
        inventoryProduct.setManager("New stock received");
        globalInventoryItem = inventoryProduct;
        return inventoryProduct;
    }
    @PostMapping("write-off")
    public Inventory writeOffInventory(@RequestParam int productId, @RequestParam int quantityToWriteOff) {
        Inventory inventoryItem = new Inventory();
        // Simulating write-off of inventory
        inventoryItem.setId(productId);
       // inventoryItem.setProducts(quantityToWriteOff);
        inventoryItem.setCreatedDate(new Date());
        inventoryItem.setUpdatedDate(new Date());
        inventoryItem.setIsActive(Boolean.TRUE);
        inventoryItem.setLocation("Oman");
        inventoryItem.setManager("Balqees");
       // inventoryItem.setWorkers();
        inventoryItem.setSupplier("Apple company");
        inventoryItem.setPhoneNumber("91796242");
        inventoryItem.setOpeningHours("8am");
        inventoryItem.setClosingHours("5pm");


        globalInventoryItem = inventoryItem;
        return inventoryItem;
    }

    private void setProducts(int quantityToWriteOff) {
    }**/


    // reporting all Inventory
    @GetMapping("report")
    public Inventory reportInventory() {

        return globalInventoryItem;
    }



}
