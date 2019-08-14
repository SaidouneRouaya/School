package Controllers;

import DAO.*;
import Entities.*;
import Entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


import java.util.*;

import static java.lang.Thread.sleep;

@org.springframework.stereotype.Controller
@Scope("session")

public class PaymentController {

    @Autowired
    StaffDAO staffDAO;

    @Autowired
    TeacherDAO teacherDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    GroupOfStudentsDAO groupOfStudentsDAO;

    @Autowired
    PaymentStaffDAO paymentStaffDAO;

    @Autowired
    PaymentTeacherDAO paymentTeacherDAO;

    @Autowired
    PaymentStudentDAO paymentStudentDAO;

    @Autowired
    SessionDAO sessionDAO;


    @RequestMapping("/studentPayment")
    public String studentsPayment(Model model) {

        String error = "";

        List<PaymentStudent> studentPaymentList = paymentStudentDAO.getAllPaymentStudents();
        model.addAttribute("studentPaymentList", studentPaymentList);

        float total = 0f;

        for (PaymentStudent paymentStudent : studentPaymentList) {
            total += paymentStudent.getAmount();
        }
        model.addAttribute("totalPayStudent", total);

        HashMap<String, List<PaymentStudent>> results = paymentStudentDAO.getPaymentStudentSorted();

        Map<String, Float> totalsByDate = paymentStudentDAO.totalsByDate;

        model.addAttribute("totalsByDate", totalsByDate);

        model.addAttribute("studentPaymentListSorted", results);


        model.addAttribute("error", error);

        return "LanguagesSchoolPages/Payment/StudentsPaymentDataTable";
    }

    @RequestMapping("/teachersSalaries")
    public String teachersSalaries(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                List<PaymentTeacher> teacherSalariesList = paymentTeacherDAO.getAllPaymentTeachers();

                model.addAttribute("teacherSalariesList", teacherSalariesList);

                float total = 0;


                for (PaymentTeacher paymentTeacher : teacherSalariesList) {
                    total += paymentTeacher.getAmount();
                }

                model.addAttribute("totalPayTeacher", total);

                SortedMap<String, List<PaymentTeacher>> results = paymentTeacherDAO.getPaymentTeacherSorted();

                int size = results.size() / 2;

                String firstKey = results.firstKey();


                String middleKey = results.keySet().toArray()[size].toString();


                model.addAttribute("teacherPaymentListSorted", results);
                model.addAttribute("teacherPaymentListSorted1", results.subMap(firstKey, middleKey));
                model.addAttribute("teacherPaymentListSorted2", results.tailMap(middleKey));


                Map<String, Float> totalsByDate = paymentTeacherDAO.totalsByDate;

                model.addAttribute("totalsByDate", totalsByDate);

                model.addAttribute("error", error);
                return "LanguagesSchoolPages/Payment/TeachersSalariesDataTable";

            } else {

                return "redirect:/error.j";
            }

        } else {
            //todo no one is connected
            return "redirect:/error.j";
        }

    }


    @RequestMapping("/staffSalaries")
    public String staffSalaries(Model model, @SessionAttribute ("sessionUser") Profile profile) {
        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {
                List<PaymentStaff> staffSalariesList = paymentStaffDAO.getAllPaymentStaffs();

                model.addAttribute("staffSalariesList", staffSalariesList);

                float total = 0;


                for (PaymentStaff paymentStaff : staffSalariesList) {
                    total += paymentStaff.getAmmount();
                }

                model.addAttribute("totalPayStaff", total);

                SortedMap<String, List<PaymentStaff>> results = paymentStaffDAO.getPaymentStaffSorted();

                int size = results.size() / 2;

                String firstKey = results.firstKey();

                String middleKey = results.keySet().toArray()[size].toString();

                model.addAttribute("staffPaymentListSorted", results);
                model.addAttribute("staffPaymentListSorted1", results.subMap(firstKey, middleKey));
                model.addAttribute("staffPaymentListSorted2", results.tailMap(middleKey));

                Map<String, Float> totalsByDate = paymentStaffDAO.totalsByDate;

                model.addAttribute("totalsByDate", totalsByDate);

                model.addAttribute("error", error);
                return "LanguagesSchoolPages/Payment/StaffSalariesDataTable";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }


    }

    @RequestMapping("/addStudentPayment")
    public String addStudentPayment(Model model) {

        String error = "";

        List<Student> studentList = studentDAO.getAllStudents();

        model.addAttribute("studentsList", studentList);

        List<HashSet<Module>> modulesList = new ArrayList<>();

        for (Student student : studentList) {

            HashSet<Module> module = new HashSet<>(student.getModulesSet());

            modulesList.add(module);
        }

        model.addAttribute("modulesList", modulesList);

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/AddStudentPayment";
    }

    @RequestMapping("/addTeacherPayment")
    public String addTeacherPayment(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        if (profile != null) {


            if (profile.getType().equalsIgnoreCase("Admin")) {

                List<Teacher> teachersList = teacherDAO.getAllTeachers();
                List<HashSet<GroupOfStudents>> groupsList = new ArrayList<>();
                Map<Integer, Float> group_salary = new HashMap<>();
                Map<Integer, Float> group_salary_absent = new HashMap<>();


                for (Teacher teacher : teachersList) {
                    HashSet<GroupOfStudents> groupOfStudents = new HashSet<>(teacher.getGroupsSet());
                    groupsList.add(groupOfStudents);


                    for (GroupOfStudents group : teacher.getGroupsSet()) {

                        float salary=0f;
                        float salary_absent=0f;

                        GroupOfStudents groupp = groupOfStudentsDAO.getGroupById(group.getId());

                        if (groupp.getPaymentType().equalsIgnoreCase("Student")) {

                            // le nombre d'étudiants * le prix par etudiant (présent)

                            System.out.println("\n\n\n groupe name: "+groupp.getName());
                            //todo
                            /** salary of all student with absent ones **/
                            salary_absent = groupp.getStudentsSet().size() * groupp.getFees();

                            /** salary of only present students **/
                            for (SessionOfGroup session : groupp.getSessionSet()) {
                                //number of student present
                                SessionOfGroup sessionn = sessionDAO.getSessionByID(session.getId());
                                salary += sessionn.getStudentsSet().size() * group.getFees();
                                System.out.println("# "+salary);

                            }

                            group_salary_absent.put(groupp.getId(), salary_absent-salary);
                            group_salary.put(groupp.getId(), salary);

                        } else if (groupp.getPaymentType().equalsIgnoreCase("Hour")) {
                            // salary= number of hours (in one session) * number of sessions * fees

                            Date d1 = groupp.getStartTime();
                            Date d2 = groupp.getEndTime();

                            float timeDiff = ((float) d2.getTime() - (float) d1.getTime()) / 3600000;

                            System.out.println(d2.getTime() - d1.getTime());
                            System.out.println("time : " + timeDiff);

                            salary = (timeDiff) * groupp.getNumberSessions() * groupp.getFees();
                            group_salary.put(groupp.getId(), salary);
                        }

                    }

                }


                model.addAttribute("teachersList", teachersList);
                model.addAttribute("groupsList", groupsList);
                model.addAttribute("groupSalariesMap", group_salary);
                model.addAttribute("groupSalariesAbsentMap", group_salary_absent);
                System.out.println("size of absent : "+group_salary_absent.size());


                model.addAttribute("error", error);
                return "LanguagesSchoolPages/Payment/AddTeacherPayment";

           } else {
                System.out.println("im here");

                return "redirect:/error.j";
            }

        } else {

            System.out.println("je suis la");
            return "redirect:/error.j";
        }
    }

    @RequestMapping("/addStaffPayment")
    public String addStaffPayment(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";


        if (profile != null) {

            System.out.println("*******"+profile.getType());

            if (profile.getType().equalsIgnoreCase("Admin")) {

                model.addAttribute("staffList", staffDAO.getAllStaffs());


                model.addAttribute("error", error);
                return "LanguagesSchoolPages/Payment/AddStaffPayment";

            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }


    @RequestMapping("/addNewStaffPayment")
    public String addNewStaffPayment(Model model, @RequestParam String id_staff,@SessionAttribute ("sessionUser") Profile profile) {

        String error = "";
        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {


                Staff staff = staffDAO.getStaffByID(Integer.parseInt(id_staff));

                //todo the one connected

                PaymentStaff paymentStaff = new PaymentStaff(new Date(), staff.getSalary(), "the one connected", staff);

                paymentStaffDAO.addPaymentStaff(paymentStaff);

                model.addAttribute("error", error);


                return "redirect:staffSalaries.j";
            } else {

                return "redirect:/error.j";
            }

        } else {
            System.out.println("je suis la");
            return "redirect:/error.j";
        }


    }


    @RequestMapping("/addNewTeacherPayment")
    public String addNewTeacherPayment(Model model, @RequestParam String id_teacher, @RequestParam String
            id_group, @RequestParam String value, @SessionAttribute  ("sessionUser") Profile profile) {

        String error = "";

        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                Teacher teacher = teacherDAO.getTeacherByID(Integer.parseInt(id_teacher));
                GroupOfStudents group = groupOfStudentsDAO.getGroupById(Integer.parseInt(id_group));

                //TODO change the one connected

                PaymentTeacher paymentTeacher = new PaymentTeacher(new Date(), Float.parseFloat(value), "the one connected",
                        group.getModule().getName(), group.getPaymentType(), teacher);

                paymentTeacherDAO.addPaymentTeacher(paymentTeacher);

                model.addAttribute("error", error);

                return "redirect:teachersSalaries.j";
            } else {
                System.out.println("je suis la 2");
                return "redirect:/error.j";
            }

        } else {
            System.out.println("je suis la");
            return "redirect:/error.j";
        }


    }


    @RequestMapping("/addNewStudentPayment")
    public String addNewStudentPayment(Model model, @RequestParam String id_student, @RequestParam String
            payed, @RequestParam String mod, @RequestParam String date_payment) {

        String error = "";

        Student student = studentDAO.getStudentByID(Integer.parseInt(id_student));

        String[] modules_fees = mod.split(",");
        StringBuilder modules = new StringBuilder();

        for (String module_fee : modules_fees) {
            modules.append(module_fee.split(" ", 2)[0]).append(", ");
        }


        PaymentStudent paymentStudent = new PaymentStudent(date_payment, Long.parseLong(payed), modules.toString(), "the one connected", student);

        paymentStudentDAO.addPaymentStudent(paymentStudent);

        model.addAttribute("error", error);

        return "redirect:studentPayment.j";

    }


    @RequestMapping("/deleteStudentFromGroup")
    public String deleteStudentFromGroup(Model model, @RequestParam String query, @RequestParam String
            id_student) {

        String error = "";

        Student student = studentDAO.getStudentByID(Integer.parseInt(id_student));

        PaymentStudent paymentStudent = paymentStudentDAO.getPayementStudentByID(Integer.parseInt(query));

        student.removePayment(paymentStudent.getId());


        studentDAO.updateStudent(student.getId(), student);

        paymentStudentDAO.deletePaymentStudent(paymentStudent.getId());

        model.addAttribute("error", error);

        return "redirect:studentPayment";

    }


}
