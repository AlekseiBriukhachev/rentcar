package com.aleksei.rentcarapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Schema(description = "Client entity")
public class Client {

    public static final int START_SEQ = 100000;
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Schema(description = "Client name", example = "John")
    private String name;

    @Column(name = "surname", nullable = false)
    @Schema(description = "Client surname", example = "Doe")
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    @Schema(description = "Client email", example = "john@email.com")
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    @Schema(description = "Client phone", example = "+1234567890")
    private String phone;

    @Column(name = "address", nullable = false)
    @Schema(description = "Client address", example = "Main st.")
    private String address;

    @Column(name = "city", nullable = false)
    @Schema(description = "Client city", example = "New York")
    private String city;

    @Column(name = "zip_code", nullable = false)
    @Schema(description = "Client zip code", example = "10001")
    private String zipCode;

    @Column(name = "country", nullable = false)
    @Schema(description = "Client country", example = "USA")
    private String country;
}
