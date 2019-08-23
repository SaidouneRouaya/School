package Controllers;

import DAO.*;
import Entities.*;
import Entities.Module;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.*;

@org.springframework.stereotype.Controller

public class ModulesController {

    @Autowired
    private ModuleDAO moduleDAO;

    @Autowired
    TeacherDAO teacherDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    SessionDAO sessionDAO;

    @Autowired
    SeanceDAO seanceDAO;

    @Autowired
    StudentSessionDAO studentSessionDAO;

    @Autowired
    GroupOfStudentsDAO groupOfStudentsDAO;

    @RequestMapping("/Modules")
    public String modulesList(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")
                    || profile.getType().equalsIgnoreCase("Receptionist")) {


                List<Module> modules = moduleDAO.getAllModules();

                if (modules==null ||modules.isEmpty()) return ("redirect:/empty.j");

                 model.addAttribute("modulesList", modules);

                 Map<Integer, List<Teacher>> moduleTeacherMap= new HashMap<>();


                for (Module module : modules) {

                    moduleTeacherMap.put(module.getId(), new ArrayList<>(module.getModuleTeachersSet()));

                }


                model.addAttribute("modulesTeachersMap", moduleTeacherMap);
                model.addAttribute("profile", profile);

                model.addAttribute("error", error);

                return "LanguagesSchoolPages/Modules/ModulesDataTable";
            } else {
                System.out.println("1");
                return "redirect:/error.j";

            }

        } else {
            //todo no one is connected
            System.out.println("2");
            return "redirect:/error.j";
        }
    }

    @RequestMapping("/addModule")
    public String addModule(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {
                model.addAttribute("profile", profile);
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
    public String updateModule(Model model, @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                model.addAttribute("teachers" , teacherDAO.getAllTeachers());
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
    public String persistUpdateModule(Model model, @RequestParam String query, @RequestParam Map<String, String> param, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                Module module = new Module();
                Teacher teacher;
                int id_teacher = Integer.parseInt(param.get("teachers"));
                int id_module = Integer.parseInt(query);

                try {

                    if (id_teacher!=0){
                        teacher = teacherDAO.getTeacherByID(id_teacher);
                        module=new Module (param.get("name"), Long.parseLong(param.get("fees")), teacher);

                        moduleDAO.updateModule(id_module, module);

                        teacher.getTeacherModulesSet().add(moduleDAO.getModuleByID(id_module));
                        teacherDAO.updateTeacher(teacher.getId(), teacher);


                    }else
                    {
                        module = new Module(param.get("name"), Long.parseLong(param.get("fees")));
                        moduleDAO.updateModule(Integer.parseInt(query), module);
                    }



                } catch (Exception ex) {
                    error = ex.toString();
                    ex.printStackTrace();
                }
                model.addAttribute("module", module);
                model.addAttribute("profile", profile);
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
    public String addNewModule(Model model, @RequestParam Map<String, String> param, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {


                Set<Teacher> teachers = new HashSet<>();
                Set<Student> students = new HashSet<>();
                Set<GroupOfStudents> groups = new HashSet<>();


                Module module = new Module(param.get("name"), Long.parseLong(param.get("fees")), groups, students, teachers);

                moduleDAO.addModule(module);
                model.addAttribute("profile", profile);
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


    @RequestMapping("/deleteModule")
    public String deleteModule(Model model,  @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        int id_module = Integer.parseInt(query);

        Module module= moduleDAO.getModuleByID(id_module);

        Set<Teacher> teachers= module.getModuleTeachersSet();
        Set<Student> students= module.getStudentsSet();
        Set<GroupOfStudents> groups=module.getGroupsSet();

        for (Teacher teacher1: teachers){
            Teacher teacher= teacherDAO.getTeacherByID(teacher1.getId());
            teacher.removeModule(module);
            teacherDAO.updateTeacher(teacher.getId(), teacher);
            module.removeTeacher(teacher);

        }

        for (Student student1: students){
            Student student = studentDAO.getStudentByID(student1.getId());
            student.removeModule(module.getId());
            studentDAO.updateStudent(student.getId(), student);
            module.removeStudent(student);


        }

        for (GroupOfStudents group: groups){

            deleteGroup(group);

        }

        moduleDAO.updateModule(module.getId(), module);
        moduleDAO.deleteModule(id_module);

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "redirect:Modules.j";
    }



    public void deleteGroup (GroupOfStudents group ){



            GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(group.getId());

            List<SessionOfGroup> sessionOfGroups= sessionDAO.getSessionByGroup(groupOfStudents.getId());

            for (SessionOfGroup sessionOfGroup: sessionOfGroups){

                for (Seance seance1: sessionOfGroup.getSeancesSet()){

                    Seance seance=  seanceDAO.getSeanceByID(seance1.getId());

                    for (Student student1: seance.getStudentsSet()){
                        Student student= studentDAO.getStudentByID(student1.getId());
                        student.removeSeance(seance.getId());
                        studentDAO.updateStudent(student.getId(), student);
                        seance.removeStudent(student);
                    }

                    seanceDAO.deleteSeance(seance.getId());
                }
                for (StudentSession studentSession: sessionOfGroup.getStudentSessionsSet()){
                    studentSessionDAO.deleteStudentSession(studentSession);
                }
                sessionDAO.deleteSession(sessionOfGroup.getId());
            }

            groupOfStudentsDAO.deleteGroup(group.getId());



    }


}
