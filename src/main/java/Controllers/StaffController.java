package Controllers;


import DAO.*;
import Entities.*;

import Entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.*;

@org.springframework.stereotype.Controller

public class StaffController {


    @Autowired
    StaffDAO staffDAO;

    @Autowired
    TeacherDAO teacherDAO;

    @Autowired
    ModuleDAO moduleDAO;

    @Autowired
    GroupOfStudentsDAO groupsDAO;

    @Autowired
    PaymentTeacherDAO paymentTeacherDAO;
    @Autowired
    PaymentStaffDAO paymentStaffDAO;

    /**
     * Staff section
     **/

    @RequestMapping("/Staff")
    public String staffList(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")|| profile.getType().equals("Receptionist")) {

               List<Staff> staff= staffDAO.getAllStaffs();

                if (staff==null || staff.isEmpty()) return ("redirect:/empty.j");


                model.addAttribute("staffList", staff );

                model.addAttribute("error", error);
                model.addAttribute("profile", profile);
                return "LanguagesSchoolPages/Staff/StaffDataTable";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }


    @RequestMapping("/addStaff")
    public String addStaff(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        model.addAttribute("error", error);

        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                model.addAttribute("user", profile);
                return "LanguagesSchoolPages/Staff/AddStaff";

            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }

    }

    @RequestMapping("/updateStaff")
    public String updateStaff(Model model, @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                model.addAttribute("staffProfile", staffDAO.getStaffByID(Integer.parseInt(query)));

                model.addAttribute("error", error);
                return "LanguagesSchoolPages/Staff/UpdateStaff";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/StaffProfile")
    public String staffProfile(Model model, @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {


                Staff staff = staffDAO.getStaffByID(Integer.parseInt(query));
                List<PaymentStaff> paymentStaffs = paymentStaffDAO.getPaymentsByStaff(staff.getId());
                float total = 0L;

                for (PaymentStaff paymentStaff : paymentStaffs) {

                    total += paymentStaff.getAmmount();
                }

                model.addAttribute("staffProfile", staff);
                model.addAttribute("payments", paymentStaffs);
                model.addAttribute("total", total);

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "LanguagesSchoolPages/Staff/StaffProfile";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/addNewStaff")
    public String addNewStaff(Model model, @RequestParam Map<String, String> param, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {


                Staff staff = new Staff(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber")),
                        param.get("job"), param.get("employmentDate"), Long.parseLong(param.get("salary")));

                staffDAO.addStaff(staff);

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "redirect:Staff.j";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/deleteStaff")
    public String deleteStaff(Model model, @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                int id_staff= Integer.parseInt(query);

                staffDAO.deleteStaff(id_staff);

                model.addAttribute("profile", profile);
                model.addAttribute("error", error);

                return "redirect:Staff.j";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/PersistUpdateStaff")
    public String persistUpdateStaff(Model model, @RequestParam String query, @RequestParam Map<String, String> param, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";


        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                Staff staff = new Staff(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber")),
                        param.get("job"), param.get("employmentDate"), Long.parseLong(param.get("salary")));

                staffDAO.updateStaff(Integer.parseInt(query), staff);

                model.addAttribute("staffProfile", staff);

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "redirect:StaffProfile.j?query=" + query + "";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }


    /**
     * Teachers section
     **/
    @RequestMapping("/Teachers")
    public String teachersList(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin") || profile.getType().equals("Receptionist")) {

                List<Teacher> teachersList = teacherDAO.getAllTeachers();


                if (teachersList==null ||teachersList.isEmpty()) return ("redirect:/empty.j");

                model.addAttribute("teachersList", teachersList);

                HashMap<Integer,List<Module>> modulesList= new HashMap<>() ;

                for (Teacher teacher : teachersList) {

                    modulesList.put(teacher.getId(), new ArrayList<>(teacher.getTeacherModulesSet()));
                }

                model.addAttribute("modulesList", modulesList);


                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "LanguagesSchoolPages/Teachers/TeacherDataTable";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/addTeacher")
    public String AddTeacher(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                List<Module> modules= moduleDAO.getAllModules();


                if (modules==null || modules.isEmpty()) return ("redirect:/empty.j");
                model.addAttribute("modules",modules );


                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "LanguagesSchoolPages/Teachers/AddTeacher";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/updateTeacher")
    public String updateTeacher(Model model, @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                model.addAttribute("teacherProfile", teacherDAO.getTeacherByID(Integer.parseInt(query)));

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "LanguagesSchoolPages/Teachers/UpdateTeacher";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/TeacherProfile")
    public String teacherProfile(Model model, @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                Teacher teacher = teacherDAO.getTeacherByID(Integer.parseInt(query));
                List<PaymentTeacher> paymentTeachers = paymentTeacherDAO.getPaymentsByTreacher(teacher.getId());
                float total = 0L;

                for (PaymentTeacher paymentTeacher : paymentTeachers) {

                    total += paymentTeacher.getTotal();
                }

                model.addAttribute("teacherProfile", teacher);
                model.addAttribute("payments", paymentTeachers);
                model.addAttribute("total", total);

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "LanguagesSchoolPages/Teachers/TeacherProfile";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/addNewTeacher")
    public String addNewTeacher(Model model, @RequestParam Map<String, String> param, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                Set<Module> modules = new HashSet<>();
                Set<PaymentTeacher> paymentTeachers = new HashSet<>();
                Set<GroupOfStudents> groupOfStudents = new HashSet<>();

                Module module = moduleDAO.getModuleByID(Integer.parseInt(param.get("modules")));

                Teacher teacher = new Teacher(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber")),
                        param.get("employmentDate"), groupOfStudents, paymentTeachers, modules);


                modules.add(module);
                module.getModuleTeachersSet().add(teacher);

                teacherDAO.addTeacher(teacher);

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "redirect:Teachers.j";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/deleteTeacher")
    public String deleteTeacher(Model model, @RequestParam String query, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                int id_teacher = Integer.parseInt(query);

                Teacher teacher = teacherDAO.getTeacherByID(id_teacher);

                Set<GroupOfStudents> groups = teacher.getGroupsSet();
                Set<Module> modules = teacher.getTeacherModulesSet();


                for (GroupOfStudents group : groups) {
                    group.setTeacher(null);
                    groupsDAO.updateGroupTeacher(group.getId(), group);
                }

                for (Module modulee: modules){
                    Module module = moduleDAO.getModuleByID(modulee.getId());

                    module.removeTeacher(teacher);
                    moduleDAO.updateModule(module.getId(), module );
                }

                teacherDAO.deleteTeacher(Integer.parseInt(query));

                model.addAttribute("profile", profile);
                model.addAttribute("error", error);
                return "redirect:Teachers.j";

            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/PersistUpdateTeacher")
    public String persistUpdateTeacher(Model model, @RequestParam String query, @RequestParam Map<String, String> param, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equals("Admin")) {

                Teacher teacher = new Teacher(param.get("name"), param.get("familyName"), Integer.parseInt(param.get("phoneNumber")),
                        param.get("employmentDate"));

                teacherDAO.updateTeacher(Integer.parseInt(query), teacher);

                model.addAttribute("teacherProfile", teacher);

                model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "redirect:TeacherProfile.j?query=" + query + "";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }


}
