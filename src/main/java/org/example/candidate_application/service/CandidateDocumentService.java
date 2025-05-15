package org.example.candidate_application.service;


import org.example.candidate_application.dto.CandidateDocumentDTO;
import org.example.candidate_application.entity.CandidateDocument;
import org.example.candidate_application.repository.CandidateDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateDocumentService {
    @Autowired
    private CandidateDocumentRepository repository;

    public CandidateDocument save(Long candidateId, CandidateDocumentDTO dto) {
        CandidateDocument doc = new CandidateDocument();
        doc.setDocumentType(dto.getDocumentType());
        doc.setFileUrl(dto.getFileUrl());
        doc.setVerified(dto.isVerified());

        return repository.save(doc);
    }
}
