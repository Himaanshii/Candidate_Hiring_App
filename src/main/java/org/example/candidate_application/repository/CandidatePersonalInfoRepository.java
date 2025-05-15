package org.example.candidate_application.repository;

import org.example.candidate_application.entity.CandidatePersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatePersonalInfoRepository extends JpaRepository<CandidatePersonalInfo, Long> {}
