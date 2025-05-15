package org.example.candidate_application.service;
import org.example.candidate_application.dto.CandidateBankInfoDTO;
import org.example.candidate_application.entity.CandidateBankInfo;
import org.example.candidate_application.repository.CandidateBankInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateBankInfoService {
    @Autowired
    private CandidateBankInfoRepository repository;

    public CandidateBankInfo save(Long candidateId, CandidateBankInfoDTO dto) {
        CandidateBankInfo info = new CandidateBankInfo();
        info.setBankName(dto.getBankName());
        info.setAccountNumber(dto.getAccountNumber());
        info.setIfscCode(dto.getIfscCode());

        return repository.save(info);
    }
}
