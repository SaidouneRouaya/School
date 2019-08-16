package Controllers;

import DAO.*;
import Entities.*;
import Entities.Module;
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

    @RequestMapping("/Groups")
    public String pageAccueil(Model model) {

        String error = "";
       /* List<SessionOfGroup> list= sessionDAO.getAllSessions();

        for (SessionOfGroup sessionOfGroup:list){

            System.out.println(" **************** Session " +sessionOfGroup.getId()+" info **************" );

            System.out.println(sessionOfGroup.getStartDate());
            System.out.println(sessionOfGroup.getNumberOfSeances());
            System.out.println(sessionOfGroup.getGroupOfStudents().getName());

            System.out.println(" -- List fo seances --" );
            for (Seance seance:sessionOfGroup.getSeancesSet())
            {
                System.out.println(seance.getDate());
            }
            System.out.println(" -- List of session student --" );
            for (StudentSession studentSession:sessionOfGroup.getStudentSessionsSet())
            {
                System.out.println(studentSession.getSession().getId());
                System.out.println(studentSession.getStudent().getName());
            }



        }
*/
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
        HashMap<Integer, List<Presence> > presenceList= new HashMap<>();


        GroupOfStudents group =groupOfStudentsDAO.getGroupById(Integer.parseInt(id_group));

        List<Student> studentListToAdd= studentDAO.getStudentsByModule(group.getModule().getId());


        HashMap<Integer, List<Student>> studentList=new HashMap<>();


        /** students List by sessions of the group **/
        for (SessionOfGroup sessionOfGroup1 : group.getSessionOfGroupsSet()){
            SessionOfGroup  sessionOfGroup= sessionDAO.getSessionByID(sessionOfGroup1.getId());
            List<Student> students= new ArrayList<>();

            for (StudentSession studentSession: sessionOfGroup.getStudentSessionsSet())
            {
                Student student= studentDAO.getStudentByID(studentSession.getStudent().getId());
                students.add(student);
            }

            studentList.put(sessionOfGroup.getId(), students);

            /** presence list **/
            for (Student stud: students){

                List<Presence> presences= new ArrayList<>();

                for (Seance seance: sessionOfGroup.getSeancesSet()){

                    System.out.println(seance.getId()+" "+seance.getDate());

                    System.out.println("test : " + stud.present(seance));

                    Presence presence=new Presence(seance.getId(), stud.present(seance) );

                    presences.add(presence);

                    System.out.println(presence.present);
                }

                presenceList.put(stud.getId(), presences);

            }

        }

        ArrayList<SessionOfGroup> sessions=new ArrayList<>(group.getSessionOfGroupsSet());

        model.addAttribute("group", group );
        model.addAttribute("students", studentList);
        model.addAttribute("addStudents", studentListToAdd);
        model.addAttribute("sessions", sessions);
        model.addAttribute("size",sessions.size() );
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
        int numberOfSeances= Integer.parseInt(param.get("seancesNumber"));
        Set<StudentSession> studentSessionsSet= new HashSet<>();


        // creation of session
        SessionOfGroup sessionOfGroup= new SessionOfGroup(param.get("startDate"));
        sessionOfGroup.setNumberOfSeances(numberOfSeances);

        // creation of seances
        Set<Seance> seancesList= new HashSet<>();

        for (int i=0; i<numberOfSeances; i++){

            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Seance seance;

            if (i==0){
                seance=new Seance(dateFormat.format(now), sessionOfGroup);
            }else
            {
                seance=new Seance(null, sessionOfGroup);
            }

        // creation of student list
            for (String id_student: studentsList){
            Student student=studentDAO.getStudentByID(Integer.parseInt(id_student));

            seance.getStudentsSet().add(student);
            student.getSeancesSet().add(seance);

                // add seances to session
            sessionOfGroup
                    .getSeancesSet()
                    .add(seance);


                studentSet.add(student);
                // studentSessionsSet.add(studentSession);
            }

            seancesList.add(seance);

        }

        GroupOfStudents groupOfStudents = new GroupOfStudents(
                param.get("name"),
                param.get("startDate"),
                param.get("r3"), param.get("startTime"), param.get("endTime"),
                Float.parseFloat(param.get("fees")), moduleDAO.getModuleByID(Integer.parseInt(param.get("modules"))),
                teacherDAO.getTeacherByID(Integer.parseInt(param.get("teachers"))));


        // creation de la relation session student
        for (Student student: studentSet){
            StudentSession studentSession= new StudentSession(new StudentSessionID(student,sessionOfGroup), sessionOfGroup.getStartDate());

            student.getStudentSessionsSet().add(studentSession);
            sessionOfGroup.getStudentSessionsSet().add(studentSession);
        }


        /** Add new session **/
        groupOfStudents.getSessionOfGroupsSet().add(sessionOfGroup);
        sessionOfGroup.setGroupOfStudents(groupOfStudents);

        /* Persisting */


        groupOfStudentsDAO.addGroup(groupOfStudents);
        sessionDAO.addSession(sessionOfGroup);

        for (Seance seance: seancesList){
            seanceDAO.addSeance(seance);
        }
        for (Student student: studentSet){
            studentDAO.updateStudent(student.getId(), student);
        }

        for (StudentSession studentSession: sessionOfGroup.getStudentSessionsSet()){
            studentSessionDAO.addStudentSession(studentSession);
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


    @RequestMapping("/addSessionToGroup")
    public String addSessionToGroup(Model model, @RequestParam String query, @RequestParam String date, @RequestParam String seancesNumber){

        String error = "";

        int id_group=Integer.parseInt(query);
        int numberOfSeance = Integer.parseInt(seancesNumber);

        GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(id_group);
        Set<Seance> seances = new HashSet<>();


        SessionOfGroup session= new SessionOfGroup(date, seances,groupOfStudents );

        for (int i=0; i<numberOfSeance; i++){
            Seance seance= new Seance(date, session);
            seanceDAO.addSeance(seance);
            seances.add(seance);
        }

        session.setSeancesSet(seances);
        groupOfStudents.getSessionOfGroupsSet().add(session);

        sessionDAO.addSession(session);
        groupOfStudentsDAO.updateGroup(id_group, groupOfStudents);


        model.addAttribute("error", error);

        return "redirect:GroupDetails.j?id_group="+query;
    }



    @RequestMapping("/deleteGroup")
    public String deleteGroup(Model model, @RequestParam String query){

        String error = "";


        GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(Integer.parseInt(query));

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


            Seance seance = seanceDAO.getSeanceByID(Integer.parseInt(id.split(" ", 2)[0]));
            Student student= studentDAO.getStudentByID(Integer.parseInt(id.split(" ", 2)[1]));

            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try{
                seance.setDate(dateFormat.parse(dateFormat.format(now)));

            }
            catch(Exception e){
                e.printStackTrace();
            }

            seance.getStudentsSet().add(student);
            student.getSeancesSet().add(seance);

            studentDAO.updateStudentSessions(student.getId(), student.getSeancesSet());

            //seanceDAO.updateSessionStudents(seance.getId(), seance.getStudentsSet());
            seanceDAO.updateSeance(seance.getId(), seance);

        }

        return "redirect:GroupDetails.j?id_group="+id_group;
    }




}
