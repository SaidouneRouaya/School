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
    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../../dist/css/skins/_all-skins.min.css">

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
                Teacher Profile
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Teacher</a></li>
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
                                <h3 class="widget-user-username">Alexander Pierce</h3>
                                <h5 class="widget-user-desc">Teacher</h5>
                            </div>

                            <div class="col-md-4">

                                <a href="updateTeacher.j" class="btn btn-app btn-default pull-right">
                                    <i class=" fa fa-edit"></i>
                                    Update profile
                                </a>
                            </div>
                        </div>
                        <div class="widget-user-image">
                            <img class="img-circle" src="../../../dist/img/user4-128x128.jpg" alt="User Avatar">
                        </div>
                        <div class="box-footer">
                            <div class="row">
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">3,200</h5>
                                        <span class="description-text">information</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">13,000</h5>
                                        <span class="description-text">information</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                                <div class="col-sm-4">
                                    <div class="description-block">
                                        <h5 class="description-header">35</h5>
                                        <span class="description-text">information</span>
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
                            <h3 class="box-title">Teacher's information</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">

                            <form>
                                <table class="table table-striped">
                                    <tbody>

                                    <tr>
                                        <td>Full name</td>
                                        <td>Alexander Pierce</td>
                                    </tr>
                                    <tr>
                                        <td>Phone number</td>
                                        <td>0987647878</td>
                                    </tr>

                                    <tr>
                                        <td>Employment day</td>
                                        <td>24/06/2017</td>
                                    </tr>
                                    <tr>
                                        <td>Job</td>
                                        <td>Administration</td>
                                    </tr>
                                    <tr>
                                        <td>Salary </td>
                                        <td>23000 DZD</td>
                                    </tr>

                                    <tr>
                                        <td>Voucher</td>
                                        <td>Passport</td>
                                    </tr>


                                    </tbody>
                                </table>

                                <div>
                                    <a href="#" class="btn  bg-red-gradient pull-right">Delete</a>
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
                                    <tr>
                                        <td>1</td>

                                        <td>12/04/2018</td>
                                        <td>23000.00 DZD</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>

                                        <td>12/05/2018</td>
                                        <td>23000.00 DZD</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>

                                        <td>12/06/2018</td>
                                        <td>23000.00 DZD</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>

                                        <td>12/07/2018</td>
                                        <td>23000.00 DZD</td>
                                    </tr>
                                    <tr>
                                        <td>5</td>

                                        <td>12/08/2018</td>
                                        <td>23000.00 DZD</td>
                                    </tr>

                                    </tbody>
                                </table>

                            </form>
                        </div>
                        <!-- /.box-body -->

                    </div>

                </div>
            </div>
            <!-- /.row -->

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
<script src="../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../dist/js/demo.js"></script>
</body>
</html>
