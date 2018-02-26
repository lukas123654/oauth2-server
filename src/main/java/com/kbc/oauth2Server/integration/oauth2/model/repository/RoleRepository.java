package com.kbc.oauth2Server.integration.oauth2.model.repository;

import com.kbc.oauth2Server.integration.oauth2.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
