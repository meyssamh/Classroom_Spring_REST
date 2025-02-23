package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.ClassStudents;
import app.vercel.meyssam.classroom.entity.Student;
import app.vercel.meyssam.classroom.repository.ClassStudentsRepository;
import app.vercel.meyssam.classroom.service.ClassStudentsService;
import org.springframework.stereotype.Service;

@Service
public class ClassStudentsServiceImpl implements ClassStudentsService {

    private final ClassStudentsRepository classStudentsRepository;

    public ClassStudentsServiceImpl(ClassStudentsRepository classStudentsRepository) {
        this.classStudentsRepository = classStudentsRepository;
    }

    @Override
    public void createClassStudent(Class chosenClass, Student savedStudent) {
        ClassStudents classStudents = new ClassStudents();
        classStudents.setStudent(savedStudent);
        classStudents.setClassEntity(chosenClass);

        classStudentsRepository.save(classStudents);
    }

    @Override
    public long deleteClassStudent(Student studentToDelete) {
        ClassStudents classStudent = classStudentsRepository.findByStudentId(studentToDelete.getId());

        if (classStudent != null) {
            classStudentsRepository.deleteById(classStudent.getId());
        } else {
            throw new IllegalArgumentException("Record not found");
        }

        return classStudent.getClassEntity().getId();
    }
}