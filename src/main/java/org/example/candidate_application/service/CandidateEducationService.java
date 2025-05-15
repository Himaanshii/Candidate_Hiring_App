package org.example.candidate_application.service;


import org.example.candidate_application.dto.CandidateEducationDTO;
import org.example.candidate_application.entity.CandidateEducation;
import org.example.candidate_application.repository.CandidateEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateEducationService {
    @Autowired
    private CandidateEducationRepository repository;

    public CandidateEducation save(Long candidateId, CandidateEducationDTO dto) {
        CandidateEducation edu = new CandidateEducation();
        edu.setDegree(dto.getDegree());
        edu.setInstitution(dto.getInstitution());
        edu.setYearOfPassing(dto.getYearOfPassing());

        return repository.save(edu);
    }
}
