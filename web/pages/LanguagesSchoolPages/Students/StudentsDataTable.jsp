<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 11/07/2019
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/Ionicons/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@ include file="../CommunFiles/header.jsp" %>

    <c:if test="${profile.type eq 'Admin'}">
        <%@ include file="../CommunFiles/menu-side.jsp" %>

    </c:if>

    <c:if test="${profile.type eq 'Receptionist'}">
        <%@ include file="../CommunFiles/menu-side-receptionist.jsp" %>
    </c:if>


    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Students List
                <small>all students</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Students</a></li>
                <li class="active">Students list</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="col-xs-6">
                        <div class="box box-primary">
                            <a type="button" href="addStudent.j" class="btn btn-block btn-primary btn-lg">Add new
                                student</a>
                        </div>
                    </div>

                    <div class="col-xs-6">
                        <div class="box box-danger">
                            <a href="unpaidStudents.j" class="btn btn-block btn-danger btn-lg"> Unpaid student</a>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-xs-12">

                    <!-- Add new student
                    <button type="button" class="btn-app .btn-lg"></button>-->


                    <!-- /.box -->

                    <div class="box">
                        <!--  <div class="box-header">
                              <h3 class="box-title">Data Table With Full Features</h3>
                          </div>
                          <!-- /.box-header -->
                        <div class="box-body">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Full name</th>
                                    <th>Phone number</th>
                                    <th>Type of student</th>
                                </tr>
                                </thead>
                                <tbody>


                                <tg:forEach begin="0" end="${studentsList.size() -1}" var="i">
                                    <tr
                                            <c:choose>
                                                <c:when test="${studentsList[i].type eq 'free'}">
                                                    class="bg-purple disabled"
                                                </c:when>

                                                <c:otherwise>
                                                    <tg:forEach begin="0" end="${unpaidStudent.size()-1}" var="m">

                                                        <c:if test="${unpaidStudent[m].id eq studentsList[i].id}">
                                                            class="bg-red disabled"
                                                        </c:if>

                                                    </tg:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                    >
                                        <td>
                                            <a class="btn  btn-box-tool  bg-red-active"
                                               href="deleteStudent.j?query=${studentsList[i].id}">
                                                <i class="fa fa-remove"></i>
                                            </a>
                                        </td>
                                        <!-- nom du type de la stat  -->
                                        <td><a class="text-black" href="Profile.j?query=${studentsList[i].id}">
                                            <b><c:out value="${studentsList[i].name}"/> <c:out
                                                    value="${studentsList[i].familyname}"/></b>
                                        </a></td>

                                        <td><c:out value="${studentsList[i].phoneNumber1}"/></td>
                                        <td><c:out value="${studentsList[i].type}"/></td>

                                    </tr>

                                </tg:forEach>

                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Full name</th>
                                    <th>Phone number</th>
                                    <th>Type of student</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>

    <!-- footer  -->
    <%@ include file="../CommunFiles/footer.jsp" %>

    <!-- Control Sidebar -->
    <%@ include file="../CommunFiles/controlMenu.jsp" %>
</div>


<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/resources/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/resources/dist/js/demo.js"></script>
<!-- page script -->
<script>
    $(function () {
        $('#example1').DataTable();
        $('#example2').DataTable({
            'paging': true,
            'lengthChange': false,
            'searching': false,
            'ordering': true,
            'info': true,
            'autoWidth': false
        })
    })
</script>
</body>
</html>
