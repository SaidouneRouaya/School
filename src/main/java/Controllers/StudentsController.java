package Controllers;

import DAO.*;
import Entities.*;
import Entities.Module;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@org.springframework.stereotype.Controller

@SessionAttributes("unpaidStudent")
public class StudentsController {

    @Autowired
   private StudentDAO studentDAO;

    @Autowired
    private ModuleDAO moduleDAO;

    @Autowired
    private SeanceDAO seanceDAO;

    @Autowired
    private StudentSessionDAO studentSessionDAO;

    @Autowired
    private PaymentStudentDAO paymentStudentDAO;

    @ModelAttribute("sessionUser")
    public void setUpUserForm(Model model) {
        unpaidStudent=fillUnpaidStudent();

        model.addAttribute("unpaidStudent", unpaidStudent);
    }

    private HashMap<Integer, List<SessionOfGroup>> sessions = new HashMap<>();

    private List<Student> unpaidStudent;


    public List<Student> fillUnpaidStudent() {

        List<Student> students = studentDAO.getAllStudents();

        if (students == null || students.isEmpty()) return new ArrayList<>();


        List<Student> unpaidStudent = new ArrayList<>();
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date today = dateFormat.parse(dateFormat.format(now));

            for (Student student : students) {

                boolean bool = false;

                if (student.getType().equalsIgnoreCase("payee")) {

                    List<SessionOfGroup> sessionsOfStudent = new ArrayList<>();
                    try {
                        if (student.getPaymentSet().size() > 0) {

                            if (student.getSubscriptionDate().before(today)) {

                                if (student.getStudentSessionsSet() != null && !student.getStudentSessionsSet().isEmpty()) {

                                    for (StudentSession studentSession : student.getStudentSessionsSet()) {



                                        if (!student.payedForSession(studentSession.getSession().getGroupOfStudents(), today)) {

                                            sessionsOfStudent.add(studentSession.getSession());
                                            unpaidStudent.add(student);

                                            bool = true;
                                        }
                                    }

                                    if (bool) sessions.put(student.getId(), sessionsOfStudent);
                                }

                            }

                        } else {

                            unpaidStudent.add(student);

                            for (StudentSession studentSession : student.getStudentSessionsSet()) {
                                sessionsOfStudent.add(studentSession.getSession());

                            }

                            sessions.put(student.getId(), sessionsOfStudent);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unpaidStudent;
    }


    @RequestMapping("/Students")
    public String studentsList(Model model, @SessionAttribute("sessionUser") Profile profile, @RequestParam String home)  {

        String error = "";

        if (home.equals("true")){
            return ("redirect:Home.j");
        }

        List<Student> studentsList=studentDAO.getAllStudents();

        if (studentsList==null || studentsList.isEmpty()) return ("redirect:/empty.j");

        unpaidStudent=fillUnpaidStudent();

        model.addAttribute("unpaidStudents", this.unpaidStudent);

        model.addAttribute("studentsList", studentsList);


        model.addAttribute("studentId",0);

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/StudentsDataTable";
    }

    @RequestMapping("/unpaidStudents")
    public String unpaidStudentsList(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";


        unpaidStudent=fillUnpaidStudent();

        if(unpaidStudent.isEmpty() || sessions.isEmpty()) return ("redirect:/empty.j");

        for (Student student: unpaidStudent) System.out.println(student.getName());

        Set<Integer> kies= sessions.keySet();


        for (int key : kies ){

            List<SessionOfGroup> list= sessions.get(key);

            for (SessionOfGroup s: list){
                System.out.println("session "+s.getId());
            }


        }

        model.addAttribute("unpaidStudents", this.unpaidStudent);
        model.addAttribute("sessions", sessions);

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/unpaidStudentsDataTable";
    }

  @RequestMapping("/addStudent")
    public String addStudent(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        unpaidStudent=fillUnpaidStudent();
      System.out.println();
        model.addAttribute("unpaidStudents", this.unpaidStudent);
        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/AddStudent";
    }

  @RequestMapping("/Profile")
    public String Profile(Model model,@RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";


        Student student=studentDAO.getStudentByID(Integer.parseInt(query));
        List <PaymentStudent> paymentStudents=paymentStudentDAO.getPaymentsByStudent(student.getId());

      float total=0L;

            for(PaymentStudent paymentStudent: paymentStudents){

            total+=paymentStudent.getAmount();
            }

        model.addAttribute("payments", paymentStudents);
        model.addAttribute("studentProfile", student);
        model.addAttribute("listOfModules", student.getModulesSet());
        model.addAttribute("total", total);


        List<Module> modules= moduleDAO.getAllModules();
      if (modules==null || modules.isEmpty()) return ("redirect:/empty.j");

      model.addAttribute("modules", modules);

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/Profile";
    }

    @RequestMapping("/updateProfile")
    public String updateProfile(Model model,  @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        model.addAttribute("studentProfile", studentDAO.getStudentByID(Integer.parseInt(query)));

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/UpdateProfile";
    }

    /** Operation on student **/
    @RequestMapping("/addNewStudent")
    public String addNewStudent(Model model,  @RequestParam Map<String,String> param, @SessionAttribute("sessionUser") Profile profile) {
        String error = "";
        Student student;


        Set<StudentSession> studentSessions = new HashSet<>();

        Set<Seance> session= new HashSet<>();
        Set<Entities.Module> modules = new HashSet<>();
        Set<PaymentStudent> payments = new HashSet<>();

        try{
            if(param.get("discount")!=null){
                student=new Student(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber1")),
                        Integer.parseInt(param.get("phoneNumber2")),param.get("r3"), param.get("subscriptionDate"),
                        Long.parseLong(param.get("discount")), param.get("picture").getBytes(), studentSessions,  modules, session, payments);
            }else
            {
                        student=new Student(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber1")),
                        Integer.parseInt(param.get("phoneNumber2")),param.get("r3"), param.get("subscriptionDate"), param.get("picture").getBytes(),
                                studentSessions,modules,  session, payments);
            }

            studentDAO.addStudent(student);


        }catch(Exception ex){
            error=ex.toString();
            ex.printStackTrace();
        }

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "redirect:Students.j?home=1";
    }

    @RequestMapping("/deleteStudent")
    public String deleteStudent(Model model,  @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        int id_student= Integer.parseInt(query);

        Student student= studentDAO.getStudentByID(id_student);

      /*  Set<PaymentStudent> payments= student.getPaymentSet();



        for (PaymentStudent pay: payments){

            paymentStudentDAO.deletePaymentStudent(pay.getId());
        }*/

        Set<Module> modules=  student.getModulesSet();
        for (Module modulee : modules){

            Module module=moduleDAO.getModuleByID(modulee.getId());
            module.removeStudent(student);
            moduleDAO.updateModule(module.getId(), module);
        }

        Set<Seance>  seances= student.getSeancesSet();
        for (Seance seance: seances){
            seance.removeStudent(student);
            seanceDAO.updateSeance(seance.getId(), seance);
        }

        Set<StudentSession> sessions = student.getStudentSessionsSet();

        for (StudentSession studentSession: sessions){

            studentSessionDAO.deleteStudentSession(studentSession);
        }

        studentDAO.deleteStudent(Integer.parseInt(query));

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "redirect:Students.j?home=1";
    }

    @RequestMapping("/PersistUpdate")
    public String persistUpdate(Model model,  @RequestParam String query, @RequestParam Map<String,String> param, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        Student student=new Student();

        Student student1= studentDAO.getStudentByID(Integer.parseInt(query));

        try{

            if(param.get("discount")!=null){
                student=new Student(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber1")),
                        Integer.parseInt(param.get("phoneNumber2")),param.get("r3"), param.get("subscriptionDate"),
                        Long.parseLong(param.get("discount")), param.get("picture").getBytes());

            }else
            {
                student=new Student(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber1")),
                        Integer.parseInt(param.get("phoneNumber2")),param.get("r3"), param.get("subscriptionDate"), param.get("picture").getBytes());
            }

            student.setSeancesSet(student1.getSeancesSet());
            student.setModulesSet(student1.getModulesSet());
            student.setPaymentSet(student1.getPaymentSet());
            student.setStudentSessionsSet(student1.getStudentSessionsSet());

            studentDAO.updateStudent(Integer.parseInt(query), student);


        }catch(Exception ex){
            error=ex.toString();
            ex.printStackTrace();
        }
        model.addAttribute("studentProfile", student);

        model.addAttribute("profile", profile);
        model.addAttribute("error", error);
        return "redirect:Profile.j?query="+query+"";
    }

    @RequestMapping("/addModuleToStudent")
    public String addModuleToStudent(Model model, @RequestParam String id_student, @RequestParam String modules, @SessionAttribute("sessionUser") Profile profile){

        String error = "";


       int id_module= Integer.parseInt(modules);
       int id_st= Integer.parseInt(id_student);



        Student student= studentDAO.getStudentByID(id_st);
        Entities.Module module= moduleDAO.getModuleByID(id_module);

        student.getModulesSet().add(module);
        studentDAO.updateStudentModules(id_st, student.getModulesSet());

        module.getStudentsSet().add(student);
        moduleDAO.updateGroupStudentsList(id_module, module.getStudentsSet());

        model.addAttribute("profile", profile);
        model.addAttribute("error", error);


        return "redirect:Profile.j?query="+id_student+"";
    }


}
