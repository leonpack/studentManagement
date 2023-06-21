package dev.nathan.studentmanagementv1.services;

import dev.nathan.studentmanagementv1.dto.requestDTO.SubjectRequestDTO;
import dev.nathan.studentmanagementv1.dto.responseDTO.SubjectResponseDTO;
import dev.nathan.studentmanagementv1.entity.Subject;

import java.util.List;
import java.util.UUID;

public interface SubjectService {

    List<SubjectResponseDTO> getAllSubjects();

    Subject getSubjectById(UUID id);

    Subject saveSubject(SubjectRequestDTO subject);

    boolean deleteSubject(UUID id);

}
