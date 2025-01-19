package com.example.imgur;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.imgur.controller.UserController;
import com.example.imgur.model.Users;
import com.example.imgur.service.ImgurService;
import com.example.imgur.service.UserService;

@SpringBootTest
class ImgurApplicationTests {

	 @Mock
	    private UserService userService;

	    @Mock
	    private ImgurService imgurService;

	    @InjectMocks
	    private UserController userController;

	    @Test
	    public void testRegisterUser() {
	        Users user = new Users("john", "password123");
	        Mockito.when(userService.register(user)).thenReturn("User registered successfully.");
	        String response = userController.registerUser(user);
	        assertEquals("User registered successfully.", response);
	    }

	    @Test
	    public void testLoginUser() {
	        String response = userController.loginUser("john", "password123");
	        assertEquals("Login successful.", response);
	    }

}
