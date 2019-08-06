package Controllers;

import DAO.GroupOfStudentsDAO;
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
    GroupOfStudentsDAO groupOfStudentsDAO;

    @Autowired
    ModuleDAO moduleDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    TeacherDAO teacherDAO;

    @RequestMapping("/Groups")
    public String pageAccueil(Model model) {

        String error = "";

        model.addAttribute("groupsList", groupOfStudentsDAO.getAllGroups());

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsList";
    }

    @RequestMapping("/GroupsByModule")
    public String GroupsByModule(Model model) {

        String error = "";

        model.addAttribute("groupsListByModule", groupOfStudentsDAO.getAllGroupsByModules());

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsByModule";
    }


    @RequestMapping("/GroupDetails")
    public String groupDetails(Model model, @RequestParam String id_group) {

        String error = "";

        model.addAttribute("group", groupOfStudentsDAO.getGroupById(Integer.parseInt(id_group)));

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

        groupOfStudentsDAO.addGroup(groupOfStudents);

        model.addAttribute("error", error);
        return "redirect:Groups.j";
    }



}
