package DAO;

import Entities.GroupOfStudents;

import java.util.List;
import java.util.SortedMap;

public interface GroupOfStudentsDAO {

    public void addGroup(GroupOfStudents groupOfStudents);
    public List<GroupOfStudents> getAllGroups();
    public void deleteGroup(int id);
    public void updateGroup(int id );
    public GroupOfStudents getGroupById(int id);
    public SortedMap<String, List<GroupOfStudents>> getAllGroupsByModules();

}
