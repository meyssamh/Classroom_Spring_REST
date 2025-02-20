package app.vercel.meyssam.classroom.repository;

import app.vercel.meyssam.classroom.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    Optional<Class> findClassById(Long classId);
    Optional<List<Class>> findAllClasses(Long userId);
    Class save(Class classToSave);
}