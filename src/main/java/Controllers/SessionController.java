package Controllers;

import DAO.*;
import Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@org.springframework.stereotype.Controller
public class SessionController {

    @Autowired
    GroupOfStudentsDAO groupOfStudentsDAO;

    @Autowired
    StudentSessionDAO studentSessionDAO;

    @Autowired
    SessionDAO sessionDAO;

    @Autowired
    ModuleDAO moduleDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    TeacherDAO teacherDAO;

    @Autowired
    SeanceDAO seanceDAO;




    @RequestMapping("/addStudentToSession")
    public String addStudentToSession(Model model, @RequestParam String query,@RequestParam String id_group, @RequestParam String students){

        String error = "";

        int id_session=Integer.parseInt(query);
        int id_student=Integer.parseInt(students);

        Student student= studentDAO.getStudentByID(Integer.parseInt(students));
        SessionOfGroup sessionOfGroup= sessionDAO.getSessionByID(id_session);


        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StudentSession studentSession= new StudentSession();
        try{
             studentSession= new StudentSession(new StudentSessionID(student,sessionOfGroup), dateFormat.parse(dateFormat.format(now)));

        }catch (Exception e){
            e.printStackTrace();
        }

        studentSessionDAO.addStudentSession(studentSession);

        studentDAO.updateStudent(id_student, student);
        sessionDAO.updateSession(id_session, sessionOfGroup);


        model.addAttribute("error", error);

        return "redirect:GroupDetails.j?id_group="+id_group;
    }




}
