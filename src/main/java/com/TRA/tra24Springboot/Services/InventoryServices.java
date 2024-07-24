package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.AOP.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryServices {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    SlackService slackService;
    @TrackExecutionTime
    public Inventory receiveStock( Inventory inventoryItem) throws  Exception {
        try {
            ProductDetails productDetails = ProductDetails.builder()
                    .name("Laptop")
                    .color("Black")
                    .price(350d)
                    .countryOfOrigin("USA").build();
            productDetails.setIsActive(Boolean.TRUE);
            productDetails.setExpiryDate(new Date());
            productDetails.setCreatedDate(new Date());



            productDetails = productDetailsRepository.save(productDetails);

            Product product =  Product.builder().sku(UUID.randomUUID()).quantity(100).build();
                              product.setIsActive(Boolean.TRUE);

                             product.setCreatedDate(new Date());

            product = productRepository.save(product);

            Order order = new Order();
            order.setProductsOrdered(Arrays.asList(product)); //setting the products lists
            order.setCategoryName("Electronics");
            order.setCreatedDate(new Date());
            order.setOrderDate(new Date());
            order.setStatus(OrderStatus.IN_PROGRESS);
            order.setPaymentStatus(PaymentStatus.PAID);
            order.setPaymentType(PaymentType.BANK_TRANSFER);
            order.setDueDate(new Date());
            order = orderRepository.save(order);

            inventoryItem.setProducts(Arrays.asList(product));
            inventoryItem.setLocation("Salalah");
            inventoryItem.setAdmin("Noura");
            inventoryItem.setPhoneNumber("12345778");
            Supplier supplier = new Supplier();
            supplier.setCompanyName("Dell");
            supplier.setOrders(Arrays.asList(order));
            supplier.setCountry("USA");
            //supplier.setContactDetails(contactDetails);
            supplier.setMinimumOrderQuantity(2);
            supplier.setCreatedDate(new Date());
            supplier.setIsActive(Boolean.TRUE);
            supplier = supplierRepository.save(supplier);

            inventoryItem.setSupplier(Arrays.asList(supplier));
            inventoryItem.setOpeningHours("8 AM");
            inventoryItem.setClosingHours("8 PM");
            inventoryItem.setWorkers(Arrays.asList("Jack", "Andrew", "Sam"));
            inventoryItem.setCreatedDate(new Date());
            inventoryItem.setIsActive(Boolean.TRUE);

            return inventoryRepository.save(inventoryItem);
        }catch (Exception e){
            throw new Exception("Failed to receive stock: " + e.getMessage(), e);
        }
    }

    @TrackExecutionTime
    public Inventory writeOffInventory(Integer inventoryId) throws Exception {
        try {
            Inventory inventoryFromDb = inventoryRepository.getInventoryById(inventoryId);
            if (inventoryFromDb == null) {
                throw new Exception("Inventory with ID: " + inventoryId + " not found");
            }
            inventoryFromDb.setIsActive(Boolean.FALSE);
            inventoryFromDb.setUpdatedDate(new Date());
            //inventoryItem.setWriteOffDate(new Date());

            return inventoryRepository.save(inventoryFromDb);
        }catch (Exception e) {
            throw new Exception("Failed to write off inventory: " + e.getMessage(), e);
        }
    }
    @TrackExecutionTime
    public List<InventoryDTO> getAll() throws  Exception{
        try {
            List<Inventory> inventories = inventoryRepository.findAll();
            if(inventories.isEmpty()){
                throw  new Exception("No inventory found ");
            }
            return  InventoryDTO.convertToDTO(inventories);
        }catch (Exception e){
            throw new Exception("Failed to retrieve Orders : " + e.getMessage(), e);
        }
    }
    @TrackExecutionTime
    public Inventory getInventoryById(Integer inventoryId) throws  Exception{
        try {
            Inventory inventory =inventoryRepository.getInventoryById(inventoryId);
            if(inventory == null){
                throw  new Exception("inventory with ID "+inventoryId+" is not found.");
            }
            return inventory;
        }catch (Exception e) {
            throw new Exception("Failed to retrieve order: " + e.getMessage(), e);
        }
    }
    @TrackExecutionTime
    public List<Inventory>getInventoryByIsActive(Boolean isActive) throws  Exception {
        try {
            List<Inventory> inventories = inventoryRepository.getInventoryByAvailability(isActive);
            if(inventories.isEmpty()){
                throw  new Exception("No inventories found with isActive: " + isActive);
            }
            return  inventories;
        }catch (Exception e){
            throw new Exception("Failed to retrieve inventories With isActive : " + e.getMessage(), e);
        }

    }
    @TrackExecutionTime
    public List<Inventory> getInventoryByLocation(String location)throws  Exception{
        try {
            List<Inventory> inventories = inventoryRepository.getInventoryByLocation(location);
            if(inventories.isEmpty()){
                throw  new Exception("No inventories found with location: " + location);
            }
            return  inventories;
        }catch (Exception e){
            throw new Exception("Failed to retrieve inventories With location : " + e.getMessage(), e);
        }

    }
    @TrackExecutionTime
    public List<Inventory> getInventoryByAdminName(String admin) throws  Exception{
        try {
            List<Inventory> inventories = inventoryRepository.getInventoryByAdminName(admin);
            if(inventories.isEmpty()){
                throw  new Exception("No inventories found with admin: " + admin);
            }
            return  inventories;
        }catch (Exception e){
            throw new Exception("Failed to retrieve inventories With admin : " + e.getMessage(), e);
        }

    }



}
