package Controllers;

import DAO.StudentDAO;
import Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@org.springframework.stereotype.Controller

public class StudentsController {

    @Autowired
   private StudentDAO studentDAO;



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


        model.addAttribute("studentProfile", studentDAO.getStudentByID(Integer.parseInt(query)));
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
    @RequestMapping("/PersistUpdate")
    public String persistUpdate(Model model,  @RequestParam String query, @RequestParam Map<String,String> param) {

        String error = "";
        Student student=new Student();

        System.out.println(param.get("name"));
        System.out.println(param.get("familyName"));
        System.out.println(Integer.parseInt(param.get("phoneNumber1")));
        System.out.println(param.get("phoneNumber1"));
        System.out.println(Integer.parseInt(param.get("phoneNumber2")));
        System.out.println(param.get("phoneNumber2"));
        System.out.println(param.get("r3"));
        System.out.println(param.get("subscriptionDate"));

        try{
           student=new Student(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber1")),
                    Integer.parseInt(param.get("phoneNumber2")),param.get("r3"), param.get("subscriptionDate"), param.get("picture").getBytes());


            studentDAO.updateStudent(Integer.parseInt(query), student);


        }catch(Exception ex){
            error=ex.toString();
            ex.printStackTrace();
        }
        model.addAttribute("studentProfile", student);

        model.addAttribute("error", error);
        return "redirect:Profile.j?query="+query+"";
    }

    /** Operation on student **/
    @RequestMapping("/addNewStudent")
    public String addNewStudent(Model model,  @RequestParam Map<String,String> param) {
        String error = "";
        Student student;


        try{
            student=new Student(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber1")),
                    Integer.parseInt(param.get("phoneNumber2")),param.get("r3"), param.get("subscriptionDate"), param.get("picture").getBytes());

            studentDAO.addStudent(student);


        }catch(Exception ex){
            error=ex.toString();
            ex.printStackTrace();
        }

        model.addAttribute("error", error);
        return "redirect:Students.j";
    }

}
