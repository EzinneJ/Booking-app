package com.ezinne.Bookingapp.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef {

    @Given("I have a valid endpoint")
    public void i_have_a_valid_endpoint() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("I sign up on the server using DataTables")
    public void i_sign_up_on_the_server_using_data_tables(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
    }

    @Then("return statusCode {int}")
    public void return_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
    }
}
