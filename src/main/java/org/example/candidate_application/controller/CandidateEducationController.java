package org.example.candidate_application.controller;


import jakarta.validation.Valid;
import org.example.candidate_application.dto.CandidateEducationDTO;
import org.example.candidate_application.service.CandidateEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates/{id}/educational-info")
public class CandidateEducationController {
    @Autowired
    private CandidateEducationService service;

    @PutMapping
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CandidateEducationDTO dto) {
        return ResponseEntity.ok(service.save(id, dto));
    }
}
