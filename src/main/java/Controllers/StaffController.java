package Controllers;


import DAO.StaffDAO;
import DAO.TeacherDAO;
import Entities.Module;
import Entities.Staff;

import Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller

public class StaffController {


    @Autowired
    StaffDAO staffDAO;

    @Autowired
    TeacherDAO teacherDAO;


    /** Staff section **/

    @RequestMapping("/Staff")
    public String staffList(Model model) {

        String error = "";

        model.addAttribute("staffList", staffDAO.getAllStaffs());
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
    public String updateStaff(Model model,  @RequestParam String query) {

        String error = "";
        model.addAttribute("staffProfile", staffDAO.getStaffByID(Integer.parseInt(query)));

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Staff/UpdateStaff";
    }

    @RequestMapping("/StaffProfile")
    public String staffProfile(Model model, @RequestParam String query) {

        String error = "";

        model.addAttribute("staffProfile", staffDAO.getStaffByID(Integer.parseInt(query)));
        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Staff/StaffProfile";
    }

    @RequestMapping("/addNewStaff")
    public String addNewStaff(Model model, @RequestParam Map<String,String> param) {

        String error = "";

        Staff staff= new Staff(param.get("name"), param.get("familyName"),Integer.parseInt(param.get("phoneNumber")),
                param.get("job"),  param.get("employmentDate"), Long.parseLong(param.get("salary")), param.get("picture").getBytes());

        staffDAO.addStaff(staff);

        model.addAttribute("error", error);
        return "redirect:Staff.j";
    }

    @RequestMapping("/deleteStaff")
    public String deleteStaff(Model model,  @RequestParam String query) {

        String error = "";

        staffDAO.deleteStaff(Integer.parseInt(query));

        model.addAttribute("error", error);
        return "redirect:Staff.j";
    }

    @RequestMapping("/PersistUpdateStaff")
    public String persistUpdateStaff(Model model,  @RequestParam String query, @RequestParam Map<String,String> param) {

        String error = "";



        Staff staff= new Staff(param.get("name"), param.get("familyName"),Integer.parseInt(param.get("phoneNumber")),
                param.get("r3"),  param.get("employmentDate"), Long.parseLong(param.get("salary")), param.get("picture").getBytes());

            staffDAO.updateStaff(Integer.parseInt(query), staff);

        model.addAttribute("staffProfile", staff);

        model.addAttribute("error", error);
        return "redirect:StaffProfile.j?query="+query+"";
    }


    /** Teachers section **/
    @RequestMapping("/Teachers")
    public String teachersList(Model model) {

        String error = "";
        List<Teacher> teachersList= teacherDAO.getAllTeachers();
        model.addAttribute("teachersList", teachersList );

        List<HashSet<Module>> modulesList= new ArrayList<>();

        for (Teacher teacher: teachersList){

           HashSet<Module> modules=new HashSet<>(teacher.getTeacherModulesSet());

            modulesList.add(modules);
        }

        model.addAttribute("modulesList", modulesList);



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
    public String updateTeacher(Model model, @RequestParam String query) {

        String error = "";
        model.addAttribute("teacherProfile", teacherDAO.getTeacherByID(Integer.parseInt(query)));

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Teachers/UpdateTeacher";
    }

    @RequestMapping("/TeacherProfile")
    public String teacherProfile(Model model, @RequestParam String query) {

        String error = "";
        model.addAttribute("teacherProfile", teacherDAO.getTeacherByID(Integer.parseInt(query)));
        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Teachers/TeacherProfile";
    }


    @RequestMapping("/addNewTeacher")
    public String addNewTeacher(Model model, @RequestParam Map<String,String> param) {

        String error = "";

        Teacher teacher= new Teacher(param.get("name"), param.get("familyName"),Integer.parseInt(param.get("phoneNumber")),
                param.get("employmentDate"), Long.parseLong(param.get("salary")), param.get("picture").getBytes());

        teacherDAO.addTeacher(teacher);

        model.addAttribute("error", error);
        return "redirect:Teachers.j";
    }

    @RequestMapping("/deleteTeacher")
    public String deleteTeacher(Model model,  @RequestParam String query) {

        String error = "";

        teacherDAO.deleteTeacher(Integer.parseInt(query));

        model.addAttribute("error", error);
        return "redirect:Teachers.j";
    }

    @RequestMapping("/PersistUpdateTeacher")
    public String persistUpdateTeacher(Model model,  @RequestParam String query, @RequestParam Map<String,String> param) {

        String error = "";

        Teacher teacher= new Teacher(param.get("name"), param.get("familyName"),Integer.parseInt(param.get("phoneNumber")),
                param.get("employmentDate"), Long.parseLong(param.get("salary")), param.get("picture").getBytes());

        teacherDAO.updateTeacher(Integer.parseInt(query), teacher);

        model.addAttribute("teacherProfile", teacher);

        model.addAttribute("error", error);
        return "redirect:TeacherProfile.j?query="+query+"";
    }




}
