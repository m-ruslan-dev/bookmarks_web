package bookmarks.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bookmarks.exceptions.UnexpectedPrincipalTypeException;
import bookmarks.interfaces.ICurrentUserService;
import bookmarks.models.User;
import bookmarks.security.CustomUserDetails;

@Service
public class CurrentUserService implements ICurrentUserService {
    public <T> T getPrincipal(Class<T> expectedType) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        // Check if the "principal" is an instance of "expectedType"
        if (expectedType.isInstance(principal)) {
            return expectedType.cast(principal);
        } else {
            throw new UnexpectedPrincipalTypeException(
                    "Unexpected principal type. Please check the argument passed to the method.");
        }
    }

    public Long getUserId() {
        CustomUserDetails principal = getPrincipal(CustomUserDetails.class);
        return principal.getId();
    }

    public User getUser() {
        User user = getPrincipal(User.class);
        return user;
    }
}
