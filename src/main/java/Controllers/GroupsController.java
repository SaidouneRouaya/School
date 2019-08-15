package Controllers;

import DAO.*;
import Entities.*;
import Entities.Module;
import Util.utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public String groupDetails(Model model, @RequestParam String id_group, @SessionAttribute ("unpaidStudent") List<Student> unpaidStudent) {

        String error = "";

        Date now = new Date();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        GroupOfStudents group =groupOfStudentsDAO.getGroupById(Integer.parseInt(id_group));
        //List<Student> studentList= studentDAO.getAllStudents();
        List<Student> studentList= studentDAO.getStudentsByGroup(group.getId());


        HashMap<Integer, List<Presence>  > presenceList= new HashMap<>();

        for (Student stud: studentList){

            System.out.println("****************************  \n" +stud.getId());
            System.out.println("list of sessions ");
            for (SessionOfGroup se : stud.getSessionsSet()){
                System.out.println(se.getId());
            }
            System.out.println("end");

            List<Presence> presences= new ArrayList<>();

            for (SessionOfGroup session: group.getSessionSet()){

                System.out.println(session.getId()+" "+session.getDate());

                System.out.println("test : " + stud.present(session));
                Presence presence=new Presence(session.getId(), stud.present(session) );

                presences.add(presence);

                System.out.println(presence.present);
            }

            presenceList.put(stud.getId(), presences);
          //  System.out.println("id student: "+stud.getId()+" "+ presences);
        }


        model.addAttribute("group", group );
        model.addAttribute("students", studentList);
        model.addAttribute("now", dateFormat.format(now));
        model.addAttribute("presences", presenceList);
        model.addAttribute("unpaidStudent", unpaidStudent);

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


        int numberOfSession= Integer.parseInt(param.get("sessionNumber"));

        GroupOfStudents groupOfStudents = new GroupOfStudents(param.get("name"), param.get("startDate"),
        param.get("r3"), Integer.parseInt(param.get("seancesNumber")), param.get("startTime"), param.get("endTime"),
                Float.parseFloat(param.get("fees")), moduleDAO.getModuleByID(Integer.parseInt(param.get("modules"))),
               teacherDAO.getTeacherByID(Integer.parseInt(param.get("teachers"))),
               studentSet);

        groupOfStudentsDAO.addGroup(groupOfStudents);

        for (Student student: studentSet){

            student.getGroupsSet().add(groupOfStudents);
            studentDAO.updateStudentGroups(student.getId(), student.getGroupsSet());
        }

        for (int i=0; i<numberOfSession; i++){

            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (i==0){
                addSessionToGroup(model, Integer.toString(groupOfStudents.getId()), dateFormat.format(now));
            }else
            {
                addSessionToGroup(model, Integer.toString(groupOfStudents.getId()), null);
            }
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

    @RequestMapping("/deletePaymentStudent")
    public String deletePaymentStudent(Model model, @RequestParam String query, @RequestParam String id_group){

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

        for (SessionOfGroup session: groupOfStudents.getSessionSet()){
            for (Student student:students){
                if (student.present(session)) student.getSessionsSet().remove(session);
                studentDAO.updateStudent(student.getId(), student);
            }
            sessionDAO.deleteSession(session.getId());
        }

       groupOfStudentsDAO.deleteGroup(Integer.parseInt(query));

        model.addAttribute("error", error);

        return "redirect:Groups.j";

    }

    @RequestMapping("/markPresence.j")
    public String markPresence ( @RequestParam String sess, @RequestParam String id_group){

//EntityExistsException

        System.out.println("im in markpresence");

        System.out.println(sess);

        String[] ids=sess.split(",");


        for (String id :ids){


            SessionOfGroup sessionOfGroup= sessionDAO.getSessionByID(Integer.parseInt(id.split(" ", 2)[0]));
            Student student= studentDAO.getStudentByID(Integer.parseInt(id.split(" ", 2)[1]));

            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try{
                sessionOfGroup.setDate(dateFormat.parse(dateFormat.format(now)));

            }
            catch(Exception e){
                e.printStackTrace();
            }

            sessionOfGroup.getStudentsSet().add(student);
            student.getSessionsSet().add(sessionOfGroup);

            studentDAO.updateStudentSessions(student.getId(), student.getSessionsSet());

            //sessionDAO.updateSessionStudents(sessionOfGroup.getId(), sessionOfGroup.getStudentsSet());
            sessionDAO.updateSession(sessionOfGroup.getId(), sessionOfGroup );

        }

        return "redirect:GroupDetails.j?id_group="+id_group;
    }




}
