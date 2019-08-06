<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 17/07/2019
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Advanced form elements</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- daterange picker -->
    <link rel="stylesheet" href="../../bower_components/bootstrap-daterangepicker/daterangepicker.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="../../bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="../../plugins/iCheck/all.css">
    <!-- Bootstrap Color Picker -->
    <link rel="stylesheet" href="../../bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
    <!-- Bootstrap time Picker -->
    <link rel="stylesheet" href="../../plugins/timepicker/bootstrap-timepicker.min.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="../../bower_components/select2/dist/css/select2.min.css">
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
                Groups
                <small>Group's details</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Group</a></li>
                <li class="active">Group's details</li>
            </ol>
        </section>

        <section class="content">


            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-12">
                    <div class="box  box-solid box-default ">

                        <div class="box-header with-border">
                            <div class="col-md-8">
                                <h3 class="box-title">Teacher : Flan ben flan</h3>
                                <p></p>
                                <p>Start date : 12/03/2018</p>
                                <p>Timing : 12:00-17:00 </p>
                            </div>
                            <div class="col-md-4">
                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                            class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <a href="#.j" class="btn btn-app bg-green-gradient pull-right">
                                    <i class=" fa fa-edit"></i>Add student</a>
                            </div>
                            <!-- /.box-tools -->
                        </div>


                        <!-- /.box-header -->
                        <div class="box-body">
                            <!--     <div class="mailbox-controls">
                               Check all button
                                <button type="button" class="btn btn-default btn-sm checkbox-toggle"><i
                                        class="fa fa-square-o"></i>
                                </button>
                                <div class="btn-groupOfStudents">
                                    <button type="button" class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i>
                                    </button>

                                </div>
                                <!-- /.btn-groupOfStudents -->

                                <!-- /.pull-right
                            </div>-->
                            <div class="table-responsive mailbox-messages">
                                <table class="table table-hover table-striped">

                                    <tbody>

                                    <tr>
                                        <th>#</th>
                                        <th>N°</th>
                                        <th>Full name</th>
                                        <th>Phone number</th>
                                        <th>S1: 12/03</th>
                                        <th>S2: 13/04</th>
                                        <th>S3: 12/05</th>
                                        <th>S4: 10/06</th>
                                        <th>S5: 10/07</th>
                                        <th>S6: 10/08</th>
                                        <th>S7: 10/09</th>
                                        <th>S8: 10/10</th>
                                    </tr>
                                    <tr>
                                        <td>
                                            <button type="button" class="btn  btn-box-tool btn-danger" data-widget="Remove" data-toggle="modal" data-target="#modal-danger"><i
                                                    class="fa fa-remove"></i>
                                            </button>
                                        </td>
                                        <td>1</td>
                                        <td>SAIDOUNE Raid</td>
                                        <td>092042018</td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red" checked disabled>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <button type="button" class="btn  btn-box-tool btn-danger" data-widget="Remove" data-toggle="modal" data-target="#modal-danger"><i
                                                    class="fa fa-remove"></i>
                                            </button>
                                        </td>
                                        <td>1</td>
                                        <td>SAIDOUNE Raid</td>
                                        <td>092042018</td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red" checked disabled>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <button type="button" class="btn  btn-box-tool btn-danger" data-widget="Remove" data-toggle="modal" data-target="#modal-danger"><i
                                                    class="fa fa-remove"></i>
                                            </button>
                                        </td>
                                        <td>1</td>
                                        <td>SAIDOUNE Raid</td>
                                        <td>092042018</td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red" checked disabled>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <button type="button" class="btn  btn-box-tool btn-danger" data-widget="Remove" data-toggle="modal" data-target="#modal-danger"><i
                                                    class="fa fa-remove"></i>
                                            </button>
                                        </td>
                                        <td>1</td>
                                        <td>SAIDOUNE Raid</td>
                                        <td>092042018</td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red" checked disabled>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <button type="button" class="btn  btn-box-tool btn-danger" data-widget="Remove" data-toggle="modal" data-target="#modal-danger"><i
                                                    class="fa fa-remove"></i>
                                            </button>
                                        </td>
                                        <td>1</td>
                                        <td>SAIDOUNE Raid</td>
                                        <td>092042018</td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red" checked disabled>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <button type="button" class="btn  btn-box-tool btn-danger" data-widget="Remove" data-toggle="modal" data-target="#modal-danger"><i
                                                    class="fa fa-remove"></i>
                                            </button>
                                        </td>
                                        <td>1</td>
                                        <td>SAIDOUNE Raid</td>
                                        <td>092042018</td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red" checked disabled>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <button type="button" class="btn  btn-box-tool btn-danger" data-widget="Remove" data-toggle="modal" data-target="#modal-danger"><i
                                                    class="fa fa-remove"></i>
                                            </button>
                                        </td>
                                        <td>1</td>
                                        <td>SAIDOUNE Raid</td>
                                        <td>092042018</td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red" checked disabled>
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                        <td>
                                            <label>
                                                <input type="checkbox" class="flat-red">
                                            </label>
                                        </td>
                                    </tr>


                                    </tbody>
                                </table>

                            </div>
                            <!-- /.box-body -->
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
                <div class="col-md-3"></div>
            </div>

            <div class="modal modal-danger fade" id="modal-danger">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Attention</h4>
                        </div>
                        <div class="modal-body">
                            <p>Would you really delete this element ?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
                            <a href="GroupDetails.j" class="btn btn-outline  pull-right">Delete anyway</a>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </section>
    </div>

    <!-- footer  -->
    <%@ include file="../CommunFiles/footer.jsp" %>

    <!-- Control Sidebar -->
    <%@ include file="../CommunFiles/controlMenu.jsp" %>
</div>


<!-- jQuery 3 -->
<script src="../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Select2 -->
<script src="../../bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src="../../plugins/input-mask/jquery.inputmask.js"></script>
<script src="../../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="../../plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="../../bower_components/moment/min/moment.min.js"></script>
<script src="../../bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="../../bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="../../bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="../../plugins/timepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll -->
<script src="../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="../../plugins/iCheck/icheck.min.js"></script>
<!-- FastClick -->
<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../dist/js/demo.js"></script>
<!-- Page script -->
<script>
    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2();


        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });

        //Colorpicker
        $('.my-colorpicker1').colorpicker();
        //color picker with addon
        $('.my-colorpicker2').colorpicker();

        //Timepicker
        $('.timepicker').timepicker({
            showInputs: false
        })
    })
</script>
</body>
</html>
