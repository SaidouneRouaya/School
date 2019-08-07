package DAO;

import Entities.GroupOfStudents;
import Entities.Module;
import Entities.Profile;
import Entities.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface StudentDAO {


    public void addStudent(Student student);
    public Student getStudentByID (int id);
    public List<Student> getAllStudents();
    public void deleteStudent(int id);
    public void updateStudent(int id, Student st);

    public void updateStudentGroups(int id, Set<GroupOfStudents> groups);
    public void updateStudentModules(int id, Set<Module> modules);
    public List<Student> getStudentsByGroup(int id_group);
    HashMap<Integer, List<Student>> getStudentsByModules(List<Module> modules);
}
