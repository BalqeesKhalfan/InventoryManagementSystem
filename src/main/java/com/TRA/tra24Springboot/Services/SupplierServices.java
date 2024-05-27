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
    public Supplier addSupplier(Supplier supplier){
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

    public String updateMinimumOrderQuantity(Integer supplierId, Integer quantity){
        Supplier supplierFromDb = supplierRepository.getSupplierById(supplierId);
      //  Supplier supplier = supplierRepository.getById(suppierId);
        supplierFromDb.setMinimumOrderQuantity(quantity);
        supplierFromDb.setUpdatedDate(new Date());

        supplierRepository.save(supplierFromDb);
        return "updated successfully";
    }

    public String remove(Integer supplierId){
        Supplier supplierFromDb = supplierRepository.getSupplierById(supplierId);
        supplierFromDb.setIsActive(Boolean.FALSE);
        System.out.println(supplierFromDb.toString());
        supplierRepository.save(supplierFromDb);
        return "Removed Successfully";
    }
    public List<SupplierDTO> getSuppliers(){
        List<Supplier> suppliers = supplierRepository.findAll();
        return SupplierDTO.convertToDTOList(suppliers);
    }
    public Supplier getSupplierById(Integer supplierId){
        return supplierRepository.getSupplierById(supplierId);
    }

    public List<Supplier> getSupplierByCompanyName(String companyName){
        return supplierRepository.getSupplierByCompanyName(companyName);
    }

    public List<Supplier> getSupplierByCountry(String countr){
        return supplierRepository.getSupplierByCountry(countr);
    }

    public List<Supplier> getSupplierByMinimumOrderQuantity(Integer minimumOrderQuantity){
        return supplierRepository.getSupplierByMinimumOrderQuantity(minimumOrderQuantity);
    }
}
