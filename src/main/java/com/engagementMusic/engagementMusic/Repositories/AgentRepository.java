package com.engagementMusic.engagementMusic.Repositories;

import com.engagementMusic.engagementMusic.Models.Agent;
import com.engagementMusic.engagementMusic.Models.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {
    Optional<Agent> findByUsername(String username);
}
