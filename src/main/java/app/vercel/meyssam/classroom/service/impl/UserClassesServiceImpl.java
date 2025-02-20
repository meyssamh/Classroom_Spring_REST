package app.vercel.meyssam.classroom.service.impl;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.User;
import app.vercel.meyssam.classroom.entity.UserClasses;
import app.vercel.meyssam.classroom.repository.UserClassesRepository;
import app.vercel.meyssam.classroom.service.UserClassesService;
import org.springframework.stereotype.Service;

@Service
public class UserClassesServiceImpl implements UserClassesService {
    private final UserClassesRepository userClassesRepository;

    public UserClassesServiceImpl(UserClassesRepository userClassesRepository) {
        this.userClassesRepository = userClassesRepository;
    }

    @Override
    public void createUserClasses(User user, Class savedClass) {
        UserClasses userClasses = new UserClasses();
        userClasses.setUser(user);
        userClasses.setClassEntity(savedClass);

        userClassesRepository.save(userClasses);
    }

    @Override
    public void deleteUserClasses(Class classToDelete) {
        UserClasses userClass = userClassesRepository.findByClassId(classToDelete.getId());

        if (userClass != null) {
            userClassesRepository.deleteById(userClass.getId());
        } else {
            throw new IllegalArgumentException("Record not found");
        }
    }
}