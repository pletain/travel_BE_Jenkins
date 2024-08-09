package com.samsam.travel.travelcommerce.domain.order.repository;

import com.samsam.travel.travelcommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, String> {

    @Query("SELECT o FROM Orders o WHERE o.user.userId = :userId")
    List<Orders> findOrdersByUserId(@Param("userId") String userId);
}