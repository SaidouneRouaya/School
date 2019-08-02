package Controllers;

import DAO.PaymentStaffDAO;
import DAO.StaffDAO;
import DAO.TeacherDAO;
import Entities.PaymentStaff;
import Entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

import static java.lang.Thread.sleep;

@org.springframework.stereotype.Controller

public class PaymentController {

    @Autowired
    StaffDAO staffDAO;

    @Autowired
    PaymentStaffDAO paymentStaffDAO;

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

        model.addAttribute("staffList", staffDAO.getAllStaffs());


        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/AddStaffPayment";
    }


     @RequestMapping("/addNewStaffPayment")
    public String addNewStaffPayment(Model model, @RequestParam String id_staff) {

        String error = "";


        Staff staff=staffDAO.getStaffByID(Integer.parseInt(id_staff));

         System.out.println(id_staff);

         PaymentStaff paymentStaff=new PaymentStaff(new Date(), staff.getSalary(), "the one connected", staff);

         paymentStaffDAO.addPaymentStaff(paymentStaff);

        model.addAttribute("error", error);


         return "redirect:staffSalaries.j";

    }

    @RequestMapping("/redirect")
    public String redirect(Model model) {

        String error = "";


        model.addAttribute("error", error);
        return "redirect:staffSalaries.j";
    }



}
