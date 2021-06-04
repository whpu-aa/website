package whpuaa.website.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import whpuaa.website.controller.model.HttpCreateTokenRequest;
import whpuaa.website.controller.model.HttpCreateTokenResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Helper {
    private Helper() {

    }

    public static String createToken(MockMvc mvc, ObjectMapper objectMapper, String username, String password) throws Exception {
        MvcResult result = mvc.perform(post("/api/token/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new HttpCreateTokenRequest(username, password, 1.))))
                .andExpect(status().isOk())
                .andReturn();

        return objectMapper.readValue(result.getResponse().getContentAsByteArray(), HttpCreateTokenResult.class).getToken();
    }
}
