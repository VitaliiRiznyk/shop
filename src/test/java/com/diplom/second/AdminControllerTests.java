package com.diplom.second;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("Vital")
    public void getAllPhones() throws Exception {
        mockMvc.perform(get("/product/phones")).andExpect(authenticated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("Vital")
    public void getFirstUser() throws Exception {
        mockMvc.perform(get("/users/1").with(csrf()))
                .andExpect(status().isOk()).andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithUserDetails("Vital")
    public void getAllOrders() throws Exception {
        mockMvc.perform(get("/orders").with(csrf()))
                .andExpect(status().isOk()).andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

}