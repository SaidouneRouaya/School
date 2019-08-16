package DAO;


import Entities.Seance;
import Entities.Student;

import java.util.List;
import java.util.Set;

public interface SeanceDAO {
    public void addSeance(Seance seance);
    public List<Seance> getAllSeances();
    public Seance getSeanceByID(int id);

    public void deleteSeance(int id);
    public void updateSeance(int id, Seance seance);

    public void updateSessionStudents(int id, Set<Student> studentList);
}

