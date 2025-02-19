package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.ClassSessions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassSessionsRepository extends JpaRepository<ClassSessions, Long> {
}
