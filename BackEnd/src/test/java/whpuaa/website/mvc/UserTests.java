package whpuaa.website.mvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import whpuaa.website.controller.model.HttpPostUserRequest;
import whpuaa.website.user.UserInfo;
import whpuaa.website.user.UserPermissions;
import whpuaa.website.user.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTests extends MvcTestBase {
    @Autowired
    private UserService userService;

    private List<UserInfo> users;

    private String adminToken;
    private String userToken;

    @BeforeEach
    public void setupUsers() throws Exception {
        users = new ArrayList<>();
        for (int i = 1; i <= 35; i++) {
            UserInfo user = userService.createUser(String.valueOf(i), "a-password");
            users.add(user);
        }

        adminToken = Helper.createToken(mvc, objectMapper, "root", "rootroot");
        userToken = Helper.createToken(mvc, objectMapper, "1", "a-password");
    }

    @Test
    public void listShouldWork() throws Exception {
        mvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalCount", equalTo(36)))
                .andExpect(jsonPath("items", hasSize(20)));

        mvc.perform(get("/api/users?page=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalCount", equalTo(36)))
                .andExpect(jsonPath("items", hasSize(16)));

        mvc.perform(get("/api/users?page=1&numberPerPage=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalCount", equalTo(36)))
                .andExpect(jsonPath("items", hasSize(10)));
    }

    @Test
    public void getShouldWork() throws Exception {
        mvc.perform(get("/api/users/" + users.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username", equalTo(users.get(0).getUsername())));

        mvc.perform(get("/api/users/1111111111"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postShouldWork() throws Exception {
        List<String> permission = new ArrayList<>();
        permission.add(UserPermissions.NEWS_MANAGEMENT);

        Map<String, String> details = new HashMap<>();
        details.put("a-key", "a-value");

        HttpPostUserRequest request = new HttpPostUserRequest("a-username", "a-password", "a-name", permission, "a-description", details);

        mvc.perform(post("/api/users?access_token=" + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", notNullValue()))
                .andExpect(jsonPath("username", equalTo("a-username")))
                .andExpect(jsonPath("name", equalTo("a-name")))
                .andExpect(jsonPath("permission", hasSize(1)))
                .andExpect(jsonPath("permission[0]", equalTo(UserPermissions.NEWS_MANAGEMENT)))
                .andExpect(jsonPath("details.a-key", equalTo("a-value")));

    }

    @Test
    public void postPermissionTest() throws Exception {
        List<String> permission = new ArrayList<>();
        permission.add(UserPermissions.NEWS_MANAGEMENT);

        Map<String, String> details = new HashMap<>();
        details.put("a-key", "a-value");

        HttpPostUserRequest request = new HttpPostUserRequest("a-username", "a-password", "a-name", permission, "a-description", details);

        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());

        mvc.perform(post("/api/users?access_token=" + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void postInvalidModelError() throws Exception {
        mvcPost("/api/users?access_token=" + adminToken, new HttpPostUserRequest(null, "a-password", null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        mvcPost("/api/users?access_token=" + adminToken, new HttpPostUserRequest("", "a-password", null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        mvcPost("/api/users?access_token=" + adminToken, new HttpPostUserRequest("!", "a-password", null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        mvcPost("/api/users?access_token=" + adminToken, new HttpPostUserRequest("a-username", null, null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        mvcPost("/api/users?access_token=" + adminToken, new HttpPostUserRequest("a-username", "", null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        mvcPost("/api/users?access_token=" + adminToken, new HttpPostUserRequest("a-username", "short", null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        List<String> badPermission = new ArrayList<>();
        badPermission.add("invalid");
        mvcPost("/api/users?access_token=" + adminToken, new HttpPostUserRequest("a-username", "a-password", null, badPermission, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
    }

    @Test
    public void postUsernameConflict() throws Exception {
        mvcPost("/api/users?access_token=" + adminToken, new HttpPostUserRequest("root", "a-password", null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100201)));
    }
}
