package whpuaa.website.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class MvcTestBase {
    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected String createToken(String username, String password) throws Exception {
        return Helper.createToken(mvc, objectMapper, username, password);
    }

    protected ResultActions mvcGet(String url) throws Exception {
        return mvc.perform(get(url));
    }

    protected ResultActions mvcPost(String url, Object json) throws Exception {
        return mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(json)));
    }
}
