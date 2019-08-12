package Controllers;

import DAO.ModuleDAO;
import Entities.*;
import Entities.Module;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.*;

@org.springframework.stereotype.Controller

public class ModulesController {

    @Autowired
    private ModuleDAO moduleDAO;

    @RequestMapping("/Modules")
    public String modulesList(Model model, @ModelAttribute("utilisateur") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                List<Module> modules = moduleDAO.getAllModules();


                model.addAttribute("modulesList", modules);
                List<HashSet<Teacher>> teachersList = new ArrayList<>();

                for (Module module : modules) {

                    HashSet<Teacher> teacher = new HashSet<>(module.getModuleTeachersSet());
                    teachersList.add(teacher);

                }

                model.addAttribute("teachersList", teachersList);

                model.addAttribute("error", error);
                return "LanguagesSchoolPages/Modules/ModulesDataTable";
            } else {

                return "redirect:/error.j";
            }

        } else {
            //todo no one is connected
            return "redirect:/error.j";
        }
    }

    @RequestMapping("/addModule")
    public String addModule(Model model, @ModelAttribute("utilisateur") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                model.addAttribute("error", error);
                return "LanguagesSchoolPages/Modules/AddModule";
            } else {

                return "redirect:/error.j";
            }

        } else {
            //todo no one is connected
            return "redirect:/error.j";
        }

    }

    @RequestMapping("/updateModule")
    public String updateModule(Model model, @RequestParam String query, @ModelAttribute("utilisateur") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                model.addAttribute("module", moduleDAO.getModuleByID(Integer.parseInt(query)));

                model.addAttribute("error", error);

                return "LanguagesSchoolPages/Modules/UpdateModule";
            } else {

                return "redirect:/error.j";
            }

        } else {
            //todo no one is connected
            return "redirect:/error.j";
        }
    }

    @RequestMapping("/PersistUpdateModule")
    public String persistUpdateModule(Model model, @RequestParam String query, @RequestParam Map<String, String> param, @ModelAttribute("utilisateur") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                Module module = new Module();

                try {

                    module = new Module(param.get("name"), Long.parseLong(param.get("fees")));

                    moduleDAO.updateModule(Integer.parseInt(query), module);

                } catch (Exception ex) {
                    error = ex.toString();
                    ex.printStackTrace();
                }
                model.addAttribute("module", module);

                model.addAttribute("error", error);
                return "redirect:Modules.j";
            } else {

                return "redirect:/error.j";
            }

        } else {
            //todo no one is connected
            return "redirect:/error.j";
        }
    }

    @RequestMapping("/addNewModule")
    public String addNewModule(Model model, @RequestParam Map<String, String> param, @ModelAttribute("utilisateur") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {


                Set<Teacher> teachers = new HashSet<>();
                Set<Student> students = new HashSet<>();
                Set<GroupOfStudents> groups = new HashSet<>();


                Module module = new Module(param.get("name"), Long.parseLong(param.get("fees")), groups, students, teachers);

                moduleDAO.addModule(module);

                model.addAttribute("error", error);
                return "redirect:Modules.j";
            } else {

                return "redirect:/error.j";
            }

        } else {
            //todo no one is connected
            return "redirect:/error.j";
        }
    }


}
