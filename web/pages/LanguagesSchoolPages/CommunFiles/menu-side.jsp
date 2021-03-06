<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 22/04/2019
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>menu-side</title>

</head>
<body>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><c:out value="${profile.name }"/> <c:out value="${profile.familyname }"/></p>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MENU</li>

            <li><a href="Home.j"><i class="fa fa-circle-o text-blue"></i> <span>Home</span></a></li>

            <li class="treeview">
                <a href="#">
                    <i class="glyphicon glyphicon-education"></i>
                    <span>Groups</span>
                    <span class="pull-right-container">
                        <span class="fa fa-angle-left pull-right pull-right"></span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="addGroup.j"><i class="fa fa-circle-o"></i>Add new group</a></li>
                    <li><a href="Groups.j?home=1"><i class="fa fa-circle-o"></i>Groups list</a></li>
                    <li><a href="GroupsByModule.j"><i class="fa fa-circle-o"></i>Groups by module</a></li>
                </ul>
            </li>


            <li class="treeview">
                <a href="#">
                    <i class="glyphicon glyphicon-text-color"></i>
                    <span>Modules</span>
                    <span class="pull-right-container">
                        <span class="fa fa-angle-left pull-right pull-right"></span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="addModule.j"><i class="fa fa-circle-o"></i>Add new module</a></li>
                    <li><a href="Modules.j"><i class="fa fa-circle-o"></i>Modules list</a></li>

                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="glyphicon glyphicon-education"></i>
                    <span>Students</span>
                    <span class="pull-right-container">
                        <span class="fa fa-angle-left pull-right pull-right"></span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="addStudent.j"><i class="fa fa-circle-o"></i>Add new student</a></li>
                    <li><a href="Students.j?home=1"><i class="fa fa-circle-o"></i>Students list</a></li>

                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-user-secret"></i>
                    <span>Teachers</span>
                    <span class="pull-right-container">
                        <span class="fa fa-angle-left pull-right pull-right"></span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="addTeacher.j"><i class="fa fa-circle-o"></i>Add new teacher</a></li>
                    <li><a href="Teachers.j"><i class="fa fa-circle-o"></i>Teachers list</a></li>

                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-users"></i>
                    <span>Staff</span>
                    <span class="pull-right-container">
                        <span class="fa fa-angle-left pull-right pull-right"></span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="addStaff.j"><i class="fa fa-circle-o"></i>Add new member</a></li>
                    <li><a href="Staff.j"><i class="fa fa-circle-o"></i>Staff list</a></li>

                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="glyphicon glyphicon-education"></i>
                    <span>Profiles</span>
                    <span class="pull-right-container">
                        <span class="fa fa-angle-left pull-right pull-right"></span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="addProfile.j"><i class="fa fa-circle-o"></i>Add new profile</a></li>
                    <li><a href="Profiles.j"><i class="fa fa-circle-o"></i>Profiles list</a></li>

                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-money"></i>
                    <span>Payments</span>
                    <span class="pull-right-container">
              <span class="fa fa-angle-left pull-right pull-right"></span>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="addStudentPayment.j"><i class="fa fa-circle-o"></i>New student's payment</a></li>
                    <li><a href="addTeacherPayment.j"><i class="fa fa-circle-o"></i>New teacher's payment</a></li>
                    <li><a href="addStaffPayment.j"><i class="fa fa-circle-o"></i>New staff's payment</a></li>

                     <li><a href="studentPayment.j"><i class="fa fa-circle-o"></i>List of students' payments</a></li>
                    <li><a href="teachersSalaries.j"><i class="fa fa-circle-o"></i>List of teachers' salaries</a></li>
                    <li><a href="staffSalaries.j"><i class="fa fa-circle-o"></i>List of staff's salaries</a></li>

                </ul>
            </li>

            <li><a href="deconnexion.j"><i class="fa fa-circle-o text-red"></i> <span>Log out</span></a></li>
        </ul>


    </section>
    <!-- /.sidebar -->
</aside>

</body>

</html>
