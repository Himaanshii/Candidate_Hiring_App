package org.example.candidate_application.controller;


import jakarta.validation.Valid;
import org.example.candidate_application.dto.CandidateDocumentDTO;
import org.example.candidate_application.service.CandidateDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates/{id}/documents")
public class CandidateDocumentController {
    @Autowired
    private CandidateDocumentService service;

    @PostMapping
    public ResponseEntity<?> upload(@PathVariable Long id, @RequestBody @Valid CandidateDocumentDTO dto) {
        return new ResponseEntity<>(service.save(id, dto), HttpStatus.CREATED);
    }
}
