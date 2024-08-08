package com.samsam.travel.travelcommerce.domain.order.service;

import com.samsam.travel.travelcommerce.domain.order.repository.OrderRepository;
import com.samsam.travel.travelcommerce.domain.ticket.repository.TicketRepository;
import com.samsam.travel.travelcommerce.domain.user.repository.UserRepository;
import com.samsam.travel.travelcommerce.dto.order.OrderDetail;
import com.samsam.travel.travelcommerce.dto.order.OrderRequest;
import com.samsam.travel.travelcommerce.entity.Orders;
import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.global.error.exception.TicketNotFoundException;
import com.samsam.travel.travelcommerce.global.error.exception.UserNotFoundException;
import com.samsam.travel.travelcommerce.utils.Common;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.samsam.travel.travelcommerce.entity.model.OrderStatus.P;
import static com.samsam.travel.travelcommerce.global.status.ErrorCode.NOT_EXIST_TICKET;
import static com.samsam.travel.travelcommerce.global.status.ErrorCode.NOT_EXIST_USER;

/**
 * 주문 관련 작업을 담당하는 서비스 클래스입니다.
 *
 *  * @author lavin
 *  * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    private final Common common;

    /**
     * 사용자 ID와 주문 요청 DTO를 받아서 새로운 주문을 생성합니다.
     *
     * @param userId 사용자 ID
     * @param orderRequest 주문 요청 DTO
     */
    @Transactional
    public void createOrder(String userId, OrderRequest orderRequest) {
        User user = findUserById(userId);
        Map<String, Ticket> ticketsMap = findTicketsByIds(orderRequest);

        List<Orders> ordersList = orderRequest.getOrders().stream()
                .map(orderDetail -> buildOrder(user, ticketsMap, orderDetail))
                .collect(Collectors.toList());

        orderRepository.saveAll(ordersList);
    }

    /**
     * 사용자 ID로 사용자를 찾아서 반환합니다.
     *
     * @param userId 사용자 ID
     * @return 찾은 사용자
     * @throws UserNotFoundException 사용자를 찾을 수 없는 경우
     */
    private User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST_USER));
    }

    /**
     * 주문 요청 DTO로부터 상품 ID 목록을 추출하여 상품 목록을 찾아서 맵으로 반환합니다.
     *
     * @param orderRequest 주문 요청 DTO
     * @return 상품 ID를 키로, 상품을 값으로 갖는 맵
     * @throws TicketNotFoundException 상품을 찾을 수 없는 경우
     */
    private Map<String, Ticket> findTicketsByIds(OrderRequest orderRequest) {
        List<String> productIds = orderRequest.getOrders().stream()
                .map(OrderDetail::getProductId)
                .distinct()
                .collect(Collectors.toList());

        List<Ticket> tickets = ticketRepository.findAllById(productIds);

        if (tickets.size() != productIds.size()) {
            throw new TicketNotFoundException(NOT_EXIST_TICKET);
        }

        return tickets.stream()
                .collect(Collectors.toMap(Ticket::getTicketId, ticket -> ticket));
    }

    /**
     * 사용자, 상품 맵, 주문 상세 정보로부터 주문 객체를 생성합니다.
     *
     * @param user 사용자
     * @param ticketsMap 상품 맵
     * @param orderDetail 주문 상세 정보
     * @return 생성된 주문 객체
     * @throws TicketNotFoundException 상품을 찾을 수 없는 경우
     */
    private Orders buildOrder(User user, Map<String, Ticket> ticketsMap, OrderDetail orderDetail) {
        Ticket ticket = Optional.ofNullable(ticketsMap.get(orderDetail.getProductId()))
                .orElseThrow(() -> new TicketNotFoundException(NOT_EXIST_TICKET));

        int totalAmount = orderDetail.getQuantity() * ticket.getPrice();

        return Orders.builder()
                .orderId(common.getTargetUuid("order"))
                .user(user)
                .ticket(ticket)
                .orderDate(LocalDateTime.now())
                .totalAmount(totalAmount)
                .quantity(orderDetail.getQuantity())
                .status(P)
                .build();
    }
}
