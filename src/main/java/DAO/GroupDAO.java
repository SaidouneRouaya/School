package DAO;

import Entities.GroupOfStudents;

import java.util.List;

public interface GroupDAO {

    public void addGroup(GroupOfStudents groupOfStudents);
    public List<GroupOfStudents> getAllGroups();
    public void deleteGroup(int id);
    public void updateGroup(int id );
}
