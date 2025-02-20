package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserClassesService {
    void createUserClasses(User user, Class savedClass);
    void deleteUserClasses(Class classToDelete);
}