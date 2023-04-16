package bookmarks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import bookmarks.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
