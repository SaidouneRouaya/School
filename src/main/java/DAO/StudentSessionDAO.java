package DAO;

import Entities.SessionOfGroup;
import Entities.StudentSession;
import Entities.StudentSessionID;

import java.util.List;

public interface StudentSessionDAO {


    public void addStudentSession(StudentSession studentSession);
    public List<StudentSession> getAllStudentSessions();
   // StudentSession getStudentSessionByID(StudentSessionID id);
    public void deleteStudentSession(StudentSession studentSession);
   // public void updateSession(int id, StudentSession studentSession);


}
