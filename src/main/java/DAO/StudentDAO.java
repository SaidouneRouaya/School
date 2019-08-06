package DAO;

import Entities.Module;
import Entities.Profile;
import Entities.Student;

import java.util.HashMap;
import java.util.List;

public interface StudentDAO {


    public void addStudent(Student student);
    public Student getStudentByID (int id);
    public List<Student> getAllStudents();
    public void deleteStudent(int id);
    public void updateStudent(int id, Student st);

    HashMap<Integer, List<Student>> getStudentsByModules(List<Module> modules);
}
