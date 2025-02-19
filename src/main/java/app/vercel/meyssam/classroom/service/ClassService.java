package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.repository.ClassRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public Class saveClass(Class classToSave) {
        classToSave.setCreatedAt(Timestamp.from(Instant.now()));
        classToSave.setUpdatedAt(Timestamp.from(Instant.now()));

        return classRepository.save(classToSave);
    }

    public Class updateClass(Long classId, Class classToUpdate) {
        Class existingClass = classRepository.findClassById(classId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Class with ID " + classId + " not found!"
                ));

        existingClass.setClassname(classToUpdate.getClassname());
        existingClass.setUpdatedAt(Timestamp.from(Instant.now()));

        return classRepository.save(existingClass);
    }

    public void deleteClass(long classId) {
        if (!classRepository.existsById(classId)) {
            throw new ResourceNotFoundException(
                    "Class with ID " + classId + " not found!"
            );
        }
        classRepository.deleteById(classId);
    }

    public Optional<Class> findById(long classId) {
        return classRepository.findClassById(classId);
    }
}