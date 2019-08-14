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
    <!-- daterange picker -->
    <link rel="stylesheet" href="../../../bower_components/bootstrap-daterangepicker/daterangepicker.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="../../../bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="../../../plugins/iCheck/all.css">
    <!-- Bootstrap Color Picker -->
    <link rel="stylesheet" href="../../../bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
    <!-- Bootstrap time Picker -->
    <link rel="stylesheet" href="../../../plugins/timepicker/bootstrap-timepicker.min.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="../../../bower_components/select2/dist/css/select2.min.css">
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

    <style>
        .example-modal .modal {
            position: relative;
            top: auto;
            bottom: auto;
            right: auto;
            left: auto;
            display: block;
            z-index: 1;
        }

        .example-modal .modal {
            background: transparent !important;
        }
    </style>
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
                Add payment
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#" class="active">Add payment</a></li>
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
                            <h3 class="box-title">Payment</h3>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body">
                            <form role="form" id="form" method="post" >

                                <div id="printableArea">

                                    <div>
                                        <h3 id="date"></h3>
                                    </div>

                                    <div id="teacher" class="form-group">
                                        <label>Teacher</label>
                                        <select name="teachers" id="teachers"  class="form-control select2" style="width: 100%;"
                                                onchange="changeGroup()">
                                            <option name="empty" value="${0}" selected>

                                                Select a teacher</option>


                                            <tg:forEach begin="0" end="${teachersList.size() -1}" var="i">

                                                <option name="teacher" value="${teachersList[i].id} ${i}">
                                                    <c:out value="${teachersList[i].name}"/>
                                                    <c:out value="${teachersList[i].familyname}"/></option>

                                            </tg:forEach>

                                        </select>
                                    </div>


                                    <tg:forEach begin="0" end="${groupsList.size() -1}" var="i">
                                        <div id="groupList${i}" style="display: none">
                                            <label>Groups</label>

                                            <select name="groups" id="groups${i}" class="form-control select2"
                                                    style="width: 100%"
                                                    onchange="changeSalary()">

                                                <option name="empty" value="${0}" selected>

                                                    Select a group</option>


                                                <tg:forEach items="${groupsList.get(i)}" var="group">

                                                    <option name="group" value="${group.id}">

                                                        <c:out value="${group.name}"/></option>

                                                </tg:forEach>

                                            </select>
                                        </div>

                                    </tg:forEach>


                                    <div class="form-group" id="salaries">
                                        <div class="row">
                                            <div class="col-xs-9">

                                                <tg:forEach items="${groupSalariesMap}" var="groupSalary_entry">

                                                    <div id="salary${groupSalary_entry.key}"
                                                         style="display: none">

                                                        <p></p>
                                                        <b><h4 class="pull-left">Salary : <c:out
                                                                value="${groupSalary_entry.value}"/>0 DZD</h4></b>

                                                    </div>

                                                    <div class="form-group" style="display: none"
                                                         id="salaryDiv${groupSalary_entry.key}">


                                                                <div class="form-group">
                                                                    <p></p>
                                                                    <b><h4 class="pull-left">Amount for absent students
                                                                        :<c:out value="${groupSalariesAbsentMap.get(groupSalary_entry.key)}"/>0
                                                                        DZD</h4></b>
                                                                    <p></p>
                                                                </div>

                                                                <div class="form-group">
                                                                    <label>Total to pay </label>
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon">DZD</span>
                                                                        <input id="salaryOfGroup${groupSalary_entry.key}"
                                                                               type="text" name="totalToPay"
                                                                               class="form-control"
                                                                              >
                                                                        <span class="input-group-addon">.0</span>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                </tg:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <!--   <div id="salary">
                                           <p class="pull-left">Salary : 0 DZD</p>
                                       </div>-->

                                </div>

                                <div>
                                    <button type="submit" class="btn btn-default pull-right" onclick="printDiv('printableArea')" >
                                        <i class="fa fa-print"></i> Submit</button>

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
                            <a href="#.j" class="btn bg-green  pull-right">Save changes</a>
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
<script src="../../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Select2 -->
<script src="../../../bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src="../../../plugins/input-mask/jquery.inputmask.js"></script>
<script src="../../../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="../../../plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="../../../bower_components/moment/min/moment.min.js"></script>
<script src="../../../bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="../../../bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="../../../bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="../../../plugins/timepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll -->
<script src="../../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src="../../../plugins/iCheck/icheck.min.js"></script>
<!-- FastClick -->
<script src="../../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../../dist/js/demo.js"></script>
<!-- Bootstrap 3.3.7 -->

<!-- Page script -->
<script>
    document.getElementById("date").innerHTML =  formatDate();

    var id_previous_group="groupList0";
    var id_previous_salary="";
    var id_previous_toPay="";


    function  formatDate() {
        var date=new Date();
        var dd = date.getDate();
        var mm = date.getMonth() + 1;
        var yyyy = date.getFullYear();

        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        return dd + '/' + mm + '/' + yyyy;
    }

    function isChecked(event){
        if(event.getAttribute('checked') === null){
            event.setAttribute('checked','');
        }
        else{
            event.removeAttribute('checked');
        }
    }

    function changeGroup() {

        var id_salary = document.getElementById("teachers").value.split(" ",2);

        document.getElementById(id_previous_group).style.display = 'none';
        document.getElementById("groupList"+id_salary[1]).style.display = 'block';


        id_previous_group="groupList"+id_salary[1];
    }


   function changeSalary() {

        var id_salary = document.getElementById("teachers").value.split(" ",2);

        var id_group = document.getElementById("groups"+id_salary[1]).value;


        if (document.getElementById(id_previous_salary) !=null) document.getElementById(id_previous_salary).style.display = 'none';
        if (document.getElementById(id_previous_toPay) !=null) document.getElementById(id_previous_toPay).style.display = 'none';


        document.getElementById("salary"+id_group).style.display = 'block';
        document.getElementById("salaryDiv"+id_group).style.display = 'block';

        var salary= document.getElementById("salaryOfGroup"+id_group).value;


        document.getElementById("form").setAttribute("action", "addNewTeacherPayment.j?id_teacher="+id_salary[0]+"&id_group="+id_group+"&value="+salary) ;
        id_previous_salary="salary"+id_group;
        id_previous_toPay="salaryDiv"+id_group;
    }



    function printDiv(divName) {


        var printContents = document.getElementById(divName).innerHTML;
        var form = document.getElementById("form");

        var originalContents = document.body.innerHTML;


        console.log("im in print");
        document.body.innerHTML = printContents;

        window.print();
        document.body.appendChild(form);
        form.submit();


        document.body.innerHTML = originalContents;
    }


    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2();

        //Date picker
        $('#datepicker').datepicker({
            autoclose: true
        });

        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });
        //Red color scheme for iCheck
        $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
            checkboxClass: 'icheckbox_minimal-red',
            radioClass: 'iradio_minimal-red'
        });
        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
// ----- On render -----
        $(function() {

            $('#profile').addClass('dragging').removeClass('dragging');
        });

        $('#profile').on('dragover', function() {
            $('#profile').addClass('dragging')
        }).on('dragleave', function() {
            $('#profile').removeClass('dragging')
        }).on('drop', function(e) {
            $('#profile').removeClass('dragging hasImage');

            if (e.originalEvent) {
                var file = e.originalEvent.dataTransfer.files[0];
                console.log(file);

                var reader = new FileReader();

                //attach event handlers here...

                reader.readAsDataURL(file);
                reader.onload = function(e) {
                    console.log(reader.result);
                    $('#profile').css('background-image', 'url(' + reader.result + ')').addClass('hasImage');

                }

            }
        })


    });


</script>

</body>
</html>
