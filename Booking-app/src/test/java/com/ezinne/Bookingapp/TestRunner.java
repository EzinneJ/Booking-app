package com.ezinne.Bookingapp;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.ezinne.Bookingapp.steps"
        )
public class TestRunner {
}
