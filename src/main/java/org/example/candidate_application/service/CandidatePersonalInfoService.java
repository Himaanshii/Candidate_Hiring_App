package org.example.candidate_application.service;


import org.example.candidate_application.dto.CandidatePersonalInfoDTO;
import org.example.candidate_application.entity.CandidatePersonalInfo;
import org.example.candidate_application.repository.CandidatePersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatePersonalInfoService {
    @Autowired
    private CandidatePersonalInfoRepository repository;

    public CandidatePersonalInfo save(Long candidateId, CandidatePersonalInfoDTO dto) {
        CandidatePersonalInfo info = new CandidatePersonalInfo();
        info.setDob(dto.getDob());
        info.setGender(dto.getGender());
        info.setAddress(dto.getAddress());
        info.setNationality(dto.getNationality());

        return repository.save(info);
    }
}
