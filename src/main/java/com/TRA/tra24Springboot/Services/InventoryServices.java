package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public Inventory receiveStock( Inventory inventoryItem) throws  Exception {
        try {
            ProductDetails productDetails = new ProductDetails();
            productDetails.setName("Laptop");
            productDetails.setColor("Black");
            productDetails.setPrice(350d);
            productDetails.setCountryOfOrigin("USA");
            productDetails.setCreatedDate(new Date());
            productDetails = productDetailsRepository.save(productDetails);

            Product product = new Product();
            product.setProductDetails(productDetails);
            product.setSku(UUID.randomUUID());
            product.setQuantity(100);
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
    public List<Inventory>getInventoryByIsActive(Boolean isActive) {
        return inventoryRepository.getInventoryByAvailability(isActive);
    }
    public List<Inventory> getInventoryByLocation(String location){
        return inventoryRepository.getInventoryByLocation(location);
    }
    public List<Inventory> getInventoryByAdminName(String admin){
        return inventoryRepository.getInventoryByAdminName(admin);
    }

}
