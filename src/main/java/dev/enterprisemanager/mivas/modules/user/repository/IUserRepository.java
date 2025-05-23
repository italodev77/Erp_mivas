package dev.enterprisemanager.mivas.modules.user.repository;


import dev.enterprisemanager.mivas.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByEmail(String email);

    boolean existsByEmail(String email);
}
