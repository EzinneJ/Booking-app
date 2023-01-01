package com.ezinne.Bookingapp.services;

import com.ezinne.Bookingapp.model.AppUser;
import com.ezinne.Bookingapp.model.Booking;
import com.ezinne.Bookingapp.repositories.AppUserRepository;
import lombok.SneakyThrows;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    @BeforeEach
    void setUp() {
        appUserService = new AppUserService(appUserRepository);
    }

    @Test
    public void canGetAllUsers() {
        AppUser appUser1 = AppUser.builder()
                .id(1L)
                .name("Chidi")
                .email("chidi@gmail.com")
                .booking(Booking.builder().id(1L).seatNumber(2).build())
                .build();
        appUserRepository.save(appUser1);
        AppUser appUser2 = AppUser.builder()
                .id(2L)
                .name("Chidii")
                .email("chidii@gmail.com")
                .booking(Booking.builder().id(2L).seatNumber(3).build())
                .build();
        appUserRepository.save(appUser2);

        when(appUserRepository.findAll()).thenReturn(List.of(appUser1,appUser2));

        List<AppUser> appUsers = appUserService.listAllUsers();

        verify(appUserRepository).findAll();
        assertEquals(2, appUsers.size());
    }

    @Test
    public void saveUserAndCreateBookingIsSuccessful() {
        AppUser appUser1 = AppUser.builder()
                .id(1L)
                .name("Chidi")
                .email("chidi@gmail.com")
                .booking(Booking.builder().id(1L).seatNumber(2).build())
                .build();
        appUserRepository.save(appUser1);

        when(appUserRepository.save(any())).thenReturn(appUser1);

         appUserService.signUpUser(appUser1);

         verify(appUserRepository, times(2)).save(any());
    }

    @Test
    public void saveUserAndCreateBookingWhenSeatNumberIsGreaterThanHundredIsUnsuccessful()
    throws IllegalArgumentException {
        AppUser appUser1 = AppUser.builder()
                .id(1L)
                .name("Chidi")
                .email("chidi@gmail.com")
                .booking(Booking.builder().id(1L).seatNumber(150).build())
                .build();
        appUserRepository.save(appUser1);

        assertThatThrownBy(() -> appUserService.signUpUser(appUser1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("only seat number 1 - 100 is available");
    }

    @Test
    public void deleteUserAndCreateBookingIsSuccessful() {
        AppUser appUser1 = AppUser.builder()
                .id(1L)
                .name("Chidi")
                .email("chidi@gmail.com")
                .booking(Booking.builder().id(1L).seatNumber(2).build())
                .build();
        appUserRepository.save(appUser1);

        appUserService.deleteUser(appUser1.getId());
        Optional<AppUser> deletedId = appUserRepository.findById(appUser1.getId());

        assertThat(deletedId).isEmpty();
    }

    @Test
    public void updateUserAndBookingIsSuccessful() {
        AppUser appUser1 = AppUser.builder()
                .id(1L)
                .name("Chidi")
                .email("chidi@gmail.com")
                .booking(Booking.builder().id(1L).seatNumber(2).build())
                .build();
        appUserRepository.save(appUser1);

        when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser1));
         appUserService.updateUser(1L, appUser1);
         appUser1.setEmail("Chidii@gmail.com");
         appUserRepository.save(appUser1);

        verify(appUserRepository, times(1)).findById(anyLong());
        assertThat(appUser1.getEmail()).isEqualTo("Chidii@gmail.com");
        assertThat(appUser1.getBooking()).hasFieldOrPropertyWithValue("seatNumber", 2);
    }

    @Test
    public void updateUserAndBookingWhenSeatNumberIsGreaterThanHundredIsUnsuccessful()
            throws IllegalArgumentException {
        AppUser appUser1 = AppUser.builder()
                .id(1L)
                .name("Chidi")
                .email("chidi@gmail.com")
                .booking(Booking.builder().id(1L).seatNumber(150).build())
                .build();
        appUserRepository.save(appUser1);

        assertThatThrownBy(() -> appUserService.updateUser(1L, appUser1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("%d does not exist", appUser1.getId()));
    }
}