package DAO;

import Entities.Teacher;

import java.util.List;

public interface TeacherDAO {
    public void addTeacher(Teacher teacher);
    public List<Teacher> getAllTeachers();
    public void deleteTeacher(int id);
    public void updateTeacher(int id, String type);
}
