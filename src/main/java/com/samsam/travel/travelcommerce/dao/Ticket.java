package com.samsam.travel.travelcommerce.dao;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    private String ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "delete_yn", nullable = false)
    private String deleteYn;

    @Column(name = "veiw_yn", nullable = false)
    private String veiwYn;

    @Column(name = "regist_date", nullable = false)
    private LocalDateTime registDate;

    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

}