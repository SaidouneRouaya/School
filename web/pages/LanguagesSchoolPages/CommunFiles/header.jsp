<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 21/04/2019
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Languages School</title>
    <!-- Tell the browser to be responsive to screen width -->

</head>
<body>
<header class="main-header" data-skin="skin-red">
    <!-- Logo -->
    <a
            class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>S</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>School</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- Messages: style can be found in dropdown.less-->

                <!-- Notifications: style can be found in dropdown.less -->
                <li class="dropdown notifications-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning"><c:out value="${groupOfStudentsList.size()}"/></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have <c:out value="${groupOfStudentsList.size()}"/> notifications</li>

                        <c:if test="${groupOfStudentsList.size() gt 0}">
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">

                                    <tg:forEach begin="0" end="${groupOfStudentsList.size() -1}" var="i">

                                        <li>
                                            <a href="GroupDetails.j?id_group=${groupOfStudentsList[i].id}">
                                                <i class="fa fa-users text-aqua"></i> <c:out
                                                    value="${groupOfStudentsList[i].name}"/>
                                            </a>
                                        </li>

                                    </tg:forEach>
                                </ul>
                            </li>
                        </c:if>
                        <li class="footer"><a href="#">View all</a></li>
                    </ul>
                </li>
                <!-- Tasks: style can be found in dropdown.less -->

                <!-- Control Sidebar Toggle Button -->
                <li>
                    <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                </li>
            </ul>
        </div>
    </nav>
</header>
</body>

</html>