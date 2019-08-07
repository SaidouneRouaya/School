package Controllers;

import DAO.*;
import Entities.GroupOfStudents;
import Entities.Module;
import Entities.SessionOfGroup;
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

    @Autowired
    SessionDAO sessionDAO;

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

        model.addAttribute("students", studentDAO.getAllStudents());

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

            System.out.println(id_student);
            studentSet.add(studentDAO.getStudentByID(Integer.parseInt(id_student)));
        }


        GroupOfStudents groupOfStudents = new GroupOfStudents(param.get("name"), param.get("startDate"),
        param.get("r3"), Integer.parseInt(param.get("sessionNumber")), param.get("startTime"), param.get("endTime"),
               moduleDAO.getModuleByID(Integer.parseInt(param.get("modules"))),
               teacherDAO.getTeacherByID(Integer.parseInt(param.get("teachers"))),
               studentSet);


        for (Student student: studentSet){

            student.getGroupsSet().add(groupOfStudents);
            studentDAO.updateStudentGroups(student.getId(), student.getGroupsSet());
        }



        model.addAttribute("error", error);
        return "redirect:Groups.j";
    }

    @RequestMapping("/updateGroup")
    public String updateGroupsOfStudent(Model model,  @RequestParam String query) {

        String error = "";
        model.addAttribute("group", groupOfStudentsDAO.getGroupById(Integer.parseInt(query)));

        model.addAttribute("error", error);

        return "LanguagesSchoolPages/Groups/UpdateGroup";
    }


    @RequestMapping("/PersistUpdateGroup")
    public String persistUpdateGroupsOfStudent(Model model,  @RequestParam String query, @RequestParam Map<String,String> param) {

        String error = "";
        GroupOfStudents group= new GroupOfStudents();

        try{

            group=new GroupOfStudents(param.get("name"), param.get("r3"), Integer.parseInt(param.get("sessionNumber")));

            groupOfStudentsDAO.updateGroup(Integer.parseInt(query), group);

        }catch(Exception ex){
            error=ex.toString();
            ex.printStackTrace();
        }
        model.addAttribute("group", group);

        model.addAttribute("error", error);
        return "redirect:GroupDetails.j?id_group="+query;
    }

    @RequestMapping("/addStudentToGroup")
    public String addStudentToGroup(Model model, @RequestParam String query, @RequestParam String students){

        String error = "";

         int id_group=Integer.parseInt(query);
         int id_student=Integer.parseInt(students);

        Student student= studentDAO.getStudentByID(Integer.parseInt(students));
        GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(id_group);

        student.getGroupsSet().add(groupOfStudents);

        studentDAO.updateStudentGroups(id_student, student.getGroupsSet());

        groupOfStudents.getStudentsSet().add(student);

        groupOfStudentsDAO.updateGroupStudentsList(id_group, groupOfStudents.getStudentsSet());

        model.addAttribute("error", error);

        return "redirect:GroupDetails.j?id_group="+query;
    }
 @RequestMapping("/addSessionToGroup")
    public String addSessionToGroup(Model model, @RequestParam String query, @RequestParam String date){

        String error = "";

        int id_group=Integer.parseInt(query);


        GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(id_group);

        SessionOfGroup session= new SessionOfGroup(date, groupOfStudents);

        sessionDAO.addSession(session);

        groupOfStudents.getSessionSet().add(session);

        groupOfStudentsDAO.updateGroupSessionsList(id_group, groupOfStudents.getSessionSet());

        model.addAttribute("error", error);

        return "redirect:GroupDetails.j?id_group="+query;
    }


    @RequestMapping("/deleteStudentFromGroup")
    public String deleteStudentFromGroup(Model model, @RequestParam String query, @RequestParam String id_group){

        String error = "";

        Student student=studentDAO.getStudentByID(Integer.parseInt(query));

        GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(Integer.parseInt(id_group));

        student.removeGroup(groupOfStudents.getId());
        groupOfStudents.removeStudent(student.getId());

        studentDAO.updateStudentGroups(Integer.parseInt(query), student.getGroupsSet());
        groupOfStudentsDAO.updateGroupStudentsList(Integer.parseInt(id_group), groupOfStudents.getStudentsSet());

        model.addAttribute("error", error);

        return "redirect:GroupDetails.j?id_group="+id_group;

    }



    @RequestMapping("/deleteGroup")
    public String deleteGroup(Model model, @RequestParam String query){

        String error = "";


        GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(Integer.parseInt(query));
        List<Student> students=studentDAO.getStudentsByGroup(groupOfStudents.getId());

        for (Student student:students){
            student.removeGroup(groupOfStudents.getId());
            studentDAO.updateStudentGroups(student.getId(), student.getGroupsSet());

        }

       groupOfStudentsDAO.deleteGroup(Integer.parseInt(query));

        model.addAttribute("error", error);

        return "redirect:Groups.j";

    }






}
