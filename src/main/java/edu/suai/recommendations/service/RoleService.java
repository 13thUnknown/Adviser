package edu.suai.recommendations.service;

import edu.suai.recommendations.exception.NotFoundException;
import edu.suai.recommendations.model.Role;
import edu.suai.recommendations.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role getRoleByTitle(String title) {
        return roleRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Role.class));
    }
}
