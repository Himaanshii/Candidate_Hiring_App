package org.example.candidate_application.repository;

import org.example.candidate_application.entity.CandidateDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDocumentRepository extends JpaRepository<CandidateDocument, Long> {}
