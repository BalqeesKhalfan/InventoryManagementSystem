package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")

public class OrderController {
    @Autowired
    OrderServices orderServices;
    @PostMapping("create")
    public Order createOrder(@RequestBody Order order) {
        return orderServices.createOrder(order);
    }
    @PutMapping("update")
    public  <T>ResponseEntity<T>  updateOrder(@RequestParam Integer orderId) {
        try {
            Order result = orderServices.updateOrder(orderId);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("updating Faild!" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") Integer orderId,Order order) {
        return orderServices.cancelOrder(orderId);
    }

   @GetMapping("getOrder")
   public ResponseEntity<?>  getOrder(){
        try {
            List<OrderDTO> orders = orderServices.getOrder();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Retrieving orders failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
    @GetMapping("getById")
    public Order getOrderById(@RequestParam Integer orderId){
        return orderServices.getOrderById(orderId);
    }
    @GetMapping("getByStatus")
    public List<Order> getOrderByStatus(@RequestParam OrderStatus orderStatus){
        return orderServices.getOrderByStatus(orderStatus);
    }
    @GetMapping("getByPaymentStatus")
    public List<Order> getOrderByPaymentStatus(@RequestParam PaymentStatus paymentStatus){
        return orderServices.getOrderByPaymentStatus(paymentStatus);
    }
    @GetMapping("getByPaymentType")
    public List<Order> getOrderByPaymentType(@RequestParam PaymentType paymentType){
        return orderServices.getOrderByPaymentType(paymentType);
    }
    @GetMapping("getByCategoryName")
    public List<Order> getOrderByPaymentType(@RequestParam String categoryName){
        return orderServices.getOrderByCategoryName(categoryName);
    }
    @GetMapping("getByIsActive")
    public List<Order> getOrderByIsActive(@RequestParam Boolean isActive) {
        return orderServices.getOrderByIsActive(isActive);
    }


}
