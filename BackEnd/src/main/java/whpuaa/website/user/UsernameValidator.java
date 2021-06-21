package whpuaa.website.user;

import com.google.common.base.CharMatcher;
import org.springframework.lang.Nullable;

import javax.annotation.CheckReturnValue;
import java.util.function.Consumer;

public class UsernameValidator {
    /**
     * Validate a username.
     *
     * @param username  The username.
     * @param allowNull Whether null value is permitted.
     * @return An error message. Or null if no error.
     */
    @Nullable
    @CheckReturnValue
    public String validate(@Nullable String username, boolean allowNull) {
        if (username == null) {
            if (!allowNull) {
                return "Username can't be null.";
            }
        } else {
            if (username.isEmpty()) {
                return "Username can't be empty.";
            }

            if (username.length() > 20) {
                return "Username can't be longer than 20.";
            }

            if (!CharMatcher.inRange('a', 'z')
                    .or(CharMatcher.inRange('A', 'Z'))
                    .or(CharMatcher.inRange('0', '9'))
                    .or(CharMatcher.is('-'))
                    .or(CharMatcher.is('_')).matchesAllOf(username)) {
                return "Username can only contain a-z, A-Z, 0-9, _, -.";
            }
        }

        return null;
    }

    /**
     * Validate username. If it is illegal, throw {@link IllegalArgumentException}.
     * @param username The username to validate.
     * @param allowNull Whether null is permitted.
     */
    public void validateAndThrow(@Nullable String username, boolean allowNull) {
        String message = validate(username, allowNull);
        if (message == null) return;
        throw new IllegalArgumentException(message);
    }

    public void validateAndDoIfFailed(@Nullable String username, boolean allowNull, Consumer<String> action) {
        String message = validate(username, allowNull);
        if (message == null) return;
        action.accept(message);
    }
}