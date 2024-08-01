package com.samsam.travel.travelcommerce.dao;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "cart_id", length = 255)
    private String cartId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "delete_yn", nullable = false, length = 1)
    @ColumnDefault("N")
    private String deleteYn;

    @Column(name = "regist_date", nullable = false, updatable = false)
    private LocalDateTime registDate;

    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @PrePersist
    protected void onCreate() {
        this.registDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        if (this.deleteYn == null) {
            this.deleteYn = "N"; // 기본 삭제 여부 값 설정
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}