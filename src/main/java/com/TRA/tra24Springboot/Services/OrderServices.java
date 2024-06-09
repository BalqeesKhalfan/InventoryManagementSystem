package com.TRA.tra24Springboot.Services;


import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class OrderServices {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductServices productServices;
    public Order createOrder(@RequestBody Order order){

        Product product = productServices.addProduct(order.getProductsOrdered().get(0));
        order.setProductsOrdered(Arrays.asList(product));
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.UNPAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());
        order.setCategoryName("Electronics");
        order.setCreatedDate(new Date());
        order.setOrderDate(new Date());
        //order.setIsActive(Boolean.TRUE);
        return orderRepository.save(order);
    }

    public Order updateOrder(@RequestParam Integer orderId) throws  Exception {
        try {
            Order orderFromDb = orderRepository.getOrderById(orderId);
            if(orderFromDb == null){
                throw new Exception("Order with ID : "+orderId+" is not found.");
            }
            orderFromDb.setOrderDate(new Date());
            return orderRepository.save(orderFromDb);
        }catch (Exception e){
            throw new Exception("Failed to update order: " + e.getMessage(), e);
        }
    }

    public String cancelOrder(@PathVariable("orderId") Integer orderId) {
        Order orderFromDb = orderRepository.getOrderById(orderId);
        if (orderFromDb != null && orderFromDb.getStatus() == OrderStatus.IN_PROGRESS) {
            // Update order status to cancel
            orderFromDb.setStatus(OrderStatus.CANCELED);
            // Update payment status to canceled if payment already processed
            if (orderFromDb.getPaymentStatus() == PaymentStatus.PAID) {
                orderFromDb.setPaymentStatus(PaymentStatus.REJECTED);
            }


            return "Order with ID " + orderId + " has been canceled.";
        } else {
            return "Unable to cancel order. Order may not exist or may not be cancelable.";
        }
    }



    public List<OrderDTO> getOrder() throws  Exception{
        try {
            List <Order> orders = orderRepository.findAll();
            if (orders.isEmpty()) {
                throw new Exception("No order found");
            }
            return OrderDTO.convertToDTO(orders);
        }catch (Exception e){
            throw new Exception("Failed to retrieve Orders : " + e.getMessage(), e);
        }
    }
    public Order getOrderById(Integer orderId) throws  Exception{
        try {
            Order order = orderRepository.getOrderById(orderId);
            if(order == null){
                throw  new Exception("orderId with ID "+orderId+" is not found.");
            }
            return order;
        }catch (Exception e){
            throw new Exception("Failed to retrieve order: " + e.getMessage(), e);
        }

    }

    public List<Order> getOrderByStatus(OrderStatus orderStatus) throws Exception{
        try {
            List<Order> orders=orderRepository.getOrderByStatus(orderStatus);
            if (orders.isEmpty()){
                throw  new Exception("No Order found with Order status :"+orderStatus);
            }
            return orders;
        }catch (Exception e) {
            throw new Exception("Failed to retrieve orders by Order Status : " + e.getMessage(), e);
        }

    }
    public List<Order> getOrderByPaymentStatus(PaymentStatus paymentStatus) throws  Exception{
        try {
            List<Order> orders = orderRepository.getOrderByPaymentStatus(paymentStatus);
            if (orders.isEmpty()){
                throw  new Exception("No Order found with payment status :"+paymentStatus);
            }
            return orders;
        }catch (Exception e){
            throw  new Exception("Faild to retrieve orders by payment status "+e.getMessage(),e);
        }
    }

    public List<Order> getOrderByPaymentType(PaymentType paymentType) throws Exception {
        try {
            List<Order> orders = orderRepository.getOrderByPaymentType(paymentType);
            if(orders.isEmpty()){
                throw  new Exception("No Orders found with payment Type :"+paymentType);
            }
            return  orders;
        }catch (Exception e){
            throw  new Exception("Faild to retrieve orders by payment type"+e.getMessage(),e);
        }

    }

    public List<Order> getOrderByCategoryName(String categoryName) throws Exception {
        try {
            List<Order> orders = orderRepository.getOrderByCategoryName(categoryName);
            if(orders.isEmpty()){
                throw  new Exception("No Orders found with payment Type :"+categoryName);
            }
            return  orders;
        }catch (Exception e){
            throw  new Exception("Faild to retrieve orders by category Name"+e.getMessage(),e);
        }

    }

    public List<Order>getOrderByIsActive(Boolean isActive) throws  Exception{
        try {
            List<Order> orders = orderRepository.findByOrderByIsActive(isActive);
            if(orders.isEmpty()){
                throw  new Exception("No orders found with isActive: " + isActive);
            }
            return  orders;
        }catch (Exception e){
            throw new Exception("Failed to retrieve orders With isActive : " + e.getMessage(), e);
        }

    }
}
