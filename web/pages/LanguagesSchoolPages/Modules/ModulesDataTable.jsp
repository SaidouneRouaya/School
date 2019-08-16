<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 11/07/2019
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="../../../bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../../dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../../../dist/css/skins/_all-skins.min.css">

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
<%@ include file="../CommunFiles/header.jsp"%>

    <c:if test="${profile.type eq 'Admin'}">
        <%@ include file="../CommunFiles/menu-side.jsp"%>

    </c:if>

    <c:if test="${profile.type eq 'Receptionist'}">
        <%@ include file="../CommunFiles/menu-side-receptionist.jsp"%>
    </c:if>



<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
          Modules List
            <small>all modules</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Modules</a></li>
            <li class="active">Modules list</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">

        <c:if test="${profile.type eq 'Admin'}">
            <div class="row">
                <div class="col-xs-12">

                    <div class="box box-primary">
                        <a type="button"  href="addModule.j" class="btn btn-block btn-primary btn-lg">Add new module</a>
                    </div>
                </div>
            </div>
        </c:if>



        <div class="row">
            <div class="col-xs-12">

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
                                <th>Module's name</th>
                                <th>Teacher</th>

                                <th>Fees</th>
                                <c:if test="${profile.type eq 'Admin'}">
                                    <th>Update</th>
                                </c:if>


                            </tr>
                            </thead>
                            <tbody>

                            <tg:forEach items="${modulesList}" var="item">
                                <tr>

                                    <!-- nom du type de la stat  -->
                                    <td><c:out value="${item.name}"/></td>

                                    <td><tg:forEach items="${teachersList}" var="teachers">
                                        <tg:forEach items="${teachers}" var="teacher">
                                            <c:out value="${teacher.name}"/>, <c:out value="${teacher.familyname}"/>
                                        </tg:forEach>
                                    </tg:forEach></td>
                                    <td><c:out value="${item.fees}"/></td>

                                    <c:if test="${profile.type eq 'Admin'}">
                                        <td><a href="updateModule.j?query=${item.id}" class="btn  bg-teal-gradient">Update</a></td>


                                    </c:if>

                                </tr>

                            </tg:forEach>



                            </tbody>
                            <tfoot>
                            <tr>
                                <th>Module's name</th>
                                <th>Teacher</th>
                                <th>Fees</th>
                                <c:if test="${profile.type eq 'Admin'}">
                                    <th>Update</th>
                                </c:if>
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
    <%@ include file="../CommunFiles/footer.jsp"%>

    <!-- Control Sidebar -->
    <%@ include file="../CommunFiles/controlMenu.jsp"%>
</div>



<!-- jQuery 3 -->
<script src="../../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="../../../bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../../../bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="../../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../../dist/js/demo.js"></script>
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
