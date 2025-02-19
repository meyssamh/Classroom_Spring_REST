package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.HistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryLogRepository extends JpaRepository<HistoryLog, Long> {
}
