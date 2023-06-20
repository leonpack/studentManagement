package dev.nathan.studentmanagementv1.services;

import dev.nathan.studentmanagementv1.entity.Subject;

import java.util.List;
import java.util.UUID;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubjectById(UUID id);

    Subject saveSubject(Subject subject);

    void deleteSubject(UUID id);

}
