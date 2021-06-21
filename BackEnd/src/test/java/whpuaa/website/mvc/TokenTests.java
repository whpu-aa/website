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
import org.springframework.test.web.servlet.MvcResult;
import whpuaa.website.controller.model.HttpCreateTokenRequest;
import whpuaa.website.controller.model.HttpCreateTokenResult;
import whpuaa.website.controller.model.HttpRevokeTokenRequest;
import whpuaa.website.controller.model.HttpVerifyTokenRequest;
import whpuaa.website.user.UserService;

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

    @Autowired
    private UserService userService;

    @Test
    public void createTokenShouldWork() throws Exception {
        mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpCreateTokenRequest("root", "rootroot", 30.))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", not(emptyString())))
                .andExpect(jsonPath("user.id", equalTo(1)));
    }

    void createTokenAssertError(HttpCreateTokenRequest request, int statusCode, int errorCode) throws Exception {
        mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is(statusCode))
                .andExpect(jsonPath("code", equalTo(errorCode)));
    }

    void createTokenAssertInvalidModel(HttpCreateTokenRequest request) throws Exception {
        createTokenAssertError(request, 400, 100000);
    }

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

    @Test
    void verifyTokenShouldWork() throws Exception {
        MvcResult mvcResult = mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpCreateTokenRequest("root", "rootroot", 30.))))
                .andExpect(status().isOk())
                .andReturn();

        HttpCreateTokenResult result = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), HttpCreateTokenResult.class);


        mvc.perform(post("/api/token/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpVerifyTokenRequest(result.getToken()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("user.id", equalTo(1)));
    }

    @Test
    void verifyTokenError() throws Exception {
        mvc.perform(post("/api/token/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpVerifyTokenRequest(null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", equalTo(100000)));

        mvc.perform(post("/api/token/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpVerifyTokenRequest("a-token"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", equalTo(100102)));

        MvcResult mvcResult = mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpCreateTokenRequest("root", "rootroot",
                        1.0 / 24 / 3600)))) // one second
                .andExpect(status().isOk())
                .andReturn();

        HttpCreateTokenResult result = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), HttpCreateTokenResult.class);

        Thread.sleep(1000);

        mvc.perform(post("/api/token/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpVerifyTokenRequest(result.getToken()))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", equalTo(100103)));
    }

    @Test
    public void revokeTokenShouldWork() throws Exception {
        MvcResult mvcResult = mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpCreateTokenRequest("root", "rootroot", 30.))))
                .andExpect(status().isOk())
                .andReturn();

        HttpCreateTokenResult result = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), HttpCreateTokenResult.class);

        mvc.perform(post("/api/token/revoke")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpRevokeTokenRequest(result.getToken())))
                .param("access_token", result.getToken()))
                .andExpect(status().isOk());

        mvc.perform(post("/api/token/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpVerifyTokenRequest(result.getToken()))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", equalTo(100102)));
    }

    @Test
    public void revokeTokenInvalidModelError() throws Exception {
        MvcResult mvcResult = mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpCreateTokenRequest("root", "rootroot", 30.))))
                .andExpect(status().isOk())
                .andReturn();

        String token = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), HttpCreateTokenResult.class).getToken();

        mvc.perform(post("/api/token/revoke")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpRevokeTokenRequest(null)))
                .param("access_token", token))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", equalTo(100000)));

        mvc.perform(post("/api/token/revoke")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpRevokeTokenRequest("")))
                .param("access_token", token))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", equalTo(100000)));
    }

    @Test
    public void revokeTokenSecurityError() throws Exception {
        mvc.perform(post("/api/token/revoke")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpRevokeTokenRequest("a-token"))))
                .andExpect(status().isUnauthorized());

        mvc.perform(post("/api/token/revoke")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpRevokeTokenRequest("a-token")))
                .param("access_token", "a-invalid-token"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void revokeOthersTokenShouldHaveNoEffect() throws Exception {
        userService.createUser("user", "password");

        MvcResult mvcResult1 = mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpCreateTokenRequest("root", "rootroot", 30.))))
                .andExpect(status().isOk())
                .andReturn();

        String rootToken = objectMapper.readValue(mvcResult1.getResponse().getContentAsByteArray(), HttpCreateTokenResult.class).getToken();

        MvcResult mvcResult2 = mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpCreateTokenRequest("user", "password", 30.))))
                .andExpect(status().isOk())
                .andReturn();

        String userToken = objectMapper.readValue(mvcResult2.getResponse().getContentAsByteArray(), HttpCreateTokenResult.class).getToken();

        mvc.perform(post("/api/token/revoke")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpRevokeTokenRequest(rootToken)))
                .param("access_token", userToken))
                .andExpect(status().isOk());

        mvc.perform(post("/api/token/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpVerifyTokenRequest(rootToken))))
                .andExpect(status().isOk());
    }
}
