package com.TRA.tra24Springboot.Services;


import com.TRA.tra24Springboot.DTO.OrderDTO;
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

    public Order updateOrder(@RequestParam Integer orderId) {
        Order orderFromDb = orderRepository.getOrderById(orderId);
        orderFromDb.setOrderDate(new Date());
        return orderRepository.save(orderFromDb);
    }

    public String cancelOrder(@PathVariable("orderId") Integer orderId) {
        Order orderFromDb = orderRepository.getOrderById(orderId);
        // Check if order exists and is cancellable
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

    public List<OrderDTO> getOrder(){
        List <Order> orders = orderRepository.findAll();

        return OrderDTO.convertToDTO(orders);
    }


}
