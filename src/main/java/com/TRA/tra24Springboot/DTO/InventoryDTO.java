package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Inventory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InventoryDTO {
   /** Integer inventoryId;
    String inventoryManager;
    String inventoryLocation;
    String inventoryPhoneNo;
    String inventorySupplier;**/
   Integer inventoryId;
    List<ProductDTO>products;
  //  List<SupplierDTO> suppliers;
    String location;

   /** public static  InventoryDTO convertToDTO(Inventory inventory){
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setInventoryId(inventory.getId());
        inventoryDTO.setInventoryManager(inventory.getManager());
        inventoryDTO.setInventoryLocation(inventoryDTO.getInventoryLocation());
        inventoryDTO.setInventoryPhoneNo(inventoryDTO.getInventoryPhoneNo());
        inventoryDTO.setInventorySupplier(inventoryDTO.getInventorySupplier());
        return inventoryDTO;
    }
    public static List<InventoryDTO> convertToDTO(List<Inventory> inventoryList){
        List<InventoryDTO> inventoryDTOS = new ArrayList<>();
        for (Inventory inventoryFromDB: inventoryList){
            InventoryDTO dto = convertToDTO(inventoryFromDB);
            inventoryDTOS.add(dto);

        }
        return inventoryDTOS;
    }**/


   public static InventoryDTO convertToDTO(Inventory inventory) {
       InventoryDTO inventoryDTO = new InventoryDTO();
       inventoryDTO.setInventoryId(inventory.getId());
       inventoryDTO.setLocation(inventory.getLocation());
       inventoryDTO.setProducts(ProductDTO.convertToDTO(inventory.getProducts()));


       return inventoryDTO;
   }

    public static List<InventoryDTO> convertToDTO(List<Inventory> inventoryListList) {
        List<InventoryDTO> orderDTO = new ArrayList<>();

        for (Inventory inventoryDetailsDB : inventoryListList) {
            InventoryDTO dto = convertToDTO(inventoryDetailsDB);
            orderDTO.add(dto);
        }

        return orderDTO;
    }
}
