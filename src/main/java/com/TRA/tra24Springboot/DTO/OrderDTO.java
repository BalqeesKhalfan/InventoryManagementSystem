package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    Integer orderId;
    List<ProductDTO> productDTO;
    OrderStatus orderStatus;
    PaymentStatus paymentStatus;
    Date orderDate;


    public static OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderStatus(order.getStatus());
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setProductDTO(ProductDTO.convertToDTO(order.getProductsOrdered()));

        return orderDTO;
    }
    public static List<OrderDTO> convertToDTO(List<Order> orderList) {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order orderObjectFromDatabase: orderList) {

            OrderDTO dto = convertToDTO(orderObjectFromDatabase);
            orderDTOS.add(dto);
        }
        return orderDTOS;
    }
}
