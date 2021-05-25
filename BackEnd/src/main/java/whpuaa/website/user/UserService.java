package whpuaa.website.user;

import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {
    /**
     * Get user id by username.
     * @param username The username.
     * @return The user id.
     * @throws UserNotExistException Thrown when user not exist.
     */
    long getUserId(String username) throws UserNotExistException;

    /**
     * Get user info by id.
     * @param id The user id.
     * @return The user info.
     * @throws UserNotExistException Thrown when user not exist.
     */
    UserInfo getUser(long id) throws UserNotExistException;

    /**
     * Get user info by username.
     * @param username The username.
     * @return The user info.
     * @throws UserNotExistException Thrown when user not exist.
     */
    UserInfo getUserByUsername(String username) throws UserNotExistException;

    /**
     * Get user list page by page.
     * @param page Page number, starts from 0.
     * @param numberPerPage Number of users per page.
     * @return The list of user info.
     */
    List<UserInfo> getUsers(long page, long numberPerPage);

    /**
     * Create a user.
     * @param username The username of new user.
     * @param password The password of new user.
     * @return The new user's info.
     * @throws UsernameConflictException Thrown when username already exists in another user.
     */
    UserInfo createUser(@NonNull String username, @NonNull String password) throws UsernameConflictException;

    /**
     * Modify a user.
     * @param id The user's id.
     * @param params The info to modify.
     * @return The new user info.
     * @throws UsernameConflictException Thrown when new username already exists in another user.
     * @throws InvalidOperationOnRootUserException Thrown when change root user's permission.
     */
    UserInfo modifyUser(long id, UserModifyParams params) throws UsernameConflictException, InvalidOperationOnRootUserException;

    /**
     * Remove a user.
     * @param id The user id.
     * @return True if user is deleted. False if user not exist.
     * @throws InvalidOperationOnRootUserException Thrown when removing root user.
     */
    boolean removeUser(long id) throws InvalidOperationOnRootUserException;

    /**
     * Given username and password, check its legality and return its user info if correct.
     * @param username The username.
     * @param password The password.
     * @return The user info.
     * @throws BadCredentialException Thrown if username or password is wrong.
     */
    UserInfo verifyUserCredential(String username, String password) throws BadCredentialException;
}
