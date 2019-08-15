package Controllers;

import DAO.ModuleDAO;
import DAO.PaymentStudentDAO;
import DAO.StudentDAO;
import Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
        model.addAttribute("unpaidStudent", unpaidStudent);
    }

    private HashMap<Integer, List<GroupOfStudents>> groups = new HashMap<>();

    private List<Student> unpaidStudent;


    public List<Student> fillUnpaidStudent(){

        List<Student> students= studentDAO.getAllStudents();


        List<Student> unpaidStudent=new ArrayList<>();


        Date today= new Date();

        boolean bool= false;
        for (Student student: students){


            if (student.getPaymentSet().size() > 0) {

                try {

                    List<GroupOfStudents> groupOfStudent = new ArrayList<>();

                    if (student.getType().equalsIgnoreCase("payee")) {

                        if (student.getSubscriptionDate().before(today)) {

                            for (GroupOfStudents group : student.getGroupsSet()) {

                                if (!student.payedForGroup(group, today)) {

                                    groupOfStudent.add(group);
                                   unpaidStudent.add(student);
                                    bool=true;
                                }

                            }

                            if (bool)  groups.put(student.getId(), groupOfStudent );
                        }

                    }


                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }else {
                unpaidStudent.add(student);
                groups.put(student.getId(), new ArrayList<>(student.getGroupsSet()) );
            }
        }
      return unpaidStudent;
    }


    @RequestMapping("/Students")
    public String studentsList(Model model) {

        String error = "";
        List<Student> studentsList=studentDAO.getAllStudents();
        model.addAttribute("studentsList", studentsList);


        model.addAttribute("studentId",0);

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/StudentsDataTable";
    }

    @RequestMapping("/unpaidStudents")
    public String unpaidStudentsList(Model model) {

        String error = "";


   /*     List<Student> students= studentDAO.getAllStudents();
        List<Student> unpaidStudents= new ArrayList<>();

        HashMap<Integer, List<GroupOfStudents>> groups = new HashMap<>();


        Date today= new Date();

        boolean bool= false;
        for (Student student: students){


            if (student.getPaymentSet().size() > 0) {

                    try {

                        List<GroupOfStudents> groupOfStudent = new ArrayList<>();

                    if (student.getType().equalsIgnoreCase("payee")) {

                        if (student.getSubscriptionDate().before(today)) {

                            for (GroupOfStudents group : student.getGroupsSet()) {

                                if (!student.payedForGroup(group, today)) {

                                    groupOfStudent.add(group);
                                    unpaidStudents.add(student);
                                    bool=true;
                                }

                            }

                            if (bool)  groups.put(student.getId(), groupOfStudent );
                        }

                    }


                }
               catch (Exception e){
                e.printStackTrace();
            }

        }else {
                    unpaidStudents.add(student);
                    groups.put(student.getId(), new ArrayList<>(student.getGroupsSet()) );
                }
        }
*/
        model.addAttribute("unpaidStudents", this.unpaidStudent);
        model.addAttribute("groups", groups);

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/unpaidStudentsDataTable";
    }

  @RequestMapping("/addStudent")
    public String addStudent(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/AddStudent";
    }

  @RequestMapping("/Profile")
    public String Profile(Model model,@RequestParam String query) {

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


        model.addAttribute("modules", moduleDAO.getAllModules());

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/Profile";
    }

    @RequestMapping("/updateProfile")
    public String updateProfile(Model model,  @RequestParam String query) {

        String error = "";
        model.addAttribute("studentProfile", studentDAO.getStudentByID(Integer.parseInt(query)));

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/UpdateProfile";
    }

    /** Operation on student **/
    @RequestMapping("/addNewStudent")
    public String addNewStudent(Model model,  @RequestParam Map<String,String> param) {
        String error = "";
        Student student;

        Set<GroupOfStudents> groups=new HashSet<>();
        Set<SessionOfGroup> session= new HashSet<>();
        Set<Entities.Module> modules = new HashSet<>();
        Set<PaymentStudent> payments = new HashSet<>();

        try{
            if(param.get("discount")!=null){
                student=new Student(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber1")),
                        Integer.parseInt(param.get("phoneNumber2")),param.get("r3"), param.get("subscriptionDate"),
                        Long.parseLong(param.get("discount")), param.get("picture").getBytes(), groups,  modules, session, payments);
            }else
            {
                        student=new Student(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber1")),
                        Integer.parseInt(param.get("phoneNumber2")),param.get("r3"), param.get("subscriptionDate"), param.get("picture").getBytes(),
                         groups,modules,  session, payments);
            }

            studentDAO.addStudent(student);


        }catch(Exception ex){
            error=ex.toString();
            ex.printStackTrace();
        }

        model.addAttribute("error", error);
        return "redirect:Students.j";
    }

    @RequestMapping("/deleteStudent")
    public String deleteStudent(Model model,  @RequestParam String query) {

        String error = "";

        studentDAO.deleteStudent(Integer.parseInt(query));

        model.addAttribute("error", error);
        return "redirect:Students.j";
    }

    @RequestMapping("/PersistUpdate")
    public String persistUpdate(Model model,  @RequestParam String query, @RequestParam Map<String,String> param) {

        String error = "";
        Student student=new Student();

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


            studentDAO.updateStudent(Integer.parseInt(query), student);


        }catch(Exception ex){
            error=ex.toString();
            ex.printStackTrace();
        }
        model.addAttribute("studentProfile", student);

        model.addAttribute("error", error);
        return "redirect:Profile.j?query="+query+"";
    }

    @RequestMapping("/addModuleToStudent")
    public String addModuleToStudent(Model model, @RequestParam String id_student, @RequestParam String modules){

        String error = "";


       int id_module= Integer.parseInt(modules);
       int id_st= Integer.parseInt(id_student);



        Student student= studentDAO.getStudentByID(id_st);
        Entities.Module module= moduleDAO.getModuleByID(id_module);

        student.getModulesSet().add(module);
        studentDAO.updateStudentModules(id_st, student.getModulesSet());

        module.getStudentsSet().add(student);
        moduleDAO.updateGroupStudentsList(id_module, module.getStudentsSet());

        model.addAttribute("error", error);


        return "redirect:Profile.j?query="+id_student+"";
    }


}
