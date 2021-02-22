package com.poseidon.app.http.controller;

import com.poseidon.app.model.Role;
import com.poseidon.app.model.User;
import com.poseidon.app.persistence.entity.UserEntity;
import com.poseidon.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(UserController.class)
class UserControllerTest extends AbstractCrudControllerTest<UserService, User, UserEntity, Long> {
    @Autowired
    private UserController userController;

    public UserControllerTest() {
        super("/user", User.class, Long.class);
    }

    @Override
    protected UserService getService() {
        return userService;
    }

    @Override
    protected User getModelA() {
        return User.builder()
                .id(1L)
                .fullname("fullname")
                .username("username")
                .password("passwordA1$")
                .role(Role.USER)
                .build();
    }

    @Override
    protected User getModelB() {
        return User.builder()
                .id(32402L)
                .fullname("yJnkKqFBtY")
                .username("xyuwd3sbk9")
                .password("passwordA1$")
                .role(Role.ADMIN)
                .build();
    }

    @Test
    void filterIn() {
        User user = new User();

        user.setPassword("xxx");
        userController.filterIn(user);
        assertTrue(user.getPassword().startsWith("$2b$"));
    }

    @Test
    void filterOut() {
        User user = new User();

        user.setPassword("");
        userController.filterOut(user);
        assertEquals("", user.getPassword());

        user.setPassword("xxx");
        userController.filterOut(user);
        assertEquals("", user.getPassword());

        user.setPassword(null);
        userController.filterOut(user);
        assertNull(user.getPassword());
    }
}
