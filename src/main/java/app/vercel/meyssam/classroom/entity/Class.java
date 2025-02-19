package app.vercel.meyssam.classroom.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(nullable = false, length = 100, name = "class_name")
    private String classname;

    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "classEntity")
    Set<UserClasses> course;

    @OneToMany(mappedBy = "classEntity")
    Set<ClassSessions> courseSessions;

    @OneToMany(mappedBy = "classEntity")
    Set<ClassStudents> courseStudents;

    public Class() {
    }

    public Class(
            Set<ClassStudents> courseStudents,
            Set<ClassSessions> courseSessions,
            Set<UserClasses> course,
            Timestamp updatedAt,
            Timestamp createdAt,
            String classname,
            long id
    ) {
        this.courseStudents = courseStudents;
        this.courseSessions = courseSessions;
        this.course = course;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.classname = classname;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String className) {
        this.classname = className;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<UserClasses> getCourse() {
        return course;
    }

    public void setCourse(Set<UserClasses> course) {
        this.course = course;
    }

    public Set<ClassSessions> getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(Set<ClassSessions> courseSessions) {
        this.courseSessions = courseSessions;
    }

    public Set<ClassStudents> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(Set<ClassStudents> courseStudents) {
        this.courseStudents = courseStudents;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", classname='" + classname + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", course=" + course +
                ", courseSessions=" + courseSessions +
                ", courseStudents=" + courseStudents +
                '}';
    }
}