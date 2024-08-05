package com.samsam.travel.travelcommerce.domain.ticket.repository;

import com.samsam.travel.travelcommerce.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Modifying
    @Transactional
    @Query(
            "UPDATE Ticket t " +
            "SET " +
                "t.title        =   :#{#ticket.title}, " +
                "t.contents     =   :#{#ticket.contents}, " +
                "t.place        =   :#{#ticket.place}, " +
                "t.price        =   :#{#ticket.price}, " +
                "t.startDate    =   :#{#ticket.startDate}, " +
                "t.endDate      =   :#{#ticket.endDate} " +
            "WHERE t.ticketId   =   :#{#ticket.ticketId} " +
            "AND t.user.userId  =   :#{#ticket.user.userId}"
    )
    int updateTicket(@Param("ticket") Ticket ticket);
}
