package DAO;

import Entities.Module;
import Entities.Staff;
import Entities.Teacher;

import java.util.HashMap;
import java.util.List;

public interface TeacherDAO {
    public void addTeacher(Teacher teacher);
    public List<Teacher> getAllTeachers();
    public void deleteTeacher(int id);
    HashMap<Integer, List<Teacher>> getTeachersByModules(List<Module> modules);
    public void updateTeacher(int id, Teacher teacher);
    public Teacher getTeacherByID(int id);
}
