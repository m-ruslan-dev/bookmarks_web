package bookmarks.interfaces;

import bookmarks.models.User;

public interface ICurrentUserService {
    public Long getUserId();

    public User getUser();
}
