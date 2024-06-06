package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import com.TRA.tra24Springboot.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SupplierServices {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    OrderServices orderServices;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ContactDetailsServices contactDetailsServices;

    public Supplier addSupplier(Supplier supplier) {
        //ContactDetails contactDetails = new ContactDetails();

        /** supplier.setCompanyName("Example Supplier");
         supplier.setCountry("Oman");
         supplier.setIsActive(Boolean.TRUE);
         supplier.setPaymentMethods("CASH");
         supplier.setShippingMethods("AirShipping ");
         supplier.setNextDeliveryTime(new Date());
         supplier.setComplaints("high ");
         supplier.setMinimumOrderQuantity("6");
         supplier.setCreatedDate(new Date());
         Order order = orderServices.createOrder(supplier.getOrders().get(0));
         supplier.setOrders(Arrays.asList(order));
         ContactDetails contactDetails = contactDetailsServices.addContactDetails(supplier.getContactDetails());
         supplier.setContactDetails(contactDetails);**/


        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails.setCreatedDate(new Date());
        productDetails = productDetailsRepository.save(productDetails);

        Product product = new Product();
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);
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

        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setPhoneNumber("12345678");
        contactDetails.setPostalCode("123");

        supplier.setCompanyName("Dell");
        supplier.setOrders(Arrays.asList(order));
        supplier.setCountry("USA");
        //supplier.setContactDetails(contactDetails);
        supplier.setMinimumOrderQuantity(2);
        supplier.setCreatedDate(new Date());
        supplier.setIsActive(Boolean.TRUE);


        return supplierRepository.save(supplier);
    }

    public String updateMinimumOrderQuantity(Integer supplierId, Integer quantity) throws Exception {

        try {
            Supplier supplierFromDb = supplierRepository.getSupplierById(supplierId);
            if (supplierFromDb == null) {
                throw new Exception("Supplier with ID: " + supplierId + " is not found.");
            }
            supplierFromDb.setMinimumOrderQuantity(quantity);
            supplierFromDb.setUpdatedDate(new Date());

            supplierRepository.save(supplierFromDb);
            return "Updated Successfully";
        } catch (Exception e) {
            throw new Exception("Failed to update Minimum Order  quantity: " + e.getMessage(), e);
        }
    }

    public String remove(Integer supplierId) throws Exception {
        try {
            Supplier supplierFromDb = supplierRepository.getSupplierById(supplierId);
            if (supplierFromDb == null) {
                throw new Exception("Supplier with ID: " + supplierId + " is not found.");
            }
            supplierFromDb.setIsActive(Boolean.FALSE);
            System.out.println(supplierFromDb.toString());
            supplierRepository.save(supplierFromDb);
            return "Removed Successfully";
        } catch (Exception e) {
            throw new Exception("Failed to remove Supplier : " + e.getMessage(), e);
        }
    }
        public List<SupplierDTO> getSuppliers () throws  Exception {
        try {
            List<Supplier> suppliers = supplierRepository.findAll();
            if (suppliers.isEmpty()) {
                throw new Exception("No Supplier found");
            }
            return SupplierDTO.convertToDTOList(suppliers);
        }catch (Exception e){
            throw new Exception("Failed to retrieve Suppliers : " + e.getMessage(), e);
        }

        }
        public Supplier getSupplierById (Integer supplierId)throws Exception{
        try {
            Supplier supplier = supplierRepository.getSupplierById(supplierId);
            if(supplier == null){
                throw  new Exception("Supplier with ID "+supplierId+" is not found.");
            }
            return supplier;
        }catch (Exception e){
            throw new Exception("Failed to retrieve Supplier: " + e.getMessage(), e);
        }
    }
        public List<Supplier> getSupplierByCompanyName (String companyName) throws  Exception{
        try {
            List<Supplier> suppliers = supplierRepository.getSupplierByCompanyName(companyName);
            if (suppliers.isEmpty()) {
                throw new Exception("No Supplier found with Company Name " + companyName);
            }
            return suppliers;
        }catch (Exception e) {
            throw new Exception("Failed to retrieve suppliers by Company name: " + e.getMessage(), e);}
     }
      public List<Supplier> getSupplierByCountry (String countr) throws  Exception{
           try {
               List<Supplier> suppliers = supplierRepository.getSupplierByCountry(countr);
               if (suppliers.isEmpty()) {
                   throw new Exception("No Supplier found with Country  Name " + countr);
               }
               return suppliers;
           }catch (Exception e){
               throw new Exception("Failed to retrieve suppliers by Country  Name"+e.getMessage());
           }

       }

        public List<Supplier> getSupplierByMinimumOrderQuantity (Integer minimumOrderQuantity) throws  Exception{
        try{
            List<Supplier> suppliers = supplierRepository.getSupplierByMinimumOrderQuantity(minimumOrderQuantity);
            if (suppliers.isEmpty()){
                throw new Exception("No Suppliers found with Minimum Order Quantity"+minimumOrderQuantity);
            }
            return suppliers;
        }catch (Exception e){
            throw  new Exception("Failed to retrieve suppliers By Minimum Order Quantity"+e.getMessage());
        }
        }

        public List<Supplier> getSupplierByIsActive (Boolean isActive)throws  Exception{
            try {
                List<Supplier> suppliers = supplierRepository.findBySupplierByIsActive(isActive);
                if(suppliers.isEmpty()){
                    throw  new Exception("No suppliers found with isActive: " + isActive);
                }
                return  suppliers;
            }catch (Exception e){
                throw new Exception("Failed to retrieve suppliers With isActive : " + e.getMessage(), e);
            }
        }

        public List<Supplier> findBySupplierByShippingMethods (String shippingMethods) throws  Exception{
           try {
               List<Supplier> suppliers = supplierRepository.findBySupplierByShippingMethods(shippingMethods);
               if(suppliers.isEmpty()){
                   throw  new Exception("No suppliers found with  this shipping Method: " + shippingMethods);
               }
               return  suppliers;
           }catch (Exception e){
               throw new Exception("Failed to retrieve suppliers shipping Method : " + e.getMessage(), e);
           }
        }
        public List<Supplier> getSupplierByPaymentMethod (PaymentType paymentMethods) throws  Exception{
           try {
               List<Supplier> suppliers = supplierRepository.getSupplierByPaymentMethod(paymentMethods);
               if(suppliers.isEmpty()){
                   throw  new Exception("No suppliers found with this Payment method "+paymentMethods);
               }
               return suppliers;
           }catch (Exception e){
               throw new Exception("Failed to retrieve suppliers Payment method : " + e.getMessage(), e);
           }

        }
    }
