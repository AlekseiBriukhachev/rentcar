package com.aleksei.rentcarapi.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cars")
@Schema(description = "Car entity")
public class Car {

    public static final int START_SEQ = 100000;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "brand", nullable = false)
    @Schema(description = "Car brand", example = "BMW")
    private String brand;

    @Column(name = "model", nullable = false)
    @Schema(description = "Car model", example = "X5")
    private String model;

    @Column(name = "car_year", nullable = false)
    @Schema(description = "Year of creating", example = "2024")
    private int yearOfCreating;

    @Column(name = "color", nullable = false)
    @Schema(description = "Car color", example = "black")
    private String color;

    @Column(name = "kilometers")
    @Schema(description = "Car kilometers", example = "10000")
    private Long kilometers;

}
