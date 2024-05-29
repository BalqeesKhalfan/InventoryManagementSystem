package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository <Inventory,Integer> {
    //Query to get inventory by ID

    @Query("SELECT inv from Inventory inv WHERE inv.id =:inventoryId")
    Inventory getInventoryById(@Param("inventoryId") Integer inventoryId );

    //Query to git inventory by availability
    @Query("SELECT i FROM Inventory i WHERE i.isActive =:isActive")
    List<Inventory> getInventoryByAvailability(@Param("isActive") Boolean isActive);

    //Query to get inventory by location
    @Query("SELECT i FROM Inventory i WHERE i.location =:location")
    List<Inventory> getInventoryByLocation(@Param("location") String location);


}
