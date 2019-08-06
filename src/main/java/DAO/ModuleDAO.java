package DAO;

import Entities.Module;

import java.util.List;

public interface ModuleDAO {
    public void addModule(Module module);
    public List<Module> getAllModules();
    public void deleteModule(int id);
    public void updateModule(int id, Module module);
    public Module getModuleByID(int id);
}
