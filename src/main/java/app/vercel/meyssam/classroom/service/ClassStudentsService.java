package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.Student;

public interface ClassStudentsService {
    void createClassStudent(Class chosenClass, Student savedStudent);
    long deleteClassStudent(Student studentToDelete);
}
