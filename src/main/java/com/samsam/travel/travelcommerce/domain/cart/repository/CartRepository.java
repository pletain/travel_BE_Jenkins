package com.samsam.travel.travelcommerce.domain.cart.repository;

import com.samsam.travel.travelcommerce.dto.cart.CartDto;
import com.samsam.travel.travelcommerce.entity.Cart;
import com.samsam.travel.travelcommerce.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    @Query(
        "SELECT " +
            "c.ticket " +
        "FROM Cart c " +
        "WHERE c.user = :#{#cartDto.user} " +
            "AND c.deleteYn = 'N' " +
            "AND c.ticket.deleteYn = 'N' " +
        "ORDER BY c.registDate "
    )
    List<Ticket> findMyCart(@Param("cartDto") CartDto cartDto);

    @Query(
        "SELECT " +
            "c " +
        "FROM Cart c " +
        "WHERE c.user = :#{#cartDto.user} " +
            "AND  c.ticket = :#{#cartDto.ticket} "
    )
    Cart findMyCartTicket(@Param("cartDto") CartDto cartDto);


    @Modifying
    @Transactional
    @Query(
        "UPDATE Cart c " +
        "SET c.quantity     =   :#{#cart.quantity} " +
        "WHERE c.cartId     =   :#{#cart.cartId} " +
            "AND c.user     =   :#{#cart.user} " +
            "AND c.ticket   =   :#{#cart.ticket} "
    )
    int updateCart(@Param("cart") Cart cart);
}
