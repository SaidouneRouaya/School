<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <link rel="stylesheet" href="../../../bower_components/select2/dist/css/select2.min.css">

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
    <link rel="stylesheet" href="../../../dist/css/modalStyle2.css">

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
                            <div class="col-md-4">
                                <h3 class="box-title">Group : <c:out value="${group.name}"/></h3>
                                <p></p>
                                <h5 class="box-title">Teacher : <c:out value="${group.teacher.name}"/>
                                    <c:out value="${group.teacher.familyname}"/></h5>
                                <p></p>
                                <h5 class="box-title">Module : <c:out value="${group.module.name}"/></h5>
                                <p></p>
                            </div>
                            <div class="col-md-4">
                                <p>Start date : <c:out value="${group.getDate(group.startDate)}"/></p>
                                <p>Timing : <c:out value="${group.getTime(group.startTime)}"/> - <c:out value="${group.getTime(group.endTime)}"/> </p>
                            </div>
                            <div class="col-md-4">
                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                            class="fa fa-minus"></i>
                                    </button>
                                </div>

                                <a id ="addModule" href="" class="btn btn-app bg-teal pull-right" data-toggle="modal" data-target="#modal-default1">
                                    <i class=" fa fa-edit"></i>Add student</a>

                                <a id="" href="updateGroup.j?query=${group.id}" class="btn btn-app bg-purple  pull-right" >
                                    <i class=" fa fa-plus"></i>
                                    Update group
                                </a>


                            </div>
                            <!-- /.box-tools -->
                        </div>


                        <!-- /.box-header -->
                        <div class="box-body">
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

                                    </tr>
                                    <c:set var="number" value="${0}"/>
                                        <tg:forEach items="${group.studentsSet}" var="student">
                                            <c:set var="number" value="${number+1}"/>
                                        <tr>
                                            <td>
                                                <a  class="btn  btn-box-tool btn-danger"
                                                        data-toggle="modal" data-target="#modal-default3"
                                                href="deleteStudentFromGroup.j?query=${student.id}&id_group=${group.id}">

                                                    <i class="fa fa-remove" ></i>
                                                </a>
                                            </td>
                                            <td><c:out value="${number}"/></td>
                                            <!-- nom du type de la stat  -->
                                            <td><a href="Profile.j?query=${student.id}" >
                                                <c:out value="${student.name}"/>  <c:out value="${student.familyname}"/>
                                            </a></td>

                                            <td><c:out value ="${student.phoneNumber1}"/></td>
                                            <td>
                                                <label>
                                                    <input type="checkbox"/>
                                                </label>
                                            </td>
                                            <td>
                                                <label>
                                                    <input type="checkbox"/>
                                                </label>
                                            </td>
                                            <td>
                                                <label>
                                                    <input type="checkbox"/>
                                                </label>
                                            </td>

                                        </tr>

                                    </tg:forEach>


                                    </tbody>
                                </table>

                            </div>



                            <button type="submit" class="btn bg-danger-gradient  pull-right"
                                    data-toggle="modal" data-target="#modal-default2" >Delete Group</button>
                            <!-- /.box-body -->
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
                <div class="col-md-3"></div>
            </div>
            <!-- The Modal -->

            <form role="form" method="post" action="addStudentToGroup.j?query=${group.id}">
                <div class="modal fade" id="modal-default1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Warning </h4>
                            </div>
                            <div class="modal-body">
                                <div id="Student" class="form-group">
                                    <label>Students</label>

                                    <select  name="students" id="students" class="form-control select2" style="width: 100%;" >

                                        <tg:forEach begin="0" end="${students.size()-1}"  var="i">

                                            <option name="student" value="${students[i].id}"><c:out value="${students[i].name}"/>
                                                <c:out value="${students[i].familyname}"/></option>

                                        </tg:forEach>

                                    </select>

                                </div>

                                <!-- Subscritption date -->
                                <div class="form-groupOfStudents">
                                    <label>Start date:</label>

                                    <div class="input-groupOfStudents date">
                                        <div class="input-groupOfStudents-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <input type="text" name="startDate" class="form-control pull-right" id="datepicker">
                                    </div>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel
                                </button>
                                <button type="submit" class="btn bg-green  pull-right">Add</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
            </form>

            <form role="form" method="post" action="deleteGroup.j?query=${group.id}">
                <div class="modal fade" id="modal-default2">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Warning </h4>
                            </div>
                            <div class="modal-body">
                                <p>Do you really want to delete this group ?</p>
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
<script src="../../../dist/js/addModule.js"></script>
<!-- Page script -->
<script>



    function setForm(id_student, id_group) {

        console.log("im here to submit");
        var form = document.getElementById("form").setAttribute("action", "deleteStudentFromGroup.j?query="+id_student+"&id_group="+id_group) ;
        form.submit();

    }

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
