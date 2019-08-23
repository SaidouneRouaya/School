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
    public String pageAccueil(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";


        model.addAttribute("profile", profile);
        List<GroupOfStudents> groups = groupOfStudentsDAO.getAllGroups();

        if (groups==null ||groups.isEmpty()) return ("redirect:/empty.j");

        model.addAttribute("groupsList", groups );

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsList";
    }

    @RequestMapping("/GroupsByModule")
    public String GroupsByModule(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";
        model.addAttribute("profile", profile);

        Map <String, List<GroupOfStudents>> groupsByModules =groupOfStudentsDAO.getAllGroupsByModules();

        if (groupsByModules==null || groupsByModules.isEmpty()) return ("redirect:/empty.j");


        model.addAttribute("groupsListByModule", groupsByModules);

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupsByModule";
    }

    @RequestMapping("/GroupDetails")
    public String groupDetails(Model model, @RequestParam String id_group, @SessionAttribute ("unpaidStudent") List<Student> unpaidStudent, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<Integer, List<Presence> > presenceList= new HashMap<>();


        GroupOfStudents group =groupOfStudentsDAO.getGroupById(Integer.parseInt(id_group));

        List<Student> studentListToAdd= studentDAO.getStudentsByModule(group.getModule().getId());


        HashMap<Integer, List<Student>> studentList=new HashMap<>();


        /** students List by sessions of the group **/

        for (SessionOfGroup sessionOfGroup1 : group.getSessionOfGroupsSet()){
            System.out.println("session ID: "+sessionOfGroup1.getId());

            SessionOfGroup  sessionOfGroup= sessionDAO.getSessionByID(sessionOfGroup1.getId());
            List<Student> students= new ArrayList<>();

            for (StudentSession studentSession: sessionOfGroup.getStudentSessionsSet())
            {

                Student student= studentDAO.getStudentByID(studentSession.getStudent().getId());

                System.out.println("seesion "+studentSession.getSession().getId()+" student "+student.getName()
                        +" Id: " +studentSession.getStudent().getName());



                List<Presence> presences= new ArrayList<>();
                System.out.println("\n\nStudent seances size: "+student.getSeancesSet().size()+"\n");
                for (Seance seance: sessionOfGroup.getSeancesSet()){

                    System.out.println("Seance "+seance.getId()+" "+seance.getDate());


                    System.out.println("was present? : " + student.present(seance));

                    Presence presence=new Presence(seance.getId(), student.present(seance) );

                    presences.add(presence);

                    System.out.println(presence.present);
                }

                presenceList.put(student.getId(), presences);


                students.add(student);

            }

            studentList.put(sessionOfGroup.getId(), students);



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
        model.addAttribute("profile", profile);

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/GroupDetails";
    }

    @RequestMapping("/addGroup")
    public String addGroup(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        List<Module> modules= moduleDAO.getAllModules();
        HashMap<Integer, List<Student>> students= studentDAO.getStudentsByModules(modules);
        List<Teacher> teachers= teacherDAO.getAllTeachers();


        if(modules.isEmpty() || students.isEmpty() || teachers.isEmpty()) return ("redirect:/empty.j");

        model.addAttribute("modulesList", modules);
        model.addAttribute("studentList", students);
        model.addAttribute("teacherList", teachers );
        model.addAttribute("profile", profile);

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Groups/AddGroup";
    }

    @RequestMapping("/addNewGroup")
    public String addNewGroup(Model model , @RequestParam Map<String, String> param, @RequestParam List<String> studentsList, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        Set<Student> studentSet=new HashSet<>();
        int numberOfSeances= Integer.parseInt(param.get("seancesNumber"));


        // creation of session
        SessionOfGroup sessionOfGroup= new SessionOfGroup(param.get("startDate"));
        sessionOfGroup.setNumberOfSeances(numberOfSeances);

        // creation of seances
        Set<Seance> seancesList= new HashSet<>();

        for (int i=0; i<numberOfSeances; i++){

            Date now = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Seance seance;

          /*  if (i==0){
                seance=new Seance(dateFormat.format(now), sessionOfGroup);
            }else
            {
                seance=new Seance(null, sessionOfGroup);
            }*/



                seance=new Seance(null, sessionOfGroup);



                // add seances to session
            sessionOfGroup
                    .getSeancesSet()
                    .add(seance);


            seancesList.add(seance);

        }

        // creation of student list
        for (String id_student: studentsList){
            Student student=studentDAO.getStudentByID(Integer.parseInt(id_student));
            studentSet.add(student);
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


        model.addAttribute("profile", profile);
        model.addAttribute("error", error);
        return "redirect:Groups.j";
    }

    @RequestMapping("/updateGroup")
    public String updateGroupsOfStudent(Model model,  @RequestParam String query, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        model.addAttribute("teachers" , teacherDAO.getAllTeachers());
        model.addAttribute("modules" , moduleDAO.getAllModules());
        model.addAttribute("group", groupOfStudentsDAO.getGroupById(Integer.parseInt(query)));
        model.addAttribute("profile", profile);
        model.addAttribute("error", error);

        return "LanguagesSchoolPages/Groups/UpdateGroup";
    }

    @RequestMapping("/PersistUpdateGroup")
    public String persistUpdateGroupsOfStudent(Model model,  @RequestParam String query, @RequestParam Map<String,String> param, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";
        GroupOfStudents group= new GroupOfStudents();
        Teacher teacher;
        Module module;
        int id_teacher = Integer.parseInt(param.get("teachers"));
        int id_module = Integer.parseInt(param.get("modules"));


        try{

            if (id_teacher!=0 ){
                if (id_module!=0 ){
                    teacher = teacherDAO.getTeacherByID(id_teacher);
                    module= moduleDAO.getModuleByID(id_module);

                    group=new GroupOfStudents(param.get("name"), param.get("r3"), Integer.parseInt(param.get("sessionNumber")), teacher, module);

                }else{
                    teacher = teacherDAO.getTeacherByID(id_teacher);
                    group=new GroupOfStudents(param.get("name"), param.get("r3"), Integer.parseInt(param.get("sessionNumber")), teacher);
                }
            }else
            {
                group=new GroupOfStudents(param.get("name"), param.get("r3"), Integer.parseInt(param.get("sessionNumber")));

            }

            groupOfStudentsDAO.updateGroup(Integer.parseInt(query), group);

        }catch(Exception ex){
            error=ex.toString();
            ex.printStackTrace();
        }
        model.addAttribute("group", group);
        model.addAttribute("profile", profile);
        model.addAttribute("error", error);
        return "redirect:GroupDetails.j?id_group="+query;
    }


    @RequestMapping("/addSessionToGroup")
    public String addSessionToGroup(Model model, @RequestParam String query, @RequestParam String date, @RequestParam String seancesNumber, @SessionAttribute ("sessionUser") Profile profile){

        String error = "";


        int numberOfSeances= Integer.parseInt(seancesNumber);
        int id_group=Integer.parseInt(query);

        GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(Integer.parseInt(query));


        // creation of session
        SessionOfGroup sessionOfGroup= new SessionOfGroup(date);
        sessionOfGroup.setNumberOfSeances(numberOfSeances);

        // creation of seances
        Set<Seance> seancesList= new HashSet<>();

        for (int i=0; i<numberOfSeances; i++){

           Seance seance;
            seance=new Seance(null, sessionOfGroup);



            seancesList.add(seance);

        }



        /** Add new session **/
        groupOfStudents.getSessionOfGroupsSet().add(sessionOfGroup);
        sessionOfGroup.setGroupOfStudents(groupOfStudents);

        /* Persisting */
        sessionOfGroup.setSeancesSet(seancesList);
        sessionDAO.addSession(sessionOfGroup);

        groupOfStudentsDAO.updateGroup(id_group, groupOfStudents);


        for (Seance seance: seancesList){
            seanceDAO.addSeance(seance);
        }

        sessionDAO.updateSession(sessionOfGroup.getId(), sessionOfGroup);

        model.addAttribute("profile", profile);
        model.addAttribute("error", error);

        return "redirect:GroupDetails.j?id_group="+query;

    }

    @RequestMapping("/addSessionToGroup2")
    public String addSessionToGroup2(Model model, @RequestParam String query, @RequestParam String date, @RequestParam String seancesNumber, @SessionAttribute ("sessionUser") Profile profile){

        String error = "";

        int id_group=Integer.parseInt(query);
        int numberOfSeance = Integer.parseInt(seancesNumber);

        GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(id_group);
        Set<Seance> seances = new HashSet<>();


        SessionOfGroup session= new SessionOfGroup(date, seances,groupOfStudents );

        groupOfStudents.getSessionOfGroupsSet().add(session);

        sessionDAO.addSession(session);

        for (int i=0; i<numberOfSeance; i++){
            Seance seance= new Seance(date, session);
            seanceDAO.addSeance(seance);
            seances.add(seance);
        }

        session.setSeancesSet(seances);
        sessionDAO.updateSession(session.getId(), session);
        groupOfStudentsDAO.updateGroup(id_group, groupOfStudents);

        model.addAttribute("profile", profile);
        model.addAttribute("error", error);

        return "redirect:GroupDetails.j?id_group="+query;
    }



    @RequestMapping("/deleteGroup")
    public String deleteGroup(Model model, @RequestParam String query, @SessionAttribute ("sessionUser") Profile profile){

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
        model.addAttribute("profile", profile);
        model.addAttribute("error", error);

        return "redirect:Groups.j";

    }


    @RequestMapping("/deleteStudentFromSession")
    public String deleteStudentFromSession(Model model, @RequestParam String query, @RequestParam String id_session,  @RequestParam String group,@SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        Student student = studentDAO.getStudentByID(Integer.parseInt(query));

        SessionOfGroup sessionOfGroup= sessionDAO.getSessionByID(Integer.parseInt(id_session));

        StudentSession studentSession= new StudentSession(new StudentSessionID(student, sessionOfGroup));

        studentSessionDAO.deleteStudentSession(studentSession);

        model.addAttribute("profile", profile); model.addAttribute("error", error);

        return "redirect:GroupDetails.j?id_group="+group;

    }




    @RequestMapping("/markPresence.j")
    public String markPresence ( @RequestParam String sess, @RequestParam String id_group, @RequestParam String id_session, @SessionAttribute ("sessionUser") Profile profile){



        int idSession = Integer.parseInt(id_session);
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");



        if (!seanceDAO.getSeanceByDate(now, idSession).isEmpty())   {
            return "redirect:GroupDetails.j?id_group="+id_group;
        }




        String[] ids=sess.split(",");
        for (String id :ids){


            Seance seance = seanceDAO.getSeanceByID(Integer.parseInt(id.split(" ", 2)[0]));
            Student student= studentDAO.getStudentByID(Integer.parseInt(id.split(" ", 2)[1]));


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
