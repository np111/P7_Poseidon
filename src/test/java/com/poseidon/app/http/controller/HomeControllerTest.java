package com.poseidon.app.http.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(HomeController.class)
class HomeControllerTest extends AbstractControllerTest {
    @Test
    void home() throws Exception {
        toMatchSnapshot(
                mockMvc.perform(get("/")).andReturn()
        );
    }

    @Test
    void adminHome() throws Exception {
        toMatchSnapshot(
                mockMvc.perform(get("/admin/home")).andReturn()
        );
    }
}