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
        customer.setCpf("001.002.003-10");

        String payload = objectMapper.writeValueAsString(customer);
        this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("name", equalTo("Gandalf The Gray")))
                .andExpect(MockMvcResultMatchers.jsonPath("cpf", equalTo("001.002.003-10")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldUpdateACustomer() throws Exception {
        CustomerMock customer = new CustomerMock();
        customer.setName("Tony (The Iron man) Stark");
        customer.setCpf("011.022.033-10");

        String payload = objectMapper.writeValueAsString(customer);
        this.mvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/4647765e-6491-47aa-82bd-c08f87439421").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name", equalTo("Tony (The Iron man) Stark")))
                .andExpect(MockMvcResultMatchers.jsonPath("cpf", equalTo("011.022.033-10")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldDeleteACustomer() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/72c5d8c5-539a-42f0-8a01-5c4ec7cdb924"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
