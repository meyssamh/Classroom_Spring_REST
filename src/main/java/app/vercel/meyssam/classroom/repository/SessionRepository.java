package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}