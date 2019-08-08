package Controllers;

import DAO.*;
import Entities.*;
import Entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Timestamp;
import java.util.*;

import static java.lang.Thread.getAllStackTraces;
import static java.lang.Thread.sleep;

@org.springframework.stereotype.Controller

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


    @RequestMapping("/studentPayment")
    public String studentsPayment(Model model) {

        String error = "";

        List<PaymentStudent> studentPaymentList= paymentStudentDAO.getAllPaymentStudents();

        model.addAttribute("studentPaymentList",studentPaymentList );

        Long total=0L;

        for (PaymentStudent paymentStudent:studentPaymentList){
            total+=paymentStudent.getAmmount();
        }
        model.addAttribute("totalPayStudent", total);

        HashMap<String, List<PaymentStudent>> results  = paymentStudentDAO.getPaymentStudentSorted();


        Map<String,Long>  totalsByDate = paymentStudentDAO.totalsByDate;



        model.addAttribute("totalsByDate", totalsByDate);

        model.addAttribute("studentPaymentListSorted", results);
        model.addAttribute("error", error);

        return "LanguagesSchoolPages/Payment/StudentsPaymentDataTable";
    }

    @RequestMapping("/teachersSalaries")
    public String teachersSalaries(Model model) {

        String error = "";

        List<PaymentTeacher> teacherSalariesList= paymentTeacherDAO.getAllPaymentTeachers();

        model.addAttribute("teacherSalariesList",teacherSalariesList );

        Long total=0L;


        for (PaymentTeacher paymentTeacher:teacherSalariesList){
            total+=paymentTeacher.getAmount();
        }

        model.addAttribute("totalPayTeacher", total);

        SortedMap<String, List<PaymentTeacher>> results  = paymentTeacherDAO.getPaymentTeacherSorted();

        int size= results.size()/2;

        String firstKey= results.firstKey();


        String middleKey=results.keySet().toArray()[size].toString();



        model.addAttribute("teacherPaymentListSorted", results);
        model.addAttribute("teacherPaymentListSorted1", results.subMap(firstKey,middleKey));
        model.addAttribute("teacherPaymentListSorted2", results.tailMap(middleKey));


        Map<String,Long>  totalsByDate = paymentTeacherDAO.totalsByDate;

        model.addAttribute("totalsByDate", totalsByDate);




        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/TeachersSalariesDataTable";
    }


    @RequestMapping("/staffSalaries")
    public String staffSalaries(Model model) {

        String error = "";

        List<PaymentStaff> staffSalariesList= paymentStaffDAO.getAllPaymentStaffs();

        model.addAttribute("staffSalariesList",staffSalariesList );

        Long total=0L;


        for (PaymentStaff paymentStaff:staffSalariesList){
            total+=paymentStaff.getAmmount();
        }

        model.addAttribute("totalPayStaff", total);

        SortedMap<String, List<PaymentStaff>> results  = paymentStaffDAO.getPaymentStaffSorted();

        int size= results.size()/2;


        String firstKey= results.firstKey();

        String middleKey=results.keySet().toArray()[size].toString();


        model.addAttribute("staffPaymentListSorted", results);
        model.addAttribute("staffPaymentListSorted1", results.subMap(firstKey,middleKey));
        model.addAttribute("staffPaymentListSorted2", results.tailMap(middleKey));

        Map<String,Long>  totalsByDate = paymentStaffDAO.totalsByDate;

        model.addAttribute("totalsByDate", totalsByDate);

        model.addAttribute("error", error);
        return "LanguagesSchoolPages/Payment/StaffSalariesDataTable";
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
    public String addTeacherPayment(Model model) {

        String error = "";

        List<Teacher> teachersList = teacherDAO.getAllTeachers();
        List<HashSet<GroupOfStudents>> groupsList = new ArrayList<>();
        Map<Integer, Long> group_salary=new HashMap<>();


        for (Teacher teacher : teachersList) {
            HashSet<GroupOfStudents> groupOfStudents = new HashSet<>(teacher.getGroupsSet());
            groupsList.add(groupOfStudents);


            for (GroupOfStudents group: teacher.getGroupsSet()){

                long salary= 0L;

                GroupOfStudents  groupp= groupOfStudentsDAO.getGroupById(group.getId());

                if (groupp.getPaymentType().equalsIgnoreCase("Student")){

                    salary= groupp.getStudentsSet().size() * groupp.getModule().getFees();

                    group_salary.put(groupp.getId(), salary);

                }
                else if (groupp.getPaymentType().equalsIgnoreCase("Hour")){
                    // salary= number of hours * number of sessions * fees

                    Date d1 = groupp.getStartTime();
                    Date d2 =  groupp.getEndTime();

                    long timeDiff = (d2.getTime() - d1.getTime())/3600000;

                    salary= (timeDiff)* groupp.getNumberSessions() * groupp.getModule().getFees();
                    group_salary.put(groupp.getId(), salary);
                }

            }

        }



        model.addAttribute("teachersList", teachersList);
        model.addAttribute("groupsList", groupsList);
        model.addAttribute("groupSalariesMap", group_salary);



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


        Staff staff = staffDAO.getStaffByID(Integer.parseInt(id_staff));


        PaymentStaff paymentStaff = new PaymentStaff(new Date(), staff.getSalary(), "the one connected", staff);

        paymentStaffDAO.addPaymentStaff(paymentStaff);

        model.addAttribute("error", error);


        return "redirect:staffSalaries.j";

    }


    @RequestMapping("/addNewTeacherPayment")
    public String addNewTeacherPayment(Model model, @RequestParam String id_teacher,@RequestParam String id_group, @RequestParam Map<String, String> param) {

        String error = "";


        Teacher teacher = teacherDAO.getTeacherByID(Integer.parseInt(id_teacher));
        GroupOfStudents group = groupOfStudentsDAO.getGroupById(Integer.parseInt(param.get(id_group)));

        System.out.println(id_teacher);


        //TODO change the one connected

        PaymentTeacher paymentTeacher = new PaymentTeacher(new Date(), Long.parseLong(param.get("salaries")), "the one connected",
                group.getModule().getName(), group.getPaymentType(), teacher);

        paymentTeacherDAO.addPaymentTeacher(paymentTeacher);

        model.addAttribute("error", error);

        return "redirect:teachersSalaries.j";

    }


    @RequestMapping("/addNewStudentPayment")
    public String addNewStudentPayment(Model model, @RequestParam String id_student, @RequestParam String payed, @RequestParam String mod, @RequestParam String date_payment) {

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


}
