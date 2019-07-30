package Controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller

public class StaffController {

    /** Staff section **/

    @RequestMapping("/Staff")
    public String staffList(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Staff/StaffDataTable";
    }

    @RequestMapping("/addStaff")
    public String addStaff(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Staff/AddStaff";
    }

    @RequestMapping("/updateStaff")
    public String updateStaff(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Staff/UpdateStaff";
    }

    @RequestMapping("/StaffProfile")
    public String staffProfile(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Staff/StaffProfile";
    }





    /** Teachers section **/
    @RequestMapping("/Teachers")
    public String teachersList(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Teachers/TeacherDataTable";
    }

    @RequestMapping("/addTeacher")
    public String AddTeacher(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Teachers/AddTeacher";
    }


    @RequestMapping("/updateTeacher")
    public String updateTeacher(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Teachers/UpdateTeacher";
    }

    @RequestMapping("/TeacherProfile")
    public String teacherProfile(Model model) {

        String error = "";

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Teachers/TeacherProfile";
    }



}
