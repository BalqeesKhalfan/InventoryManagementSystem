package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class InventoryServices {
    @Autowired
    InventoryRepository inventoryRepository;
    public Inventory receiveStock(@RequestBody Inventory inventoryItem) {
        inventoryItem.setCreatedDate(new Date());
        inventoryItem.setIsActive(Boolean.TRUE);
        inventoryItem.setOpeningHours("8am");
        inventoryItem.setClosingHours("6pm");
        return inventoryRepository.save(inventoryItem);
    }

    public Inventory writeOffInventory(Integer inventoryId) {
        Inventory inventoryFromDb = inventoryRepository.getInventoryById(inventoryId);
        inventoryFromDb.setIsActive(Boolean.FALSE);
        inventoryFromDb.setUpdatedDate(new Date());
        //inventoryItem.setWriteOffDate(new Date());

        return inventoryRepository.save(inventoryFromDb);
    }
    public List<InventoryDTO> getAll(){
        List<Inventory> inventories = inventoryRepository.findAll();

        return InventoryDTO.convertToDTO(inventories);

    }

    public Inventory getInventoryById(Integer inventoryId){
        return inventoryRepository.getInventoryById(inventoryId);
    }
    public List<Inventory>getInventoryByIsActive(Boolean isActive) {
        return inventoryRepository.getInventoryByAvailability(isActive);
    }


}
