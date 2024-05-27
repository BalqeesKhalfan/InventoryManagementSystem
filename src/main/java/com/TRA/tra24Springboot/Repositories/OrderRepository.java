package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("SELECT ord from Order ord WHERE ord.id =:orderId")
    Order getOrderById(@Param("orderId") Integer orderId );
    @Query("SELECT ord from Order ord WHERE ord.status =:orderStatus")
    List<Order> getOrderByStatus(@Param("orderStatus") OrderStatus orderStatus );

    @Query("SELECT ord from Order ord WHERE ord.paymentStatus =:paymentStatus")
    List<Order> getOrderByPaymentStatus(@Param("paymentStatus") PaymentStatus paymentStatus );

}
