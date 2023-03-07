package bookmarks.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface ICustomUserDetails extends UserDetails {
    public Long getId();
}
