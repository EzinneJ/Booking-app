package com.ezinne.Bookingapp.services;

import com.ezinne.Bookingapp.model.AppUser;
import com.ezinne.Bookingapp.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public String signUpUser(AppUser appUser) {
        if (appUser.getBooking().getSeatNumber() > 100){
             throw new IllegalArgumentException("only seat number 1 - 100 is available");
        } else {
            appUserRepository.save(appUser);
            Integer assignedSeatNumber = appUser.getBooking().getSeatNumber();
            return String.format("Congratulation!!! User has signed Up successfully and " +
                    "seat %d has been assigned to you", assignedSeatNumber);
        }
    }

    public List<AppUser> listAllUsers() {
        return appUserRepository.findAll();
    }

    public String deleteUser(Long userId) {
        appUserRepository.deleteById(userId);
        return String.format("User with %d has been deleted", userId);
    }

}




