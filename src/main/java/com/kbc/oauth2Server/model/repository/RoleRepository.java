package com.kbc.oauth2Server.model.repository;

import com.kbc.oauth2Server.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
