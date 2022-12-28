package com.ezinne.Bookingapp.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "seat_number", unique = true)
    private Integer seatNumber;

    @OneToOne(mappedBy = "booking")
    private AppUser appUser;

}
