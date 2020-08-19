package com.rafaelhayashi.brasilprev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaelhayashi.brasilprev.model.CustomerMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerApiDtoTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAListOfCustomers() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/v1/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldCreateACustomer() throws Exception {

        CustomerMock customer = new CustomerMock();
        customer.setName("Gandalf The Gray");
        customer.setCpf("09876543210");

        String payload = objectMapper.writeValueAsString(customer);
        this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("name", equalTo("Gandalf The Gray")))
                .andExpect(MockMvcResultMatchers.jsonPath("cpf", equalTo("09876543210")))
                .andDo(MockMvcResultHandlers.print());
    }
}
