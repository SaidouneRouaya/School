package DAO;

import Entities.Module;
import Entities.Student;

import java.util.List;
import java.util.Set;

public interface ModuleDAO {
    public void addModule(Module module);
    public List<Module> getAllModules();
    public void deleteModule(int id);
    public void updateModule(int id, Module module);
    public Module getModuleByID(int id);
    public void updateGroupStudentsList (int id, Set<Student> studentList);
}
