<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 17/07/2019
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/Ionicons/css/ionicons.min.css">
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
    <%@ include file="../CommunFiles/menu-side.jsp" %>


    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Staff Profile
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Staff</a></li>
                <li class="active">Profile</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-12">


                    <!-- Widget: user widget style 1 -->
                    <div class="box box-widget widget-user">
                        <!-- Add the bg color to the header using any of the bg-* classes -->
                        <div class="widget-user-header bg-aqua-active">
                            <div class="col-md-8">
                                <h3 class="widget-user-username">${staffProfile.name} ${staffProfile.familyname}</h3>
                                <h5 class="widget-user-desc">Staff</h5>
                            </div>

                            <div class="col-md-4">

                            <a href="updateStaff.j?query=${staffProfile.id}" class="btn btn-app btn-default pull-right" >
                                <i class=" fa fa-edit"></i>
                                Update profile
                            </a>
                            </div>
                        </div>
                        <div class="widget-user-image">
                            <img class="img-circle"  src="${pageContext.request.contextPath}/resources/dist/img/avatar.png"  alt="Staff Avatar">
                        </div>
                        <div class="box-footer">
                            <div class="row">
                                <div class="col-sm-6 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">Employment Date</h5>
                                        <span class="description-text">${staffProfile.employmentDate}</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                                <div class="col-sm-6 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">Salary</h5>
                                        <span class="description-text">${staffProfile.salary}</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /.row -->
                        </div>
                    </div>
                    <!-- /.widget-user -->
                </div>
            </div>
            <!-- /.col -->

            <div class="row">
                <div class="col-md-5">

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Staff information</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">

                            <form>
                                <table class="table table-striped">
                                    <tbody>

                                    <tr>
                                        <td>Full name</td>
                                        <td>${staffProfile.name} ${staffProfile.familyname}</td>
                                    </tr>
                                    <tr>
                                        <td>Phone number</td>
                                        <td>${staffProfile.phoneNumber}</td>
                                    </tr>

                                    <tr>
                                        <td>Employment day</td>
                                        <td>${staffProfile.employmentDate}</td>
                                    </tr>
                                    <tr>
                                        <td>Job</td>
                                        <td>${staffProfile.job}</td>
                                    </tr>

                                    </tbody>
                                </table>

                                <div>
                                    <a href="deleteStaff.j" class="btn  bg-red-gradient pull-right"
                                       data-toggle="modal" data-target="#modal-default" >Delete</a>
                                </div>


                            </form>
                        </div>
                        <!-- /.box-body -->

                    </div>

                </div>
                <div class="col-md-7">

                    <div class="box box-default">
                        <div class="box-header  with-border">
                            <h3 class="box-title">Salary history</h3>
                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                </button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">

                            <form>
                                <table class="table table-striped">
                                    <tbody>

                                    <tr>
                                        <th>#</th>

                                        <th>Date</th>
                                        <th>Amount</th>
                                    </tr>

                                    <c:set var="number" value="0"/>
                                    <c:if test="${payments.size() gt 0}">

                                        <tg:forEach begin="0" end="${payments.size()-1}" var="i">
                                            <c:set var="number" value="${number+1}"/>

                                            <tr>

                                                <td><c:out value="${number}"/></td>
                                                <td><c:out value="${payments[i].date}"/></td>
                                                <td><c:out value="${payments[i].ammount}"/>0 DZD</td>
                                                <td>
                                                    <a type="submit" class="btn btn-default pull-right"
                                                       href="staffVoucher.j?p=${payments[i].id}"
                                                       target="_blank"><i class="fa fa-print"></i> print
                                                    </a></td>

                                            </tr>

                                        </tg:forEach>

                                    </c:if>
                                    </tbody>
                                    <h4 class="pull-right">Total :${total}0 DZD</h4>

                                </table>

                            </form>
                        </div>
                        <!-- /.box-body -->

                    </div>

                </div>
            </div>
            <!-- /.row -->
            <!-- The Modal -->
            <form role="form" method="post" action="deleteStaff.j?query=${staffProfile.id}">
                <div class="modal fade" id="modal-default">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Warning </h4>
                            </div>
                            <div class="modal-body">
                                <p>Do you really want to delete this member ?</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel
                                </button>
                                <button type="submit" class="btn bg-danger  pull-right">Delete anyway</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
            </form>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- footer  -->
    <%@ include file="../CommunFiles/footer.jsp" %>

    <!-- Control Sidebar -->
    <%@ include file="../CommunFiles/controlMenu.jsp" %>

</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/resources/dist/js/demo.js"></script>
</body>
</html>
