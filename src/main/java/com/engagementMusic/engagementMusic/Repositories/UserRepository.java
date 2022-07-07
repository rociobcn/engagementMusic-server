package com.engagementMusic.engagementMusic.Repositories;

import com.engagementMusic.engagementMusic.Models.Member;
import com.engagementMusic.engagementMusic.Models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
