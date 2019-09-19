package DAO;

import Entities.GroupOfStudents;
import Entities.Seance;
import Entities.SessionOfGroup;
import Entities.Student;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;

public interface GroupOfStudentsDAO {

    public void addGroup(GroupOfStudents groupOfStudents);
    public List<GroupOfStudents> getAllGroups();
    public void deleteGroup(int id);
    public void updateGroup(int id, GroupOfStudents groupOfStudents );
    public void updateGroupTeacher(int id, GroupOfStudents groupOfStudentsNew);
   // public void updateGroupStudentsList (int id, Set<Student> studentList);
    public  void updateGroupSessionsList (int id, Set<SessionOfGroup> sessionsList);
    public GroupOfStudents getGroupById(int id);
    public SortedMap<String, List<GroupOfStudents>> getAllGroupsByModules();

}
