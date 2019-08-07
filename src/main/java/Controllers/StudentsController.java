package Controllers;

import DAO.ModuleDAO;
import DAO.PaymentStudentDAO;
import DAO.StudentDAO;
import Entities.GroupOfStudents;
import Entities.PaymentStudent;
import Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@org.springframework.stereotype.Controller

public class StudentsController {

    @Autowired
   private StudentDAO studentDAO;

    @Autowired
    private ModuleDAO moduleDAO;

    @Autowired
    private PaymentStudentDAO paymentStudentDAO;


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

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Students/UnpaidStudentsDataTable";
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

       Long total=0L;

            for(PaymentStudent paymentStudent: paymentStudents){

            total+=paymentStudent.getAmmount();
            }

        model.addAttribute("studentProfile", student);
        model.addAttribute("listOfModules", student.getModulesSet());
        model.addAttribute("payments", paymentStudents);
        model.addAttribute("total",total);


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
