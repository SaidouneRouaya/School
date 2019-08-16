package DAO;

import Entities.*;
import Entities.Module;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface StudentDAO {


    void addStudent(Student student);

    Student getStudentByID(int id);

    List<Student> getAllStudents();

   // List<Student> getStudentsByGroup(int id_group);

    HashMap<Integer, List<Student>> getStudentsByModules(List<Module> modules);

    void updateStudent(int id, Student st);

  //  void updateStudentGroups(int id, Set<GroupOfStudents> groups);
  List<Student> getStudentsByModule(int id);

    void updateStudentSessions (int id, Set<Seance> sessionOfGroupsSet);

    void updateStudentModules(int id, Set<Module> modules);

    void deleteStudent(int id);


}
