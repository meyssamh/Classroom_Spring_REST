package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
