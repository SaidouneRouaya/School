package Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller

public class PaymentController {




    @RequestMapping("/studentPayment")
    public String studentsPayment(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/StudentsPaymentDataTable";
    }

    @RequestMapping("/teachersSalaries")
    public String teachersSalaries(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/TeachersSalariesDataTable";
    }


    @RequestMapping("/staffSalaries")
    public String staffSalaries(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/StaffSalariesDataTable";
    }

    @RequestMapping("/addStudentPayment")
    public String addStudentPayment(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/AddStudentPayment";
    }

    @RequestMapping("/addTeacherPayment")
    public String addTeacherPayment(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/AddTeacherPayment";
    }

     @RequestMapping("/addStaffPayment")
    public String addStaffPayment(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/AddStaffPayment";
    }




}
