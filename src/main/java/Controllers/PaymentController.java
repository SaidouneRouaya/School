package Controllers;

import DAO.*;
import Entities.*;
import Entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;


import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Thread.sleep;

@org.springframework.stereotype.Controller
@Scope("session")

public class PaymentController {

    @Autowired
    StaffDAO staffDAO;

    @Autowired
    StudentSessionDAO studentSessionDAO;

    @Autowired
    SessionDAO sessionDAO;

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
    SeanceDAO seanceDAO;


    @RequestMapping("/studentPayment")
    public String studentsPayment(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        List<PaymentStudent> studentPaymentList = paymentStudentDAO.getAllPaymentStudents();


        if (studentPaymentList==null ||studentPaymentList.isEmpty()) return ("redirect:/empty.j");


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


        model.addAttribute("profile", profile);
        model.addAttribute("error", error);

        return "LanguagesSchoolPages/Payment/StudentsPaymentDataTable";
    }

    @RequestMapping("/teachersSalaries")
    public String teachersSalaries(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                List<PaymentTeacher> teacherSalariesList = paymentTeacherDAO.getAllPaymentTeachers();

                if (teacherSalariesList==null ||teacherSalariesList.isEmpty()) return ("redirect:/empty.j");

                model.addAttribute("teacherSalariesList", teacherSalariesList);

                float total = 0;


                for (PaymentTeacher paymentTeacher : teacherSalariesList) {
                    total += paymentTeacher.getTotal();
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

                if (staffSalariesList==null ||staffSalariesList.isEmpty()) return ("redirect:/empty.j");
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

             model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "LanguagesSchoolPages/Payment/StaffSalariesDataTable";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }


    }

    @RequestMapping("/addStudentPayment")
    public String addStudentPayment(Model model, @SessionAttribute("sessionUser") Profile profile) {

        String error = "";

        List<Student> studentList = studentDAO.getAllStudents();
        if (studentList==null || studentList.isEmpty()) return ("redirect:/empty.j");



        List<Map<Integer, Float>> feesList = new ArrayList<>();
        List<Map<Integer, Integer>> groupsList = new ArrayList<>();
        List<HashSet<Module>> modulesList = new ArrayList<>();

        for (Student student1 : studentList) {

            Student student = studentDAO.getStudentByID(student1.getId());

            Map<Integer, Float> fees = new HashMap<>();
            Map<Integer, Integer> groups = new HashMap<>();
            HashSet<Module> modules = new HashSet<>(student.getModulesSet());


            for (Module module : modules) {
                float fee = 0;
                GroupOfStudents groupp = student.getGroupOfModule(module);


                if (groupp != null) {

                    GroupOfStudents group = groupOfStudentsDAO.getGroupById(groupp.getId());
                    SessionOfGroup session = group.getLatestSession();
                    int numberSeances = session.getNumberOfSeances();

                    // represents number of seances that this student won't have
                    int numberSeancesOfStudent = 0;

                    for (Seance seance : session.getSeancesSet()) {

                        if (seance.getDate() != null && session.getStartDate().before(seance.getDate())) {
                            numberSeancesOfStudent++;
                        }
                    }

                    // fees * number of seances that this student will have starting from his start date in this session

                    fee = (module.getFees() / (float) numberSeances) * (numberSeances - numberSeancesOfStudent);

                    groups.put(module.getId(), group.getId());


                } else {

                    fee = module.getFees();
                }
                fees.put(module.getId(), fee);
            }

            modulesList.add(modules);
            groupsList.add(groups);
            feesList.add(fees);
        }

        model.addAttribute("studentsList", studentList);
         model.addAttribute("groupsList", groupsList);
        model.addAttribute("modulesList", modulesList);
        model.addAttribute("feesList", feesList);

        model.addAttribute("profile", profile);
        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/AddStudentPayment";
    }


    @RequestMapping("/addNewStudentPayment")
    public String addNewStudentPayment(Model model, @RequestParam String id_student, @RequestParam String
            payed,@RequestParam String T, @RequestParam String mod,  @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        Student student = studentDAO.getStudentByID(Integer.parseInt(id_student));

        String[] modules_fees = mod.split(",");
        StringBuilder modules = new StringBuilder();
        Set<GroupOfStudents> groups= new HashSet<>();


        for (String module_fee : modules_fees) {
            modules.append(module_fee.split("#", 3)[0]).append(", ");
            groups.add(groupOfStudentsDAO.getGroupById(Integer.parseInt(module_fee.split("#", 3)[2])));
        }

        Date now= new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        float payedd=Float.parseFloat(payed);
        float total=Float.parseFloat(T);


        try {

            PaymentStudent paymentStudent = new PaymentStudent(dateFormat.parse(dateFormat.format(now)), payedd, total - payedd
                    , total, modules.toString(), profile.getFamilyname() + " " + profile.getName(), student, groups);
            paymentStudentDAO.addPaymentStudent(paymentStudent);


            for (GroupOfStudents group: groups){
                group.getPaymentStudentSet().add(paymentStudent);
                groupOfStudentsDAO.updateGroup(group.getId(), group);
            }


        }catch(Exception e){
            e.printStackTrace();

        }



        model.addAttribute("profile", profile);
        model.addAttribute("error", error);

        return "redirect:studentPayment.j";

    }

    @RequestMapping("/addTeacherPayment")
    public String addTeacherPayment(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        if (profile != null) {


            if (profile.getType().equalsIgnoreCase("Admin")) {

                List<Teacher> teachersList = teacherDAO.getAllTeachers();
                if (teachersList==null || teachersList.isEmpty()) return ("redirect:/empty.j");


                List<HashSet<GroupOfStudents>> groupsList = new ArrayList<>();
                Map<Integer, List<SessionOfGroup>> group_sessions = new HashMap<>();
                Map<Integer, Float> session_salary = new HashMap<>();
                Map<Integer, Float> session_salary_absent = new HashMap<>();


                for (Teacher teacher : teachersList) {
                    HashSet<GroupOfStudents> groupOfStudents = new HashSet<>(teacher.getGroupsSet());
                    groupsList.add(groupOfStudents);


                    for (GroupOfStudents groupp : teacher.getGroupsSet()) {

                        GroupOfStudents group = groupOfStudentsDAO.getGroupById(groupp.getId());

                        group_sessions.put(group.getId(), new ArrayList<>(group.getSessionOfGroupsSet()));

                        for (SessionOfGroup session : group.getSessionOfGroupsSet()) {


                            float salary = 0f;
                            float salary_absent = 0f;

                            if (group.getPaymentType().equalsIgnoreCase("Student")) {


                                // le nombre d'étudiants * le prix par etudiant (présent)


                                /** salary of all student with absent ones **/


                                /* number of student * number of seances * fees of this group */
                                salary_absent = session.getStudentSessionsSet().size() * session.getSeancesSet().size() * group.getFees();


                                /** salary of only present students **/
                                for (Seance seance : session.getSeancesSet()) {
                                    //number of student present
                                    Seance seancee = seanceDAO.getSeanceByID(seance.getId());

                                    salary += (float) seancee.getStudentsSet().size() * (group.getFees());

                                }

                                session_salary_absent.put(session.getId(), salary_absent - salary);
                                session_salary.put(session.getId(), salary);

                            } else if (group.getPaymentType().equalsIgnoreCase("Hour")) {
                                // salary= number of hours (in one session) * number of sessions * fees

                                Date d1 = group.getStartTime();
                                Date d2 = group.getEndTime();

                                float timeDiff = ((float) d2.getTime() - (float) d1.getTime()) / 3600000;

                                salary = (timeDiff) * group.getNumberSessions() * group.getFees();
                                session_salary.put(session.getId(), salary);
                            }
                        }

                    }

                }


                model.addAttribute("teachersList", teachersList);
                model.addAttribute("groupsList", groupsList);
                model.addAttribute("group_sessionsMap", group_sessions);
                model.addAttribute("sessionSalariesMap", session_salary);
                model.addAttribute("sessionSalariesAbsentMap", session_salary_absent);


             model.addAttribute("profile", profile); model.addAttribute("error", error);
                return "LanguagesSchoolPages/Payment/AddTeacherPayment";

           } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }
    }

    @RequestMapping("/addStaffPayment")
    public String addStaffPayment(Model model, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        if (profile != null) {


            if (profile.getType().equalsIgnoreCase("Admin")) {


                List<Staff> staff= staffDAO.getAllStaffs();
                if (staff==null || staff.isEmpty()) return ("redirect:/empty.j");

                model.addAttribute("staffList",staff );
                model.addAttribute("profile", profile);
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



                PaymentStaff paymentStaff = new PaymentStaff(new Date(), staff.getSalary(),
                        profile.getFamilyname()+" "+profile.getName(), staff);

                paymentStaffDAO.addPaymentStaff(paymentStaff);

             model.addAttribute("profile", profile); model.addAttribute("error", error);


                return "redirect:staffSalaries.j";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }


    }


    @RequestMapping("/addNewTeacherPayment")
    public String addNewTeacherPayment(Model model, @RequestParam String id_teacher, @RequestParam String group, @RequestParam String totalToPay, @RequestParam String T, @SessionAttribute  ("sessionUser") Profile profile) {

        String error = "";

        if (profile != null) {
            if (profile.getType().equalsIgnoreCase("Admin")) {

                Teacher teacher = teacherDAO.getTeacherByID(Integer.parseInt(id_teacher));

                String[] groups = group.split(",");
                float salary= Float.parseFloat(T);

                StringBuilder groupsList = new StringBuilder();
                Set<SessionOfGroup> sessions= new HashSet<>();

                for (String groupp : groups) {

                    String id= groupp.split("#", 5)[3];
                    String id_session= groupp.split("#", 5)[4];

                    if(id.isEmpty()) id= groupp.split("#", 3)[2];

                    groupsList.append( groupOfStudentsDAO.getGroupById(Integer.parseInt(id)).getName()).append(", ");

                    sessions.add(sessionDAO.getSessionByID(Integer.parseInt(id_session)));
                }


                PaymentTeacher paymentTeacher = new PaymentTeacher(new Date(), Float.parseFloat(totalToPay),salary,
                        profile.getFamilyname()+" "+profile.getName(),
                        groupsList.toString(), "test", teacher, sessions);

                paymentTeacherDAO.addPaymentTeacher(paymentTeacher);

                for (SessionOfGroup sessionOfGroup: sessions){

                   sessionOfGroup.getPaymentSet().add(paymentTeacher);
                   sessionDAO.updateSession(sessionOfGroup.getId(), sessionOfGroup);
                }

                model.addAttribute("profile", profile); model.addAttribute("error", error);

                return "redirect:teachersSalaries.j";
            } else {

                return "redirect:/error.j";
            }

        } else {

            return "redirect:/error.j";
        }


    }



    @RequestMapping("/studentVoucher")
    public String studentVoucher(Model model, @RequestParam String p , @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";
        int id_payment= Integer.parseInt(p);

        PaymentStudent paymentStudent=paymentStudentDAO.getPayementStudentByID(id_payment);
        Student student=studentDAO.getStudentByID(paymentStudent.getStudentPay().getId());

        List<Integer> modulesPayed= new ArrayList<>();

        for (GroupOfStudents group: paymentStudent.getGroupPay()){

            modulesPayed.add(group.getModule().getId());
        }


        model.addAttribute("student", student);
        model.addAttribute("modules", student.getModulesSet());

        model.addAttribute("modulesPayed", modulesPayed);


        model.addAttribute("payment", paymentStudent);
        model.addAttribute("date", paymentStudent.getDate());

     model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/StudentVoucher";
    }

    @RequestMapping("/teacherVoucher")
    public String teacherVoucher(Model model, @RequestParam String p, @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";
        int id_payment= Integer.parseInt(p);

      PaymentTeacher paymentTeacher = paymentTeacherDAO.getPayementTeacherByID(id_payment);
      Teacher teacher= teacherDAO.getTeacherByID(paymentTeacher.getTeacher().getId());


        List<Integer> sessionPayed= new ArrayList<>();


       for (SessionOfGroup sessionOfGroup: paymentTeacher.getSessionPay()){
           sessionPayed.add(sessionOfGroup.getId());

       }


        model.addAttribute("teacher", teacher);
        model.addAttribute("groups", teacher.getGroupsSet());

        model.addAttribute("sessionPayed", sessionPayed);

        model.addAttribute("payment", paymentTeacher);
        model.addAttribute("date", paymentTeacher.getDate());


     model.addAttribute("profile", profile); model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/TeacherVoucher";
    }

    @RequestMapping("/staffVoucher")
    public String staffVoucher(Model model, @RequestParam String p, @SessionAttribute ("sessionUser") Profile profile ) {

        String error = "";
        int id_payment= Integer.parseInt(p);

      PaymentStaff  paymentStaff =paymentStaffDAO.getPayementStaffByID(id_payment);
      Staff staff= staffDAO.getStaffByID(paymentStaff.getStaff().getId());

        model.addAttribute("staff", staff);
        model.addAttribute("date", paymentStaff.getDate());

        model.addAttribute("payment", paymentStaff);


     model.addAttribute("profile", profile);
     model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/StaffVoucher";
    }







}
