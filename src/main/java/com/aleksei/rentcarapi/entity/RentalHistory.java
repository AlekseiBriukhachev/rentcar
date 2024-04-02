package com.aleksei.rentcarapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "rental_history")
@Schema(description = "Rental history entity")
public class RentalHistory {
    public static final int START_SEQ = 100000;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "start_date")
    @Schema(description = "Start date of rental", type = "date", example = "2024-04-01")
    private LocalDate startDate;

    @Column(name = "end_date")
    @Schema(description = "End date of rental", type = "date", example = "2024-04-01")
    private LocalDate endDate;
}
