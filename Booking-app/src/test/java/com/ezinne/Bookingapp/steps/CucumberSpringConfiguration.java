package com.ezinne.Bookingapp.steps;

import com.ezinne.Bookingapp.BookingAppApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration
@SpringBootTest(classes = BookingAppApplication.class)
public class CucumberSpringConfiguration {
}
