package Controllers;

import DAO.ModuleDAO;
import Entities.*;
import Entities.Module;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.*;

@org.springframework.stereotype.Controller

public class ModulesController {

    @Autowired
    private ModuleDAO moduleDAO;

    @RequestMapping("/Modules")
    public String modulesList(Model model) {

        String error = "";
        List<Module> modules =  moduleDAO.getAllModules();


        model.addAttribute("modulesList", modules );
        List<HashSet<Teacher>> teachersList= new ArrayList<>();

        for (Module module:modules){

            HashSet<Teacher> teacher=new HashSet<>(module.getModuleTeachersSet());
            teachersList.add(teacher);

        }

        model.addAttribute("teachersList",teachersList );

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Modules/ModulesDataTable";
    }

    @RequestMapping("/addModule")
    public String addModule(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Modules/AddModule";

    }

      @RequestMapping("/addNewModule")
    public String addNewModule(Model model , @RequestParam Map<String, String> param ) {

        String error = "";

          Set<Teacher> teachers= new HashSet<>();
          Set<Student> students= new HashSet<>();
          Set<Group> groups= new HashSet<>();
          Set<Session> sessions= new HashSet<>();

        Module module=new Module(param.get("name"),  Integer.parseInt(param.get("numberLessons")),
                Long.parseLong(param.get("fees")), groups, sessions, students, teachers);

        moduleDAO.addModule(module);

        model.addAttribute("error", error);
        return "redirect:Modules.j";
    }



}
