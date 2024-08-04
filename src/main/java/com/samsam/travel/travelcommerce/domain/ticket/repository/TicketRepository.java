package com.samsam.travel.travelcommerce.domain.ticket.repository;

import com.samsam.travel.travelcommerce.dao.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
