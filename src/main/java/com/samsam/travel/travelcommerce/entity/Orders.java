package com.samsam.travel.travelcommerce.entity;

import com.samsam.travel.travelcommerce.dto.order.OrderListResponse;
import com.samsam.travel.travelcommerce.entity.model.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "order_id", length = 255)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    @NotNull
    private Ticket ticket;

    @Column(name = "order_date", nullable = false, updatable = false)
    @NotNull
    private LocalDateTime orderDate;

    @Column(name = "total_amount", nullable = false)
    @NotNull
    @Min(1)
    private int totalAmount;

    @Column(name = "quantity", nullable = false)
    @NotNull
    @Min(1)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 1)
    @NotNull
    private OrderStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderId, orders.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }


    /**
 * 현재 {@link Orders} 객체를 {@link OrderListResponse} 객체로 변환합니다.
 * 이 메서드는 주문 목록을 준비하기 위해 사용됩니다.
 *
 * @return {@link OrderListResponse} 객체, 주문 세부 정보를 포함합니다.
 * @return 주문 ID, 사용자 ID, 티켓 ID, 주문 날짜, 총 금액, 수량, 상태
 */
public OrderListResponse toOrderListResponse() {
        return OrderListResponse.builder()
                .orderId(this.orderId)
                .userId(this.user.getUserId())
                .ticketId(this.ticket.getTicketId())
                .orderDate(this.orderDate)
                .totalAmount(this.totalAmount)
                .quantity(this.quantity)
                .status(this.status)
                .build();
    }

}