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
                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>Alexander Pierce</p>
            </div>
        </div>
        <!-- search form
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form> -->
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MENU</li>
            <li class="treeview">
                <a href="index.j">
                    <i class="fa fa-home"></i> <span>Home</span>

                </a>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="glyphicon glyphicon-education"></i>
                    <span>Groups</span>
                    <span class="pull-right-container">
                        <span class="fa fa-angle-left pull-right pull-right"></span>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="Groups.j"><i class="fa fa-circle-o"></i>Groups list</a></li>
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
                    <li><a href="Modules.j"><i class="fa fa-circle-o"></i>Modules list</a></li>
                    <li><a href="addModule.j"><i class="fa fa-circle-o"></i>Add new module</a></li>
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
                    <li><a href="Students.j"><i class="fa fa-circle-o"></i>Students list</a></li>
                    <li><a href="addStudent.j"><i class="fa fa-circle-o"></i>Add new student</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-paint-brush"></i>
                    <span>Layout Options</span>
                    <span class="pull-right-container">
              <span class="fa fa-angle-left pull-right"></span>
            </span>

                </a>
                <ul class="treeview-menu">
                    <li><a href="pages/layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li>
                    <li><a href="pages/layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li>
                    <li><a href="pages/layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li>
                    <li><a href="pages/layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
                </ul>
            </li>


        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

</body>

</html>