package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.entity.HistoryLog;
import app.vercel.meyssam.classroom.repository.HistoryLogRepository;
import app.vercel.meyssam.classroom.service.HistoryLogService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class HistoryLogServiceImpl implements HistoryLogService {

    private final HistoryLogRepository historyLogRepository;

    public HistoryLogServiceImpl(HistoryLogRepository historyLogRepository) {
        this.historyLogRepository = historyLogRepository;
    }

    @Override
    public void saveUserRegistrationInHistoryLog(long userId) {
        HistoryLog historyLog = new HistoryLog();

        historyLog.setCreatedAt(Timestamp.from(Instant.now()));
        historyLog.setActivity("User registered with id " + userId + ".");
        historyLog.setuserId(userId);

        historyLogRepository.save(historyLog);
    }

    @Override
    public void saveClassCreationInHistoryLog(long userId, long classId) {
        HistoryLog historyLog = new HistoryLog();

        historyLog.setCreatedAt(Timestamp.from(Instant.now()));
        historyLog.setActivity("User with id " + userId + " saved class with id " + classId + ".");
        historyLog.setuserId(userId);

        historyLogRepository.save(historyLog);
    }

    @Override
    public void saveClassUpdateInHistoryLog(long userId, long classId) {
        HistoryLog historyLog = new HistoryLog();

        historyLog.setCreatedAt(Timestamp.from(Instant.now()));
        historyLog.setActivity("User with id " + userId + " updated class with id " + classId + ".");
        historyLog.setuserId(userId);

        historyLogRepository.save(historyLog);
    }

    @Override
    public void saveClassDeletionInHistoryLog(long userId, long classId) {
        HistoryLog historyLog = new HistoryLog();

        historyLog.setCreatedAt(Timestamp.from(Instant.now()));
        historyLog.setActivity("User with id " + userId + " deleted class with id " + classId + ".");
        historyLog.setuserId(userId);

        historyLogRepository.save(historyLog);
    }

    @Override
    public void saveStudentCreationInHistoryLog(long userId, long classId, long studentId) {
        HistoryLog historyLog = new HistoryLog();

        historyLog.setCreatedAt(Timestamp.from(Instant.now()));
        historyLog.setActivity("User with id " + userId +
                " created student with id " + studentId +
                " in class with id " + classId + ".");
        historyLog.setuserId(userId);

        historyLogRepository.save(historyLog);
    }

    @Override
    public void saveStudentUpdateInHistoryLog(long userId, long classId, long studentId) {
        HistoryLog historyLog = new HistoryLog();

        historyLog.setCreatedAt(Timestamp.from(Instant.now()));
        historyLog.setActivity("User with id " + userId +
                " updated student with id " + studentId +
                " in class with id " + classId + ".");
        historyLog.setuserId(userId);

        historyLogRepository.save(historyLog);
    }

    @Override
    public void saveStudentDeletionInHistoryLog(long userId, long classId, long studentId) {
        HistoryLog historyLog = new HistoryLog();

        historyLog.setCreatedAt(Timestamp.from(Instant.now()));
        historyLog.setActivity("User with id " + userId +
                " deleted studentwith id " + studentId +
                " in class with id " + classId + ".");
        historyLog.setuserId(userId);

        historyLogRepository.save(historyLog);
    }
}