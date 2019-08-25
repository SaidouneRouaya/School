<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 13/07/2019
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<!-- daterange picker -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css">
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/iCheck/all.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
<!-- Bootstrap time Picker -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/timepicker/bootstrap-timepicker.min.css">
<!-- Select2 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/select2/dist/css/select2.min.css">
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
                Add group of students
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#" class="active">Add group of students</a></li>
            </ol>
        </section>


        <!-- Main content -->
        <section class="content">


            <div class="row">
                <div class="col-xs-3"></div>
                <div class="col-xs-6">
                    <!-- general form elements disabled -->
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title">Add new group of students</h3>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body">
                            <form role="form" method="post" action="addNewGroup.j">


                                <!-- text input -->
                                <div class="form-group">
                                    <label>Name of the group of students</label>
                                    <input type="text"  name="name"  id="name" class="form-control" placeholder="Enter ..." required>
                                </div>

                                <!-- select Module -->
                                <div class="form-group">
                                    <label>Module</label>
                                    <select name="modules" id="modules" class="form-control"  onchange="changeStudents()"  >
                                        <option name="empty" value="${0}" selected>

                                            Select a module</option>

                                        <tg:forEach begin="0" end="${modulesList.size() -1}" var="i">

                                            <option name="module"
                                                    value="${modulesList[i].id}">
                                                <c:out value="${modulesList[i].name}"/>
                                        </tg:forEach>

                                    </select>
                                </div>

                                <!-- select teacher -->
                                <div class="form-group">
                                    <label>Teacher</label>
                                    <select name="teachers" id="teacher" class="form-control" >
                                        <option name="empty" value="${0}" selected>

                                            Select a teacher</option>

                                        <tg:forEach begin="0" end="${teacherList.size() -1}" var="i">

                                        <option name="teacher"
                                                value="${teacherList[i].id}">
                                                <c:out value="${teacherList[i].name}"/><c:out value="${teacherList[i].familyname}"/>
                                            </tg:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Fees (unit price) </label>
                                    <div class="input-group">
                                        <span class="input-group-addon">DZD</span>
                                        <input type="number" name="fees" class="form-control">
                                        <span class="input-group-addon">.00</span>
                                    </div>
                                </div>
                                <!-- radio -->
                                <div class="form-group" >

                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="r3" value="Hour"  >Per hour
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="r3" value="Student" >Per student present
                                        </label>
                                    </div>
                                </div>

                                <!-- Subscritption date -->
                                <div class="form-group">
                                    <label>Start date:</label>

                                    <div class="input-group date">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <input type="text"  name="startDate" class="form-control pull-right" id="datepicker" required>
                                    </div>
                                    <!-- /.input  -->
                                </div>

                                <!-- timing -->

                                <div class="bootstrap-timepicker">
                                    <div class="form-group">
                                        <label>Start time :</label>

                                        <div class="input-group">
                                            <input type="text" name="startTime" class="form-control timepicker " required>

                                            <div class="input-group-addon">
                                                <i class="fa fa-clock-o"></i>
                                            </div>
                                        </div>
                                        <!-- /.input  -->
                                    </div>
                                    <!-- /.form  -->
                                </div>
                                <div class="bootstrap-timepicker">
                                    <div class="form-group">
                                        <label>End time :</label>

                                        <div class="input-group">
                                            <input type="text" name="endTime" class="form-control timepicker" required>

                                            <div class="input-group-addon">
                                                <i class="fa fa-clock-o"></i>
                                            </div>
                                        </div>
                                        <!-- /.input -->
                                    </div>
                                    <!-- /.form  -->
                                </div>


                                <!-- Session -->
                                <div class="form-group">
                                    <label>Number of seances</label>
                                    <input type="number" name="seancesNumber" class="form-control" placeholder="Enter number of seances ..." required>
                                </div>



                                <!-- Students -->
                                <div class="form-group">
                                    <label>Student (use ctrl+press)</label>

                                    <div id="selectionID" class="form-group" name="selectionID">

                                        <tg:forEach var="entry" items="${studentList.entrySet()}">
                                            <select name="studentsList" id="studentsList${entry.key}" multiple=""
                                                    class="form-control" style="display: none" >

                                                <tg:forEach begin="0" end="${entry.value.size() -1}" var="i">

                                                    <option name="student" value="${entry.value.get(i).id}">
                                                        <c:out value="${entry.value.get(i).name}"/>
                                                        <c:out value="${entry.value.get(i).familyname}"/>
                                                    </option>

                                                </tg:forEach>


                                            </select>
                                        </tg:forEach>

                                    </div>
                                </div>

                                <div>
                                    <button type="submit" class="btn bg-green-gradient pull-right" >Add</button>

                                </div>

                            </form>
                        </div>

                    </div>
                </div>
                <div class="col-xs-3"></div>
            </div>
            <div class="modal fade" id="modal-default">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Attention </h4>
                        </div>
                        <div class="modal-body">
                            <p>Would you save changes ?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                            <a href="Groups.j" class="btn bg-green  pull-right">Save changes</a>
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
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/resources/bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="${pageContext.request.contextPath}/resources/bower_components/moment/min/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="${pageContext.request.contextPath}/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="${pageContext.request.contextPath}/resources/plugins/iCheck/icheck.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/resources/dist/js/demo.js"></script>
<!-- Page script -->
<script>
    var id_previous="studentsList1";

    function changeStudents() {
        var id= document.getElementById("modules").value;

        console.log("im here");
        if (document.getElementById(id_previous) != null)  document.getElementById(id_previous).style.display="none";

        document.getElementById("studentsList"+id.toString()).style.display="block";

        id_previous="studentsList"+id.toString();
    }





    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2();

        //Datemask dd/mm/yyyy
        $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' });
        //Datemask2 mm/dd/yyyy
        $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' });
        //Money Euro
        $('[data-mask]').inputmask();

        //Date range picker
        $('#reservation').daterangepicker();
        //Date range picker with time picker
        $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY hh:mm:ss ' });
        //Date range as a button
        $('#daterange-btn').daterangepicker(
            {
                ranges   : {
                    'Today'       : [moment(), moment()],
                    'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
                    'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                    'This Month'  : [moment().startOf('month'), moment().endOf('month')],
                    'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                },
                startDate: moment().subtract(29, 'days'),
                endDate  : moment()
            },
            function (start, end) {
                $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
            }
        );

        //Date picker
        $('#datepicker').datepicker({
            autoclose: true
        });

        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        //Red color scheme for iCheck
        $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
            checkboxClass: 'icheckbox_minimal-red',
            radioClass   : 'iradio_minimal-red'
        });
        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass   : 'iradio_flat-green'
        });

        //Colorpicker
        $('.my-colorpicker1').colorpicker();
        //color picker with addon
        $('.my-colorpicker2').colorpicker();

        //Timepicker
        $('.timepicker').timepicker({
            showInputs: false,
            showMeridian: false
        })
    })
</script>

</body>
</html>
