package com.samsam.travel.travelcommerce;

import com.samsam.travel.travelcommerce.domain.order.repository.OrderRepository;
import com.samsam.travel.travelcommerce.domain.order.service.OrderService;
import com.samsam.travel.travelcommerce.domain.ticket.repository.TicketRepository;
import com.samsam.travel.travelcommerce.domain.user.repository.UserRepository;
import com.samsam.travel.travelcommerce.dto.order.OrderDetail;
import com.samsam.travel.travelcommerce.dto.order.OrderRequest;
import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.entity.model.Role;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_shouldCreateNewOrder_whenRequestContainsValidUserAndTicketIds() {
        // Given
        String userId = "user1";
        OrderRequest orderRequest = new OrderRequest(List.of(new OrderDetail("ticket1", 2, 30000)), 30000);
        User user = User.builder()
                .userId(userId)
                .password("password")
                .name("john Doe")
                .phone("010-1234-5678")
                .role(Role.NORMAL)
                .build();

        Ticket ticket = Ticket.builder()
                .ticketId("ticket1")
                .contents("contents")
                .deleteYn("N")
                .endDate(LocalDate.of(2031, 1, 1))
                .price(15000)
                .registDate(LocalDateTime.of(2031, 1, 1, 12, 0))
                .startDate(LocalDate.of(2024, 1, 1))
                .updateDate(LocalDateTime.of(2024, 1, 8, 12, 0))
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(ticketRepository.findAllById(anyList())).thenReturn(List.of(ticket));

        // When
        orderService.createOrder(userId, orderRequest);

        // Then
        verify(orderRepository, times(1)).saveAll(anyList());
    }
}
