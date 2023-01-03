package com.ezinne.Bookingapp.controllers;

import com.ezinne.Bookingapp.BookingAppApplication;
import com.ezinne.Bookingapp.model.AppUser;
import com.ezinne.Bookingapp.model.Booking;
import com.ezinne.Bookingapp.services.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BookingAppApplication.class)
@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest
class AppUserControllerIT {

    @Autowired
    private AppUserService appUserService;

    static DockerImageName myImage = DockerImageName.parse("postgresql:latest").asCompatibleSubstituteFor("postgres");

    @Container
    private static PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer(myImage);


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenGetURI_whenMockMVC_thenVerifyResponse() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("http://localhost:8081/api/v1/user"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Test
    public void givenPostURI_whenMockMVC_thenUserIsCreated() throws Exception {
        AppUser appUser1 = AppUser.builder()
                .id(1L)
                .name("Chidi")
                .email("chidi@gmail.com")
                .booking(Booking.builder().id(1L).seatNumber(2).build())
                .build();
        appUserService.signUpUser(appUser1);

        MvcResult mvcResult = this.mockMvc.perform(post("http://localhost:8081/api/v1/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appUser1)))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals("text/plain;charset=UTF-8",
                mvcResult.getResponse().getContentType());
    }

}