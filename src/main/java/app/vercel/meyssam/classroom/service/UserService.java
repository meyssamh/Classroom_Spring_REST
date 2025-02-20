package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.entity.User;

public interface UserService {
    User getUserById(long userId);
}
