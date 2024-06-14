package com.maverickshube.maverickshube.services;

import com.maverickshube.maverickshube.dtos.CreateUserRequest;
import com.maverickshube.maverickshube.dtos.CreateUserResponse;
import com.maverickshube.maverickshube.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Test
    public void registerTest(){
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("1234");

        CreateUserResponse response = userService.register(request);
        assertNotNull(response);
        assertEquals("test@example.com", response.getEmail());
    }
    @Test
    @DisplayName("test that user can be retrieve by id")
    @Sql(scripts = {"/db/data.sql"})
    public void testGetUserById(){
        User user = userService.getUserById(200L);
        assertNotNull(user);
    }
}
