package com.poseidon.app.http.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(LoginController.class)
class LoginControllerTest extends AbstractControllerTest {
    @Test
    void login() throws Exception {
        toMatchSnapshot(
                mockMvc.perform(get("/app/login")).andReturn()
        );
    }

    @Test
    void error() throws Exception {
        toMatchSnapshot(
                mockMvc.perform(get("/app/error")).andReturn()
        );
    }
}