package bookmarks.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bookmarks.interfaces.ICurrentUserService;
import bookmarks.security.CustomUserDetails;

@Service
public class CurrentUserService implements ICurrentUserService {
    public CustomUserDetails getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal;
    }

    public Long getUserId() {
        CustomUserDetails principal = getPrincipal();
        return principal.getId();
    }
}
