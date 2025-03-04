package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    Class findClassById(Long classId);

    @Query("""
            SELECT c FROM Class c
            JOIN UserClasses uc
            ON c.id = uc.classEntity.id
            WHERE uc.user.id = :userId
            """)
    Optional<List<Class>> findAllClasses(Long userId);

    Class save(@NonNull Class classToSave);
}