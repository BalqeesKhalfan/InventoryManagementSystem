package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Inventory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InventoryDTO {
    Integer inventoryId;
    String inventoryManager;
    String inventoryLocation;
    String inventoryPhoneNo;
    String inventorySupplier;

    public static  InventoryDTO convertToDTO(Inventory inventory){
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
    }
}
