package app.vercel.meyssam.classroom.service;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.UserClasses;
import app.vercel.meyssam.classroom.repository.UserClassesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserClassesService {
    private final UserClassesRepository userClassesRepository;

    public UserClassesService(UserClassesRepository userClassesRepository) {
        this.userClassesRepository = userClassesRepository;
    }

    public List<Class> findClassesByUserId(long userId) {
        return userClassesRepository.findByUser_Id(userId);
    }

    public UserClasses save(UserClasses userClassToSave) {
        return userClassesRepository.save(userClassToSave);
    }

    public void deleteRecordByClassId(long classId) {
        UserClasses userClass = userClassesRepository.findByClassId(classId);

        if (userClass != null) {
            userClassesRepository.deleteById(userClass.getId());
        } else {
            throw new IllegalArgumentException("Record not found");
        }
    }
}
