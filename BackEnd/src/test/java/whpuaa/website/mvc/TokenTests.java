package whpuaa.website.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import whpuaa.website.controller.model.HttpCreateTokenRequest;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class TokenTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTokenShouldWork() throws Exception {
        mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpCreateTokenRequest("root", "rootroot", 30.))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", not(emptyString())))
                .andExpect(jsonPath("user.id", is(1)));
    }

    void createTokenAssertError(HttpCreateTokenRequest request, int statusCode, int errorCode) throws Exception {
        mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is(statusCode))
                .andExpect(jsonPath("code", is(errorCode)));
    }

    void createTokenAssertInvalidModel(HttpCreateTokenRequest request) throws Exception {
        createTokenAssertError(request, 400, 100000);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void createTokenError() throws Exception {
        createTokenAssertInvalidModel(new HttpCreateTokenRequest(null, "rootroot", null));
        createTokenAssertInvalidModel(new HttpCreateTokenRequest("root", null, null));
        createTokenAssertInvalidModel(new HttpCreateTokenRequest("", "rootroot", null));
        createTokenAssertInvalidModel(new HttpCreateTokenRequest("!!!", "rootroot", null));
        createTokenAssertInvalidModel(new HttpCreateTokenRequest("root", "", null));
        createTokenAssertInvalidModel(new HttpCreateTokenRequest("root", "rootroot", -1.0));
        createTokenAssertInvalidModel(new HttpCreateTokenRequest("root", "rootroot", 366.0));

        createTokenAssertError(new HttpCreateTokenRequest("root", "a-password", null), 400, 100101);
    }
}
