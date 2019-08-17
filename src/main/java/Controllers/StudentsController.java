package Controllers;

import DAO.ModuleDAO;
import DAO.PaymentStudentDAO;
import DAO.StudentDAO;
import Entities.*;
import Entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@org.springframework.stereotype.Controller

@SessionAttributes("unpaidStudent")
public class StudentsController {

    @Autowired
   private StudentDAO studentDAO;

    @Autowired
    private ModuleDAO moduleDAO;

    @Autowired
    private PaymentStudentDAO paymentStudentDAO;

    @ModelAttribute("sessionUser")
    public void setUpUserForm(Model model) {
        unpaidStudent=fillUnpaidStudent();
       while(unpaidStudent.isEmpty())
       {
           unpaidStudent=fillUnpaidStudent();
       }
        model.addAttribute("unpaidStudent", unpaidStudent);
    }

    private HashMap<Integer, List<SessionOfGroup>> sessions = new HashMap<>();

    private List<Student> unpaidStudent;


    public List<Student> fillUnpaidStudent(){

        List<Student> students= studentDAO.getAllStudents();

        if (students==null || students.isEmpty()){
            return new ArrayList<Student>();
        }

        List<Student> unpaidStudent=new ArrayList<>();


        Date today= new Date();

        boolean bool= false;
        for (Student student: students){



            if (student.getPaymentSet().size() > 0) {

                try {
                    if (student.getType().equalsIgnoreCase("payee")) {

                        if (student.getSubscriptionDate().before(today)) {

                            List<SessionOfGroup> sessionsOfStudent= new ArrayList<>();


                            for (StudentSession studentSession : student.getStudentSessionsSet()) {


                                if (!student.payedForSession(studentSession.getSession().getGroupOfStudents(), today)) {

                                    sessionsOfStudent.add(studentSession.getSession());

                                    unpaidStudent.add(student);

                                    bool=true;
                                }

                            }

                            if (bool)  sessions.put(student.getId(), sessionsOfStudent);
                        }

                    }


                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }else {
                unpaidStudent.add(student);
                List<SessionOfGroup> sessionsOfStudent= new ArrayList<>();
                for (StudentSession studentSession : student.getStudentSessionsSet()) {
                    sessionsOfStudent.add(studentSession.getSession());

                }
                sessions.put(student.getId(),sessionsOfStudent );
            }
        }
      return unpaidStudent;
    }


    @RequestMapping("/Students")
    public String studentsList(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        List<Student> studentsList=studentDAO.getAllStudents();

        if (studentsList==null || studentsList.isEmpty()) return ("redirect:/empty.j");

        model.addAttribute("studentsList", studentsList);


        model.addAttribute("studentId",0);

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/StudentsDataTable";
    }

    @RequestMapping("/unpaidStudents")
    public String unpaidStudentsList(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        model.addAttribute("unpaidStudents", this.unpaidStudent);
        model.addAttribute("sessions", sessions);

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/unpaidStudentsDataTable";
    }

  @RequestMapping("/addStudent")
    public String addStudent(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

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
        return "redirect:Students.j";
    }

    @RequestMapping("/deleteStudent")
    public String deleteStudent(Model model,  @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        studentDAO.deleteStudent(Integer.parseInt(query));

        model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "redirect:Students.j";
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
