package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM ClassStudents s WHERE s.classEntity.id = :classId")
    List<Student> findByClassId(long classId);
}