package whpuaa.website.user;

import com.google.common.base.CharMatcher;
import org.springframework.lang.Nullable;

public class PasswordValidator {
    /**
     * Validate a password.
     *
     * @param password  The password.
     * @param allowNull Whether null value is permitted.
     * @return An error message. Or null if no error.
     */
    @Nullable
    public String Validate(@Nullable String password, boolean allowNull) {
        if (password == null) {
            if (!allowNull) {
                return "Password can't be null.";
            }
        } else {
            if (password.isEmpty()) {
                return "Password can't be empty.";
            }

            if (password.length() < 6) {
                return "Password can't shorter than 6.";
            }

            if (password.length() > 20) {
                return "Password can't be longer than 20.";
            }

            if (!CharMatcher.ascii().and(CharMatcher.whitespace().negate()).matchesAllOf(password)) {
                return "Password can only consists of ascii character and not space.";
            }
        }

        return null;
    }

    /**
     * Validate password. If it is illegal, throw {@link IllegalArgumentException}.
     * @param password The username to validate.
     * @param allowNull Whether null is permitted.
     */
    public void ValidateAndThrow(@Nullable String password, boolean allowNull) {
        String message = Validate(password, allowNull);
        if (message == null) return;
        throw new IllegalArgumentException(message);
    }
}
