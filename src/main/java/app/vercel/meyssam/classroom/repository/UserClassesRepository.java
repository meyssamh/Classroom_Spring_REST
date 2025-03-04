package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.Class;
import app.vercel.meyssam.classroom.entity.UserClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserClassesRepository extends JpaRepository<UserClasses, Long> {
    @Query("SELECT c FROM Class c JOIN UserClasses u WHERE u.id = :userId")
    List<Class> findByUserId(Long id);

    @Query("SELECT r FROM UserClasses r WHERE r.classEntity.id = :classId")
    UserClasses findByClassId(Long classId);
}