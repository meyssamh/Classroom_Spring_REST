package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.entity.HistoryLog;
import app.vercel.meyssam.classroom.entity.User;
import app.vercel.meyssam.classroom.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HistoryLogService historyLogService;

    public UserService(UserRepository userRepository, HistoryLogService historyLogService) {
        this.userRepository = userRepository;
        this.historyLogService = historyLogService;
    }

    public User saveUser(User user) {
        // have to move to UserController
        HistoryLog historyLog = new HistoryLog();
        historyLog.setuserId(user.getId());
        historyLog.setCreatedAt(Timestamp.from(Instant.now()));
        historyLog.setActivity("User registered.");

        historyLogService.saveHistoryLog(historyLog);

        user.setCreatedAt(Timestamp.from(Instant.now()));

        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}