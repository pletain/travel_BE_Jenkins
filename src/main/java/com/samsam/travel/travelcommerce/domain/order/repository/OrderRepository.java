package com.samsam.travel.travelcommerce.domain.order.repository;

import com.samsam.travel.travelcommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, String> {
}