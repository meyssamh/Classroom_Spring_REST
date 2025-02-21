package app.vercel.meyssam.classroom.service;

import org.springframework.stereotype.Service;

@Service
public interface HistoryLogService {
    void saveUserRegistrationInHistoryLog(long userId);

    void saveClassCreationInHistoryLog(long userId, long classId);
    void saveClassUpdateInHistoryLog(long userId, long classId);
    void saveClassDeletionInHistoryLog(long userId, long classId);

    void saveStudentCreationInHistoryLog(long userId, long classId, long studentId);
    void saveStudentUpdateInHistoryLog(long userId, long classId, long studentId);
    void saveStudentDeletionInHistoryLog(long userId, long classId, long studentId);
}
