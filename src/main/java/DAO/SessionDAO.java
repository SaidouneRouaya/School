package DAO;

import Entities.Session;

import java.util.List;

public interface SessionDAO {
    public void addSession(Session session);
    public List<Session> getAllSessions();
    public void deleteSession(int id);
    public void updateSession(int id, String type);
}
