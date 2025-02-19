package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.ClassStudents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassStudentsRepository extends JpaRepository<ClassStudents, Long> {
}
