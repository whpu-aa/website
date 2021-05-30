package whpuaa.website.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import whpuaa.website.util.ListWithTotalCount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        assertRootUser(userService.getUser(1));
        assertThatThrownBy(() -> userService.getUser(2)).isInstanceOf(UserNotExistException.class);
    }

    @Test
    public void getUserByUsernameShouldWork() throws UserNotExistException {
        assertRootUser(userService.getUserByUsername("root"));
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
            userService.createUser(String.valueOf(i), "a-password");
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

    @Test
    public void createUserShouldWork() throws UsernameConflictException, UserNotExistException {
        UserInfo user = userService.createUser("a-user", "a-password");
        assertThat(user.getId()).isNotZero();
        assertThat(user.getUsername()).isEqualTo("a-user");
        assertThat(user.getName()).isEmpty();
        assertThat(user.getDescription()).isEmpty();
        assertThat(user.getPermission()).isEmpty();
        assertThat(user.getDetails()).isEmpty();

        // should not throw
        userService.getUser(user.getId());

        assertThat(userService.getUserId("a-user")).isEqualTo(user.getId());

        assertThatThrownBy(() -> userService.createUser("a-user", "another-password")).isInstanceOf(UsernameConflictException.class);
    }

    @Test
    public void modifyUserShouldWork() throws UsernameConflictException, InvalidOperationOnRootUserException, UserNotExistException {
        UserInfo old = userService.createUser("a-user", "a-password");

        Map<String, String> details = new HashMap<>();
        details.put("a-detail", "a-value");
        details.put("b-detail", "b-value");
        details.put("c-detail", "c-value");

        UserInfo user = userService.modifyUser(old.getId(), new UserModifyParams(
                "another-username", "a-name", "another-password", "a-description",
                new ArrayList<>(UserPermissions.PERMISSION_SET), details));

        assertThat(user.getId()).isEqualTo(old.getId());
        assertThat(user.getUsername()).isEqualTo("another-username");
        assertThat(user.getName()).isEqualTo("a-name");
        assertThat(user.getDescription()).isEqualTo("a-description");
        assertThat(user.getPermission()).isEqualTo(new ArrayList<>(UserPermissions.PERMISSION_SET));
        assertThat(user.getDetails()).isEqualTo(details);

        Map<String, String> newDetails = new HashMap<>();
        newDetails.put("a-detail", null);
        newDetails.put("b-detail", "another-value");

        UserInfo newUser = userService.modifyUser(old.getId(), new UserModifyParams(
                null, null, null, null, null, newDetails));

        assertThat(newUser.getId()).isEqualTo(old.getId());
        assertThat(newUser.getUsername()).isEqualTo("another-username");
        assertThat(newUser.getName()).isEqualTo("a-name");
        assertThat(newUser.getDescription()).isEqualTo("a-description");
        assertThat(newUser.getPermission()).isEqualTo(new ArrayList<>(UserPermissions.PERMISSION_SET));
        assertThat(newUser.getDetails()).hasSize(2);
        assertThat(newUser.getDetails().get("b-detail")).isEqualTo("another-value");
        assertThat(newUser.getDetails().get("c-detail")).isEqualTo("c-value");
    }

    @Test
    public void modifyUserThrowException() throws UsernameConflictException {
        UserModifyParams params = new UserModifyParams();
        params.setPermission(new ArrayList<>(UserPermissions.PERMISSION_SET));
        assertThatThrownBy(() -> userService.modifyUser(1, params)).isInstanceOf(InvalidOperationOnRootUserException.class);
        assertThatThrownBy(() -> userService.modifyUser(1234, params)).isInstanceOf(UserNotExistException.class);

        params.setUsername("root");
        UserInfo user = userService.createUser("a-user", "a-password");
        assertThatThrownBy(() -> userService.modifyUser(user.getId(), params)).isInstanceOf(UsernameConflictException.class);
    }

    @Test
    public void removeUserShouldWork() throws UsernameConflictException, InvalidOperationOnRootUserException {
        assertThatThrownBy(() -> userService.removeUser(1)).isInstanceOf(InvalidOperationOnRootUserException.class);

        UserInfo user = userService.createUser("a-user", "a-password");
        assertThat(userService.removeUser(user.getId())).isTrue();

        assertThat(userService.removeUser(1234)).isFalse();
    }

    @Test
    public void verifyUserCredentialShouldWork() throws BadCredentialException, UserNotExistException {
        assertThatThrownBy(() -> userService.verifyUserCredential("a-user", "a-password")).isInstanceOf(UserNotExistException.class);
        assertThatThrownBy(() -> userService.verifyUserCredential("root", "a-password")).isInstanceOf(BadCredentialException.class);

        UserInfo user = userService.verifyUserCredential("root", "rootroot");
        assertRootUser(user);
    }
}
