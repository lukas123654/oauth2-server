package com.kbc.oauth2Server.model.repository;

import com.kbc.oauth2Server.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);

    List<User> findByUsernameContainingIgnoreCase(String username);

    @Transactional
    Long deleteByUsername(String username);

}
