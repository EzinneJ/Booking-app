package com.ezinne.Bookingapp.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "appUser")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
