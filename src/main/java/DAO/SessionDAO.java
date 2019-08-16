package DAO;


import Entities.SessionOfGroup;
import java.util.List;


public interface SessionDAO {


    public void addSession(SessionOfGroup session);
    public List<SessionOfGroup> getAllSessions();
    public SessionOfGroup getSessionByID(int id);
    List<SessionOfGroup> getSessionByGroup (int id_group);
    public void deleteSession(int id);
    public void updateSession(int id, SessionOfGroup session);


}
