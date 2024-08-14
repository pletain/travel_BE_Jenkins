package com.samsam.travel.travelcommerce.entity;

import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id", length = 255)
    private String ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "title", nullable = false, length = 255)
    @NotNull
    private String title;

    @Column(name = "contents", nullable = false)
    @NotNull
    private String contents;

    @Column(name = "place", nullable = false, length = 255)
    @NotNull
    private String place;

    @Column(name = "price", nullable = false)
    @NotNull
    @Min(1)
    private int price;

    @Column(name = "start_date", nullable = false)
    @NotNull
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @NotNull
    private LocalDate endDate;

    @Column(name = "delete_yn", nullable = false, length = 1)
    private String deleteYn;

    @Column(name = "view_yn", nullable = false, length = 1)
    private String viewYn;

    @CreationTimestamp
    @Column(name = "regist_date", nullable = false, updatable = false)
    private LocalDateTime registDate;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "ticket")
    private List<Review> reviews = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    public static Ticket convertDtoToEntity(TicketDto ticketDto) {
        return Ticket.builder()
                .ticketId(ticketDto.getTicketId())
                .user(ticketDto.getUser())
                .title(ticketDto.getTitle())
                .contents(ticketDto.getContents())
                .place(ticketDto.getPlace())
                .price(ticketDto.getPrice())
                .startDate(ticketDto.getStartDate())
                .endDate(ticketDto.getEndDate())
                .deleteYn("N")
                .viewYn("Y")
                .build();
    }
}