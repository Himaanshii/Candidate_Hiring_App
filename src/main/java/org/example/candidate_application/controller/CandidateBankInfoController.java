package org.example.candidate_application.controller;


import jakarta.validation.Valid;
import org.example.candidate_application.dto.CandidateBankInfoDTO;
import org.example.candidate_application.service.CandidateBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates/{id}/bank-info")
public class CandidateBankInfoController {
    @Autowired
    private CandidateBankInfoService service;

    @PutMapping
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CandidateBankInfoDTO dto) {
        return ResponseEntity.ok(service.save(id, dto));
    }
}
