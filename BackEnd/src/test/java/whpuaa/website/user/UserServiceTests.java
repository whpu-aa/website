package whpuaa.website.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import whpuaa.website.util.ListWithTotalCount;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void getUserIdShouldWork() throws UserNotExistException {
        assertThat(userService.getUserId("root")).isEqualTo(1);
        assertThatThrownBy(() -> userService.getUserId("a-user-not-exist")).isInstanceOf(UserNotExistException.class);
    }

    private void assertRootUser(UserInfo user) {
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getUsername()).isEqualTo("root");
        assertThat(user.getName()).isEmpty();
        assertThat(user.getDescription()).isEmpty();
        assertThat(user.getPermission()).isEqualTo(new ArrayList<>(UserPermissions.PERMISSION_SET));
        assertThat(user.getDetails()).isEmpty();
    }

    @Test
    public void getUserShouldWork() throws UserNotExistException {
        assertRootUser( userService.getUser(1));
        assertThatThrownBy(() -> userService.getUser(2)).isInstanceOf(UserNotExistException.class);
    }

    @Test
    public void getUserByUsernameShouldWork() throws UserNotExistException {
        assertRootUser( userService.getUserByUsername("root"));
        assertThatThrownBy(() -> userService.getUserByUsername("a-not-exist-user")).isInstanceOf(UserNotExistException.class);
    }

    @Test
    public void getUsersShouldWork() {
        ListWithTotalCount<UserInfo> result = userService.getUsers(0, 10);
        assertThat(result.getTotalCount()).isEqualTo(1);
        assertThat(result.getList()).hasSize(1);
        assertRootUser(result.getList().get(0));
    }

    @Test
    public void getUsersShouldWorkWithMoreUser() throws UsernameConflictException {
        for (int i = 1; i <= 24; i++) {
            userService.createUser(String.valueOf(i), String.valueOf(i));
        }

        ListWithTotalCount<UserInfo> result1 = userService.getUsers(0, 10);
        assertThat(result1.getTotalCount()).isEqualTo(25);
        assertThat(result1.getList()).hasSize(10);

        ListWithTotalCount<UserInfo> result2 = userService.getUsers(2, 10);
        assertThat(result2.getTotalCount()).isEqualTo(25);
        assertThat(result2.getList()).hasSize(5);

        ListWithTotalCount<UserInfo> result3 = userService.getUsers(5, 10);
        assertThat(result3.getTotalCount()).isEqualTo(25);
        assertThat(result3.getList()).hasSize(0);

        ListWithTotalCount<UserInfo> result4 = userService.getUsers(0, 30);
        assertThat(result4.getTotalCount()).isEqualTo(25);
        assertThat(result4.getList()).hasSize(25);
    }
}
