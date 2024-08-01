package com.samsam.travel.travelcommerce.dao;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id", length = 255)
    private String ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "place", nullable = false, length = 255)
    private String place;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "delete_yn", nullable = false, length = 1)
    @ColumnDefault("N")
    private String deleteYn;

    @Column(name = "veiw_yn", nullable = false, length = 1)
    @ColumnDefault("N")
    private String veiwYn;

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
        if (this.veiwYn == null) {
            this.veiwYn = "N"; // 기본 노출 여부 값 설정
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}