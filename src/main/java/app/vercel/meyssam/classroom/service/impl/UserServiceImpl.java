package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.entity.User;
import app.vercel.meyssam.classroom.repository.UserRepository;
import app.vercel.meyssam.classroom.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HistoryLogServiceImpl historyLogServiceImpl;

    public UserServiceImpl(UserRepository userRepository, HistoryLogServiceImpl historyLogServiceImpl) {
        this.userRepository = userRepository;
        this.historyLogServiceImpl = historyLogServiceImpl;
    }

    public User saveUser(User user) {
//        return type must be DTO
        historyLogServiceImpl.saveUserRegistrationInHistoryLog(user.getId());

        user.setCreatedAt(Timestamp.from(Instant.now()));

        return userRepository.save(user);
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}