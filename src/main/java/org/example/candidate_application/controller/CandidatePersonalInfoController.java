package org.example.candidate_application.controller;

import jakarta.validation.Valid;
import org.example.candidate_application.dto.CandidatePersonalInfoDTO;
import org.example.candidate_application.service.CandidatePersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates/{id}/personal-info")
public class CandidatePersonalInfoController {
    @Autowired
    private CandidatePersonalInfoService service;

    @PutMapping
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CandidatePersonalInfoDTO dto) {
        return ResponseEntity.ok(service.save(id, dto));
    }
}
