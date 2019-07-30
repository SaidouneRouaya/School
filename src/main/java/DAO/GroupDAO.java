package DAO;

import Entities.Group;
import Entities.Student;

import java.util.List;

public interface GroupDAO {
    public void addGroup(Group group);
    public List<Group> getAllGroups();
    public void deleteGroup(int id);
    public void updateGroup(int id, String type);
}
