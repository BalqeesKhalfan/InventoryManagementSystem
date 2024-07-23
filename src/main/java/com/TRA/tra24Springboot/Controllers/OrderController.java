package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Logging.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Services.OrderServices;
import com.TRA.tra24Springboot.Services.SlackService;
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
    @Autowired
    SlackService slackService;
    @PostMapping("create")

    public Order createOrder(@RequestBody Order order) {
        slackService.sendMessage("Balqees","Order has been Added");
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

    @PutMapping("cancel")

    public <T> ResponseEntity<T> cancelOrder(@RequestParam Integer orderId) {
        try {
            String result = orderServices.cancelOrder(orderId);
            return  (ResponseEntity<T>) new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return (ResponseEntity<T>) new ResponseEntity<>("Cancelletion faild "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //method to cancel order


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

    public ResponseEntity<?> getOrderById(@RequestParam Integer orderId){
        try {
            Order order = orderServices.getOrderById(orderId);
            return  new ResponseEntity<>(order, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("Retrieving order failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getByStatus")

    public ResponseEntity<?>  getOrderByStatus(@RequestParam OrderStatus orderStatus){
        try {
            List<Order> orders= orderServices.getOrderByStatus(orderStatus);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Retrieving orders by status failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByPaymentStatus")

    public ResponseEntity<?> getOrderByPaymentStatus(@RequestParam PaymentStatus paymentStatus){
        try {
            List<Order> orders= orderServices.getOrderByPaymentStatus(paymentStatus);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Retrieving orders by payment status  failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getByPaymentType")

    public ResponseEntity<?> getOrderByPaymentType(@RequestParam PaymentType paymentType){
        try {
            List<Order>orders =orderServices.getOrderByPaymentType(paymentType);
            return  new ResponseEntity<>(orders,HttpStatus.OK);

        }catch (Exception e){
            return  new ResponseEntity<>("Retrieving orders by payment type failed!"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByCategoryName")

    public ResponseEntity<?> getOrderByPaymentType(@RequestParam String categoryName){
        try {
            List<Order>orders =orderServices.getOrderByCategoryName(categoryName);
            return  new ResponseEntity<>(orders,HttpStatus.OK);

        }catch (Exception e){
            return  new ResponseEntity<>("Retrieving orders by category Name failed!"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByIsActive")

    public ResponseEntity<?> getOrderByIsActive(@RequestParam Boolean isActive) {

        try {
            List<Order> orders= orderServices.getOrderByIsActive(isActive);
            return  new ResponseEntity<>(orders,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Retrieving orders by isActive failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
