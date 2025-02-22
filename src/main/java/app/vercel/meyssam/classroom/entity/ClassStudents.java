package app.vercel.meyssam.classroom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "class_students")
public class ClassStudents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    Student student;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = true)
    Class classEntity;

    public ClassStudents() {
    }

    public ClassStudents(long id, Student student, Class classEntity) {
        this.id = id;
        this.student = student;
        this.classEntity = classEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Class getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(Class classEntity) {
        this.classEntity = classEntity;
    }

    @Override
    public String toString() {
        return "ClassStudents{" +
                "id=" + id +
                ", student=" + student +
                ", classEntity=" + classEntity +
                '}';
    }
}