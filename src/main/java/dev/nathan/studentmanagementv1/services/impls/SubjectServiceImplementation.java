package dev.nathan.studentmanagementv1.services.impls;

import dev.nathan.studentmanagementv1.dto.requestDTO.SubjectRequestDTO;
import dev.nathan.studentmanagementv1.dto.responseDTO.SubjectResponseDTO;
import dev.nathan.studentmanagementv1.entity.Subject;
import dev.nathan.studentmanagementv1.repo.SubjectRepo;
import dev.nathan.studentmanagementv1.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImplementation implements SubjectService {

    private final SubjectRepo subjectRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<SubjectResponseDTO> getAllSubjects() {
        return subjectRepo.findAll().stream().map(subject -> modelMapper.map(subject, SubjectResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Subject getSubjectById(UUID id) {
        Optional<Subject> result = subjectRepo.findById(id);
        Subject subject = null;
        if(result.isPresent()) {
            subject = result.get();
        } else {
            throw new RuntimeException("Subject not found");
        }
        return subject;
    }

    @Override
    public Subject saveSubject(SubjectRequestDTO subject) {
        Subject newSubject = new Subject();
        subject.setSubjectName(subject.getSubjectName());
        return subjectRepo.save(newSubject);
    }

    @Override
    public boolean deleteSubject(UUID id) {
        Optional<Subject> result = subjectRepo.findById(id);
        Subject subject = null;
        if(result.isPresent()) {
            subject = result.get();
        } else {
            throw new RuntimeException("Subject not found!");
        }
        subjectRepo.delete(subject);
        return true;
    }

}
