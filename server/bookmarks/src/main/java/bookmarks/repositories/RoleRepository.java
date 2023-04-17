package bookmarks.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bookmarks.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("""
            SELECT r FROM Role r WHERE r.role = :role
            """)
    Optional<Role> findRoleByName(String role);
}
