package bookmarks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bookmarks.exceptions.UnexpectedPrincipalTypeException;
import bookmarks.exceptions.UserIsNotLoggedInException;
import bookmarks.interfaces.ILoggedUserService;
import bookmarks.security.CustomUserDetails;

@Service
public class LoggedUserService implements ILoggedUserService {
    @Autowired
    AuthenticationStatusService authenticationStatusService;

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

    public Long getUserId() throws UserIsNotLoggedInException {
        if (authenticationStatusService.isUserLoggedIn()) {
            CustomUserDetails principal = getPrincipal(CustomUserDetails.class);
            return principal.getId();
        } else {
            throw new UserIsNotLoggedInException("User is not logged in");
        }
    };
}
