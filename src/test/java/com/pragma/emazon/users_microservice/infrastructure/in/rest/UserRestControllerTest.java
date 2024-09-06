package com.pragma.emazon.users_microservice.infrastructure.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emazon.users_microservice.application.dto.request.CreateUserRequest;
import com.pragma.emazon.users_microservice.application.handler.UserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.context.support.TestExecutionEvent.TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserHandler userHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private CreateUserRequest createUserRequest;

    @BeforeEach
    void setUp() {

        createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Maria");
        createUserRequest.setLastName("Gonzalez");
        createUserRequest.setDocumentId("123456789");
        createUserRequest.setPhoneNumber("+456123456789");
        createUserRequest.setBirthDate("1990-01-01");
        createUserRequest.setEmail("assistant@warehouse.com");
        createUserRequest.setPassword("password");
    }

    @Test
    @WithMockUser(authorities = {}, password = "password", roles = {"ADMIN"}, setupBefore = TEST_METHOD, username = "", value = "user")
    void createWarehouseAssistantUserShouldReturnCreatedWhenRequestIsValid() throws Exception {
        mockMvc.perform(post("/api/v1/users/warehouse-assistants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequest)))
                        .andExpect(status().isCreated());

        verify(userHandler).createWarehouseAssistantUser(Mockito.any(CreateUserRequest.class));
    }
}
