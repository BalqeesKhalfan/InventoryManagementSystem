package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Services.InventoryServices;
import com.TRA.tra24Springboot.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    Inventory globalInventoryItem;

    @Autowired
    InventoryServices inventoryServices;
    @Autowired
    ReportService reportService;

    @PostMapping("receive")

    public ResponseEntity<?> receiveStock( Inventory inventoryItem) {

        try {
            Inventory result = inventoryServices.receiveStock(inventoryItem);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Receiving stock failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("write-off")

    public ResponseEntity<?> writeOffInventory(@RequestParam Integer inventoryId) {
        try {
            Inventory result = inventoryServices.writeOffInventory(inventoryId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e ){
            return  new ResponseEntity<>("Writting off inventory failed "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // reporting all Inventory
    @GetMapping("report")

    public String reportInventory() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("********** Report *************\n");
       stringBuilder.append("Products: "+globalInventoryItem.getProducts()+"\n");
        // Sort products by name
        List<Product> sortedProducts = globalInventoryItem.getProducts().stream().collect(Collectors.toList());

        // Parse and display sorted product information
        String productsInfo = parseProductsInfo(sortedProducts);
        stringBuilder.append("Sorted Products:\n").append(productsInfo);
        stringBuilder.append("Location: " + globalInventoryItem.getLocation()+"\n");
        stringBuilder.append("Manager: "+globalInventoryItem.getManager()+"\n");
        stringBuilder.append("Supplier: "+globalInventoryItem.getSupplier()+"\n");
        stringBuilder.append("PhonNumber :"+globalInventoryItem.getPhoneNumber()+"\n");
        stringBuilder.append("OpeningHours: "+globalInventoryItem.getOpeningHours()+"\n");
        stringBuilder.append("ClosingHours: "+globalInventoryItem.getClosingHours()+"\n");


        return stringBuilder.toString();
    }

    private String parseProductsInfo(List<Product> products) {
        StringBuilder productsInfo = new StringBuilder();
        for (Product product : products) {
            productsInfo.append("Name: ").append(product.getProductDetails().getName()+"\n");
            productsInfo.append("Country of Origin: ").append(product.getProductDetails().getCountryOfOrigin()+"\n");
            productsInfo.append("Description : ").append(product.getProductDetails().getDescription()+"\n");
            productsInfo.append("Quantity  : ").append(product.getQuantity()+"\n");
            productsInfo.append("category : ").append(product.getCategory()+"\n");
            productsInfo.append("Color  : ").append(product.getProductDetails().getColor()+"\n");
            productsInfo.append("Size : ").append(product.getProductDetails().getSize()+"\n");
            productsInfo.append("sku  : ").append(product.getSku()+"\n");
            productsInfo.append(" price : ").append(product.getProductDetails().getPrice()+"\n");

            // Add other product attributes as needed
            productsInfo.append("\n");
        }
        return productsInfo.toString();
    }

    @GetMapping("getAll")

    public ResponseEntity<?>getInventories(){
        try {
            List<InventoryDTO> inventories = inventoryServices.getAll();
            reportService.createInventoryReport();
            return  new ResponseEntity<>(inventories,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Retrieving inventories failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getById")

    public ResponseEntity<?> getInventoryById(@RequestParam Integer inventoryId){
        try {
            Inventory inventory = inventoryServices.getInventoryById(inventoryId);
            return  new ResponseEntity<>(inventory, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("Retrieving inventory failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getByAvailability")

    public ResponseEntity<?> getInventoryByAvailability(@RequestParam Boolean isActive){
        try {
            List<Inventory> inventories= inventoryServices.getInventoryByIsActive(isActive);
            return  new ResponseEntity<>(inventories,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving inventories by isActive failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getByLocation")

    public ResponseEntity<?> getInventoryByLocation(@RequestParam String location) {
        try {
            List<Inventory> inventories=inventoryServices.getInventoryByLocation(location);
            return  new ResponseEntity<>(inventories,HttpStatus.OK);
        }catch (Exception e ){
            return  new ResponseEntity<>("Retrieving inventories by location failed!"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getByAdmin")

    public ResponseEntity<?> getInventoryByAdminName(@RequestParam String admin){
        try {
            List<Inventory> inventories=inventoryServices.getInventoryByAdminName(admin);
            return  new ResponseEntity<>(inventories,HttpStatus.OK);
        }catch (Exception e ){
            return  new ResponseEntity<>("Retrieving inventories by admin failed!"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
