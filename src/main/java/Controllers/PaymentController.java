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
        studentSessionDAO.getAllStudentSessions();

        List <Map<Integer, String>> groupsList = new ArrayList<>();
        List < Map<Integer, Float>> feesList = new ArrayList<>();


        List<HashSet<Module>> modulesList = new ArrayList<>();

        for (Student student1 : studentList) {

            Student student= studentDAO.getStudentByID(student1.getId());


            Map<Integer, String> groups= new HashMap<>();
            Map<Integer, Float> fees= new HashMap<>();


            HashSet<Module> modules = new HashSet<>(student.getModulesSet());


            for (Module module:modules) {

                GroupOfStudents group = student.getGroupOfModule(module);
                groups.put(module.getId(), Integer.toString(group.getId()));

                 float fee = 0;
               /*  Iterator<StudentSession> it=student.getStudentSessionsSet().iterator();

                StudentSession session=  it.next();

                while (it.hasNext() && session.getSession().getId()!= group.getLatestSession().getId()){
                   session= it.next();
                }*/
                StudentSession session= student.getLatestSession();

                    int numberSeances = session.getSession().getNumberOfSeances();
                    int numberSeancesOfStudent = 0;

                    for (Seance seance : session.getSession().getSeancesSet()) {

                        if (seance.getDate().before(session.getStartDate())) {
                            numberSeancesOfStudent++;
                        }
                    }

                    // fees * number of seances that this student will have starting from his start date in this session
                    fee = group.getFees() * (numberSeances - numberSeancesOfStudent);

                System.out.println("########################################################");
                System.out.println(group.getId());
                System.out.println( " group fee = "+ group.getFees() );
                System.out.println( " number of seance = "+  (numberSeances - numberSeancesOfStudent) );

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
                Map<Integer, List<SessionOfGroup>> group_sessions = new HashMap<>();
                Map<Integer, Float> session_salary = new HashMap<>();
                Map<Integer, Float> session_salary_absent = new HashMap<>();


                for (Teacher teacher : teachersList) {
                    HashSet<GroupOfStudents> groupOfStudents = new HashSet<>(teacher.getGroupsSet());
                    groupsList.add(groupOfStudents);


                    for (GroupOfStudents groupp : teacher.getGroupsSet()) {

                        GroupOfStudents group = groupOfStudentsDAO.getGroupById(groupp.getId());

                        group_sessions.put(group.getId(), new ArrayList<>(group.getSessionOfGroupsSet()));

                     for (SessionOfGroup session: group.getSessionOfGroupsSet()){


                         float salary=0f;
                         float salary_absent=0f;

                         if (group.getPaymentType().equalsIgnoreCase("Student")) {

                             // le nombre d'étudiants * le prix par etudiant (présent)


                             /** salary of all student with absent ones **/
                             salary_absent = session.getStudentSessionsSet().size() * group.getFees();

                             /** salary of only present students **/
                             for (Seance seance : session.getSeancesSet()) {
                                 //number of student present
                                 Seance sessionn = seanceDAO.getSeanceByID(seance.getId());
                                 salary += sessionn.getStudentsSet().size() * group.getFees();

                             }

                             session_salary_absent.put(session.getId(), salary_absent-salary);
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

                PaymentStaff paymentStaff = new PaymentStaff(new Date(), staff.getSalary(),
                        profile.getFamilyname()+" "+profile.getName(), staff);

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

                    String id= groupp.split(" ", 4)[3];
                    if(id.isEmpty()) id= groupp.split(" ", 3)[2];

                    groupsList.append( groupOfStudentsDAO.getGroupById(Integer.parseInt(id)).getName()).append(", ");
                }


                PaymentTeacher paymentTeacher = new PaymentTeacher(new Date(), Float.parseFloat(totalToPay),salary,
                        profile.getFamilyname()+" "+profile.getName(),
                        groupsList.toString(), "test", teacher);

                paymentTeacherDAO.addPaymentTeacher(paymentTeacher);

                for (SessionOfGroup sessionOfGroup: sessions){

                   sessionOfGroup.getPaymentSet().add(paymentTeacher);
                   sessionDAO.updateSession(sessionOfGroup.getId(), sessionOfGroup);
                }

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
            payed,@RequestParam String T, @RequestParam String mod,  @SessionAttribute ("sessionUser") Profile profile) {

        String error = "";

        Student student = studentDAO.getStudentByID(Integer.parseInt(id_student));

        String[] modules_fees = mod.split(",");
        StringBuilder modules = new StringBuilder();
        Set<GroupOfStudents> groups= new HashSet<>();


        for (String module_fee : modules_fees) {
            modules.append(module_fee.split(" ", 3)[0]).append(", ");
           groups.add(groupOfStudentsDAO.getGroupById(Integer.parseInt(module_fee.split(" ", 3)[2])));


        }

        Date now= new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        float payedd=Float.parseFloat(payed);
        float total=Float.parseFloat(T);

        System.out.println("groups"+groups.toString());
        System.out.println("to pay"+total);

        PaymentStudent paymentStudent = new PaymentStudent(dateFormat.format(now), payedd, total-payedd
                , total,  modules.toString(), profile.getFamilyname()+" "+profile.getName(), student, groups);

        paymentStudentDAO.addPaymentStudent(paymentStudent);

        for (GroupOfStudents group: groups){
            group.getPaymentStudentSet().add(paymentStudent);
            groupOfStudentsDAO.updateGroup(group.getId(), group);
        }

        model.addAttribute("error", error);

        return "redirect:studentPayment.j";

    }


    @RequestMapping("/studentVoucher")
    public String studentVoucher(Model model, @RequestParam String p) {

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

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/StudentVoucher";
    }

    @RequestMapping("/teacherVoucher")
    public String teacherVoucher(Model model, @RequestParam String p) {

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

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/TeacherVoucher";
    }

    @RequestMapping("/staffVoucher")
    public String staffVoucher(Model model, @RequestParam String p) {

        String error = "";
        int id_payment= Integer.parseInt(p);

      PaymentStaff  paymentStaff =paymentStaffDAO.getPayementStaffByID(id_payment);
      Staff staff= staffDAO.getStaffByID(paymentStaff.getStaff().getId());

        model.addAttribute("staff", staff);
        model.addAttribute("date", paymentStaff.getDate());

        model.addAttribute("payment", paymentStaff);

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/StaffVoucher";
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


   /* @RequestMapping("/deletePaymentStudent")
    public String deletePaymentStudent(Model model, @RequestParam String query, @RequestParam String id_group){

        String error = "";

        Student student=studentDAO.getStudentByID(Integer.parseInt(query));

        GroupOfStudents groupOfStudents= groupOfStudentsDAO.getGroupById(Integer.parseInt(id_group));

        student.removeGroup(groupOfStudents.getId());
        groupOfStudents.removeStudent(student.getId());

        studentDAO.updateStudentGroups(Integer.parseInt(query), student.getGroupsSet());
        groupOfStudentsDAO.updateGroupStudentsList(Integer.parseInt(id_group), groupOfStudents.getStudentsSet());

        model.addAttribute("error", error);

        return "redirect:GroupDetails.j?id_group="+id_group;

    }
    */






}
