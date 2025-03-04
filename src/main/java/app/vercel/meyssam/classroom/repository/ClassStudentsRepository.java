package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.ClassStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStudentsRepository extends JpaRepository<ClassStudents, Long> {
    @Query("SELECT s FROM ClassStudents s WHERE s.student.id = :studentId")
    ClassStudents findByStudentId(Long studentId);
}