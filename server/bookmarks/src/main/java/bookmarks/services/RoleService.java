package bookmarks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookmarks.exceptions.RoleNotFoundException;
import bookmarks.mysql.models.Role;
import bookmarks.mysql.repositories.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role getRoleByName(String name) throws RoleNotFoundException {
        final Optional<Role> role = roleRepository.findRoleByName(name);
        return role.orElseThrow(() -> new RoleNotFoundException("Could not find role: " + name));
    }
}
