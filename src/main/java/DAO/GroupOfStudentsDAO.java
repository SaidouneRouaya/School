package DAO;

import Entities.GroupOfStudents;
import Entities.Student;

import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

public interface GroupOfStudentsDAO {

    public void addGroup(GroupOfStudents groupOfStudents);
    public List<GroupOfStudents> getAllGroups();
    public void deleteGroup(int id);
    public void updateGroup(int id, GroupOfStudents groupOfStudents );
    public void updateGroupStudentsList (int id, Set<Student> studentList);
    public GroupOfStudents getGroupById(int id);
    public SortedMap<String, List<GroupOfStudents>> getAllGroupsByModules();

}
