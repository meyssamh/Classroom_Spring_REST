package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT s FROM ClassSessions s WHERE s.classEntity.id = :classId")
    List<Session> findByClassId(long classId);
}