package com.engagementMusic.engagementMusic.Repositories;

import com.engagementMusic.engagementMusic.Models.Agent;
import com.engagementMusic.engagementMusic.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
