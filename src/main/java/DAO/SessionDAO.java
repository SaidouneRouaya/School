package DAO;


import Entities.SessionOfGroup;

import java.util.List;

public interface SessionDAO {
    public void addSession(SessionOfGroup session);
    public List<SessionOfGroup> getAllSessions();
    public void deleteSession(int id);
    public void updateSession(int id, String type);
}
