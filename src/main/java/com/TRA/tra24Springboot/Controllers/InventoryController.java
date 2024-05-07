package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
   /**

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
    public String reportInventory() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("********** Report *************\n");
     //   stringBuilder.append("Products: "+globalInventoryItem.getProducts()+"\n");
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



}
