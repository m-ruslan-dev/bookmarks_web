package bookmarks.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bookmarks.interfaces.IAuthenticationStatusService;

@Service
public class AuthenticationStatusService implements IAuthenticationStatusService {
    public Boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Boolean status = authentication != null ? true : false;
        return status;
    }
}
