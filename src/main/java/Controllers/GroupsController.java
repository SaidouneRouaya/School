package Controllers;

import DAO.GroupDAO;
import DAO.ModuleDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import Entities.GroupOfStudents;
import Entities.Module;
import Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@org.springframework.stereotype.Controller

public class GroupsController {


    @Autowired
    GroupDAO groupDAO;

    @Autowired
    ModuleDAO moduleDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    TeacherDAO teacherDAO;

    @RequestMapping("/Groups")
    public String pageAccueil(Model model) {

        String error = "";

        model.addAttribute("groupsList", groupDAO.getAllGroups());

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsList";
    }

    @RequestMapping("/englishGroup")
    public String englishGroup(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsListEnglish";
    }

    @RequestMapping("/frenchGroup")
    public String frenchGroup(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsListFrench";
    }

    @RequestMapping("/GroupDetails")
    public String groupDetails(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupDetails";
    }

    @RequestMapping("/addGroup")
    public String addGroup(Model model) {

        String error = "";

        List<Module> modules= moduleDAO.getAllModules();
        HashMap<Integer, List<Student>> students= studentDAO.getStudentsByModules(modules);

        model.addAttribute("modulesList", modules);
        model.addAttribute("studentList",students);
        model.addAttribute("teacherList", teacherDAO.getAllTeachers());


        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/AddGroup";
    }

    @RequestMapping("/addNewGroup")
    public String addNewGroup(Model model , @RequestParam Map<String, String> param, @RequestParam List<String> studentsList) {

        String error = "";

        Set<Student> studentSet=new HashSet<>();
        for (String id_student: studentsList){

            studentSet.add(studentDAO.getStudentByID(Integer.parseInt(id_student)));
        }



       GroupOfStudents groupOfStudents = new GroupOfStudents(param.get("name"), param.get("startDate"), param.get("r3"),
               moduleDAO.getModuleByID(Integer.parseInt(param.get("modules"))),
               teacherDAO.getTeacherByID(Integer.parseInt(param.get("teachers"))),
               studentSet);

        System.out.println(groupOfStudents);

        groupDAO.addGroup(groupOfStudents);

        model.addAttribute("error", error);
        return "redirect:Groups.j";
    }



}
