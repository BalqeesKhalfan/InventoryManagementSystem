package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Order updateOrder(@RequestParam Integer orderId) {
        return orderServices.updateOrder(orderId);
    }

    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") Integer orderId,Order order) {
        return orderServices.cancelOrder(orderId);
    }


    //update order
   /** @PutMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }
    //cancel order
    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") String orderId, Order order) {
        // Check if order exists and is cancellable
        if (order != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
            // Update order status to cancel
            order.setStatus(OrderStatus.CANCELED);
            // Update payment status to canceled if payment already processed
            if (order.getPaymentStatus() == PaymentStatus.PAID) {
                order.setPaymentStatus(PaymentStatus.REJECTED);
            }
            // Call service method to update order in the database
            // orderService.updateOrder(order);

            return "Order with ID " + orderId + " has been canceled.";
        } else {
            return "Unable to cancel order. Order may not exist or may not be cancelable.";
        }
    }**/

   @GetMapping("getOrder")
   public List<OrderDTO> getOrder(){

       return orderServices.getOrder();
   }
    @GetMapping("getId")
    public Order getOrderById(@RequestParam Integer orderId){
        return orderServices.getOrderById(orderId);
    }

}
