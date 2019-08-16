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

        @media print {
            html, body {
                height: 99%;
            }
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

                            <!-- List of modules -->
                                    <tg:forEach begin="0" end="${groupsList.size() -1}" var="i">
                                        <div id="groupList${i}" style="display: none">
                                            <label>Groups' sessions</label>

                                            <div class="form-group" id="groups${i}" >

                                                <tg:forEach items="${groupsList.get(i)}" var="group">
                                                    <label> <c:out value="${group.name}"/></label>

                                                    <!--sessionsOfGroup is a list try size() -->

                                                    <tg:forEach items="${group_sessionsMap.get(group.id)}" var="sessionsOfGroup">

                                                        <label>
                                                            <input type="checkbox" onclick="isChecked(this)"
                                                                   name="group"
                                                                   id="group"
                                                                   value="${group.paymentType} ${group.fees} ${sessionSalariesAbsentMap.get(sessionsOfGroup.id)} ${group.id}"/>
                                                            Session of: <c:out value="${sessionsOfGroup.startDate}"/>,
                                                        </label><br>

                                                    </tg:forEach>
                                                </tg:forEach>

                                            </div>

                                        </div>
                                    </tg:forEach>


                                    <div class="form-group" id="salaries">

                                        <!--------------------------------------------------------------->

                                        <ul class="list-group list-group-unbordered">
                                            <div id="salary">
                                                <li class="list-group-item">
                                                    <b>Salary : </b>
                                                    <span class=" btn bg-purple pull-right" id="salary_span"></span>
                                                </li>
                                            </div>


                                            <!--Absent of this student -->
                                            <div id="AbsentDiv" >
                                                <li class="list-group-item">
                                                    <b>Amount for absent students :</b>
                                                    <span class="btn bg-teal pull-right" id="absent_span"></span>
                                                </li>
                                            </div>


                                            <!-- To pay -->
                                            <div id="salaryDiv">
                                                <li class="list-group-item">
                                                    <b>Total to pay : </b>
                                                    <input id="salaryOfGroup" type="text" name="totalToPay"
                                                           class="col-xs-4 btn bg-navy pull-right"
                                                           onchange="setValue(this)">
                                                </li>
                                            </div>

                                        </ul>

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
    var total=0 ;
    var absentTotal=0;
    var id_previous_group="groupList0";
    var id_previous_salary="";
    var id_previous_absent="";
    var id_previous_toPay="salaryDiv";


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

    function setValue(event){
            console.log("je suis set value "+event.value);

           document.getElementById("salaryOfGroup").setAttribute("value",event.value);
           console.log("je suis set value 2 "+ document.getElementById("salaryOfGroup").value);


    }

    function changeGroup() {

        var id_salary = document.getElementById("teachers").value.split(" ",2);

        document.getElementById(id_previous_group).style.display = 'none';
        document.getElementById("groupList"+id_salary[1]).style.display = 'block';


        id_previous_group="groupList"+id_salary[1];
    }

    function isChecked(event) {

        var idGroup_fees_absentFees = event.value.split(" ", 4);
        var id_group = idGroup_fees_absentFees[3];
        var id_salary = document.getElementById("teachers").value.split(" ",2);

        var salary= document.getElementById("salaryOfGroup").value;


        console.log(idGroup_fees_absentFees[0]);

        if (event.getAttribute('checked') === null) {
            event.setAttribute('checked', '');
            total+= parseInt(idGroup_fees_absentFees[1]) ;
            if(idGroup_fees_absentFees[0]==='Student'){

                absentTotal+= parseInt(idGroup_fees_absentFees[2]) ;
            }

        }
        else {
            event.removeAttribute('checked');
            total -= parseInt(idGroup_fees_absentFees[1]);
            if (idGroup_fees_absentFees[0] === 'Student') {
                absentTotal -= parseInt(idGroup_fees_absentFees[2]);
            }
        }

        document.getElementById("salary_span").innerText=total+".0 DZD";
        if(idGroup_fees_absentFees[0]==='Student') {
            document.getElementById("absent_span").innerText = absentTotal + ".0 DZD";
        }


        document.getElementById("form").setAttribute("action", "addNewTeacherPayment.j?id_teacher="+id_salary[0]+"&id_group="+id_group+"&value="+salary) ;

    }
   function changeSalary() {

        var id_salary = document.getElementById("teachers").value.split(" ",2);

        var id_group = document.getElementById("groups"+id_salary[1]).value;


        if (document.getElementById(id_previous_salary) !=null) document.getElementById(id_previous_salary).style.display = 'none';
        if (document.getElementById(id_previous_absent) !=null) document.getElementById(id_previous_absent).style.display = 'none';
        if (document.getElementById(id_previous_toPay) !=null) document.getElementById(id_previous_toPay).style.display = 'none';


        document.getElementById("salary"+id_group).style.display = 'block';
        document.getElementById("AbsentDiv"+id_group).style.display = 'block';
        document.getElementById("salaryDiv").style.display = 'block';

        console.log(document.getElementById("salaryOfGroup"));
        console.log(document.getElementById("salaryOfGroup").value);

        var salary= document.getElementById("salaryOfGroup").value;


        document.getElementById("form").setAttribute("action", "addNewTeacherPayment.j?id_teacher="+id_salary[0]+"&id_group="+id_group+"&value="+salary) ;
        id_previous_salary="salary"+id_group;
        id_previous_absent="AbsentDiv"+id_group;
        id_previous_toPay="salaryDiv";
    }



    function printDiv(divName) {


        document.getElementById("AbsentDiv").innerHTML="";
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
