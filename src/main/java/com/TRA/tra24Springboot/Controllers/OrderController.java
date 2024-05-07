package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Models.PaymentType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/orders")

public class OrderController {
    Order globalOrder=new Order();
    @PostMapping("create")
    public Order createOrder(@RequestBody Order order) {
        // Simulate creating an order
        order.setId(1);

        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.UNPAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());
        order.setCategoryName("Electronics ");

        // Add any additional processing logic here
        return order;
    }
    //update order
    @PutMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        order.setOrderDate(new Date());
        return order;
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
    }

}
