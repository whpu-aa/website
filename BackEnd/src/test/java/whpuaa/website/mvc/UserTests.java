package whpuaa.website.mvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import whpuaa.website.controller.model.HttpPatchUserRequest;
import whpuaa.website.controller.model.HttpPostUserRequest;
import whpuaa.website.user.UserInfo;
import whpuaa.website.user.UserPermissions;
import whpuaa.website.user.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

        adminToken = createToken("root", "rootroot");
        userToken = createToken("1", "a-password");
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

    @Test
    public void patchShouldWorkAsUser() throws Exception {
        long userId = users.get(0).getId();
        String url = "/api/users/" + userId + "?access_token=" + userToken;

        Map<String, String> mockDetails = new HashMap<>();
        mockDetails.put("a-key", "a-value");
        mvcPatch(url, new HttpPatchUserRequest(null, null, null, null, "a-description", mockDetails))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description", equalTo("a-description")))
                .andExpect(jsonPath("details", both(aMapWithSize(1)).and(hasEntry("a-key", "a-value"))));

        mockDetails.put("a-key", null);
        mockDetails.put("b-key", "b-value");
        mvcPatch(url, new HttpPatchUserRequest(null, null, null, null, null, mockDetails))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description", equalTo("a-description")))
                .andExpect(jsonPath("details", both(aMapWithSize(1)).and(hasEntry("b-key", "b-value"))));
    }

    @Test
    public void patchShouldWorkAsAdmin() throws Exception {
        long userId = users.get(0).getId();
        String url = "/api/users/" + userId + "?access_token=" + adminToken;

        List<String> mockPermission = new ArrayList<>();
        mockPermission.add(UserPermissions.NEWS_MANAGEMENT);
        Map<String, String> mockDetails = new HashMap<>();
        mockDetails.put("a-key", "a-value");
        mvcPatch(url, new HttpPatchUserRequest("a-username", "a-password", "a-name", mockPermission, "a-description", mockDetails))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username", equalTo("a-username")))
                .andExpect(jsonPath("name", equalTo("a-name")))
                .andExpect(jsonPath("description", equalTo("a-description")))
                .andExpect(jsonPath("permission", both(iterableWithSize(1)).and(hasItem(UserPermissions.NEWS_MANAGEMENT))))
                .andExpect(jsonPath("details", both(aMapWithSize(1)).and(hasEntry("a-key", "a-value"))));
    }

    @Test
    public void patchPermissionCheck() throws Exception {
        long userId = users.get(0).getId();
        String urlBase = "/api/users/" + userId;
        String urlAsUser = urlBase + "?access_token=" + userToken;

        mvcPatch(urlBase, new HttpPatchUserRequest()).andExpect(status().isUnauthorized());

        mvcPatch(urlAsUser, new HttpPatchUserRequest("a-username", null, null, null, null, null))
                .andExpect(status().isForbidden());
        mvcPatch(urlAsUser, new HttpPatchUserRequest(null, "a-password", null, null, null, null))
                .andExpect(status().isForbidden());
        mvcPatch(urlAsUser, new HttpPatchUserRequest(null, null, "a-name", null, null, null))
                .andExpect(status().isForbidden());
        List<String> mockPermission = new ArrayList<>();
        mockPermission.add(UserPermissions.NEWS_MANAGEMENT);
        mvcPatch(urlAsUser, new HttpPatchUserRequest(null, null, null, mockPermission, null, null))
                .andExpect(status().isForbidden());

        mvcPatch("/api/users/" + users.get(1).getId() + "?access_token=" + userToken, new HttpPatchUserRequest())
                .andExpect(status().isForbidden());
    }

    @Test
    public void patchInvalidModelError() throws Exception {
        long userId = users.get(0).getId();
        String url = "/api/users/" + userId + "?access_token=" + adminToken;

        mvcPatch(url, new HttpPatchUserRequest("", null, null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        mvcPatch(url, new HttpPatchUserRequest("!", null, null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        mvcPatch(url, new HttpPatchUserRequest(null, "", null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        mvcPatch(url, new HttpPatchUserRequest(null, "short", null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
        List<String> badPermission = new ArrayList<>();
        badPermission.add("invalid");
        mvcPatch(url, new HttpPatchUserRequest(null, null, null, badPermission, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100000)));
    }

    @Test
    public void patchUsernameConflictError() throws Exception {
        long userId = users.get(0).getId();
        String url = "/api/users/" + userId + "?access_token=" + adminToken;

        mvcPatch(url, new HttpPatchUserRequest("root", null, null, null, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100201)));
    }

    @Test
    public void patchRootUserPermissionError() throws Exception {
        String url = "/api/users/1?access_token=" + adminToken;
        List<String> mockPermission = new ArrayList<>();
        mockPermission.add(UserPermissions.NEWS_MANAGEMENT);
        mvcPatch(url, new HttpPatchUserRequest(null, null, null, mockPermission, null, null))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("code", equalTo(100203)));
    }

    @Test
    public void deleteShouldWork() throws Exception {
        long userId = users.get(0).getId();
        String url = "/api/users/" + userId + "?access_token=" + adminToken;

        mvcDelete(url).andExpect(status().isOk());

        mvcDelete(url).andExpect(status().isOk());
    }

    @Test
    public void deletePermissionCheck() throws Exception {
        long userId = users.get(0).getId();
        String baseUrl = "/api/users/" + userId;
        String url = baseUrl + "?access_token=" + userToken;

        mvcDelete(baseUrl).andExpect(status().isUnauthorized());
        mvcDelete(url).andExpect(status().isForbidden());
    }

    @Test
    public void deleteRootUserError() throws Exception {
        String url = "/api/users/1?access_token=" + adminToken;

        mvcDelete(url).andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", equalTo(100203)));
    }
}
