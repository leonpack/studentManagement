package dev.nathan.studentmanagementv1.controller;

import dev.nathan.studentmanagementv1.dto.requestDTO.SubjectRequestDTO;
import dev.nathan.studentmanagementv1.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<?> getAllSubjects() {
        return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<?> getSubjectById(@PathVariable UUID subjectId) {
        return new ResponseEntity<>(subjectService.getSubjectById(subjectId), HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<?> addNewSubject(@RequestBody SubjectRequestDTO dto) {
        return new ResponseEntity<>(subjectService.saveSubject(dto), HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<?> removeSubject(@PathVariable UUID subjectId) {
        if(subjectService.deleteSubject(subjectId)) {
            return new ResponseEntity<>("Remove subject done!", HttpStatusCode.valueOf(200));
        } else
            return new ResponseEntity<>("Something's wrong, please try again", HttpStatusCode.valueOf(400));
    }

}
