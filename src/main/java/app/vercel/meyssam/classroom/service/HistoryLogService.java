package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.entity.HistoryLog;
import app.vercel.meyssam.classroom.repository.HistoryLogRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryLogService {

    private final HistoryLogRepository historyLogRepository;

    public HistoryLogService(HistoryLogRepository historyLogRepository) {
        this.historyLogRepository = historyLogRepository;
    }

    public HistoryLog saveHistoryLog(HistoryLog historyLog) {
        return historyLogRepository.save(historyLog);
    }
}