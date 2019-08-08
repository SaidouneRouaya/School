package DAO;


import Entities.SessionOfGroup;
import Entities.Student;

import java.util.List;
import java.util.Set;

public interface SessionDAO {
    public void addSession(SessionOfGroup session);
    public List<SessionOfGroup> getAllSessions();
    public SessionOfGroup getSessionByID(int id);

    public void deleteSession(int id);
    public void updateSession(int id, String type);

    public void updateSessionStudents(int id, Set<Student> studentList);
}
