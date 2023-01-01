package com.ezinne.Bookingapp.controllers;

import com.ezinne.Bookingapp.model.AppUser;
import com.ezinne.Bookingapp.model.Booking;
import com.ezinne.Bookingapp.services.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("signup")
    @Operation(tags = "Sign up as a user")
    public String signUpUser(@RequestBody AppUser user) {
      return appUserService.signUpUser(user);
    }

    @GetMapping
    @Operation(tags = "Get a list of all users")
    public List<AppUser> listAllUsers() {
       return appUserService.listAllUsers();
    }

    @DeleteMapping("{userId}")
    @Operation(tags = "delete a user via userId")
    public String deleteUser(@PathVariable Long userId) {
      return   appUserService.deleteUser(userId);
    }

    @PutMapping("{userId}")
    @Operation(tags = "Update app user details")
    public String updateUser(@PathVariable Long userId, @RequestBody AppUser newAppUserDetails) {
        return appUserService.updateUser(userId, newAppUserDetails);
    }

}
