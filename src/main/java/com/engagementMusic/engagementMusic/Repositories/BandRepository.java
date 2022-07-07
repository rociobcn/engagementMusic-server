package com.engagementMusic.engagementMusic.Repositories;

import com.engagementMusic.engagementMusic.Models.Agent;
import com.engagementMusic.engagementMusic.Models.Band;
import com.engagementMusic.engagementMusic.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BandRepository extends JpaRepository<Band, String> {

    Optional<Band> findByUsername(String username);
    Optional<Band> findByNameOfGroup(String name);

}
