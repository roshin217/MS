package com.itm.space.backendresources;

import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.util.RandomUserGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class ControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    RandomUserGenerator randomUserGenerator;

    @Test
    @WithMockUser(roles = "MODERATOR")
    void testCreateUser_noExistUser() throws Exception {

        String username = randomUserGenerator.generateUsername();
        String email = randomUserGenerator.generateEmail(username);
        String password = randomUserGenerator.generatePassword();
        String firstName = randomUserGenerator.generateFirstName();
        String lastName = randomUserGenerator.generateLastName();

        UserRequest userRequest = new UserRequest(
                username,
                email,
                password,
                firstName,
                lastName
        );
        mvc.perform(requestWithContent(post("/api/users"), userRequest))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void testCreateUser_existUser() throws Exception {
        UserRequest userRequest = new UserRequest(
                "test",
                "test@gmail.com",
                "test",
                "test",
                "test"
        );
        mvc.perform(requestWithContent(post("/api/users"), userRequest))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @WithMockUser(roles = "MODERATOR")
    void testGetUserById_exist() throws Exception {

        UUID userId = UUID.fromString("53aeaac8-cfd1-46f6-ab6b-2d718935852f");

       mvc.perform(requestToJson(get("/api/users/{id}", userId)))
                .andExpect(status().isOk());
    }


//    НЕ РАБОТАЕТ
    @Test
    @WithMockUser(roles = "MODERATOR")
    void testGetUserById_nonExist() throws Exception {

        UUID userId =UUID.randomUUID();

        mvc.perform(requestToJson(get("/api/users/{id}", userId)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void testHelloPage() throws Exception {
        mvc.perform(requestToJson(get("/api/users/hello")))
                .andExpect(status().isOk());
    }
}
