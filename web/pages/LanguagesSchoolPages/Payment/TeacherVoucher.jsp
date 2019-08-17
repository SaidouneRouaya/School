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

<body onload="window.print();">
<div class="wrapper">



    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->



        <!-- Main content -->
        <section class="content">

            <div class="row">

                <div class="col-xs-12">
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
                                        <h3 id="date">${date}</h3>
                                    </div>


                                    <div id="teacher" class="form-group">
                                        <label>Teacher</label>
                                        <select name="teachers" id="teachers"  class="form-control select2" style="width: 100%;">

                                            <option name="empty" value="${0}" selected>

                                                Select a teacher</option>
                                                <option name="teacher" selected >
                                                    <c:out value="${teacher.name}"/>
                                                    <c:out value="${teacher.familyname}"/></option>


                                        </select>
                                    </div>

                                    <!-- List of modules -->

                                        <div id="groupList" >
                                            <label>Groups' sessions</label>

                                            <div class="form-group" id="groups" >

                                                    <tg:forEach items="${groups}" var="group">
                                                    <div class="row">

                                                        <div class="col-xs-4">
                                                            <label> <c:out value="${group.name}"/></label>

                                                        </div>
                                                        <div class="col-xs-8">

                                                            <tg:forEach items="${group.sessionOfGroupsSet}" var="sessionsOfGroup">

                                                                <label>
                                                                    <input type="checkbox"
                                                                           name="group" id="group"
                                                                            <tg:forEach begin="0" end="${sessionPayed.size()-1}" var="i">
                                                                                <c:if test="${sessionsOfGroup.id eq sessionPayed[i] }">
                                                                                    checked
                                                                                </c:if>
                                                                            </tg:forEach>
                                                                    />
                                                                    Session of: <c:out value="${sessionsOfGroup.startDate}"/>

                                                                </label><br>

                                                            </tg:forEach>
                                                        </div>
                                                    </div>

                                                </tg:forEach>

                                            </div>

                                        </div>



                                    <div class="form-group" id="salaries">

                                        <!--------------------------------------------------------------->

                                        <ul class="list-group list-group-unbordered">
                                            <div id="salary">
                                                <li class="list-group-item">
                                                    <b>Salary : </b>
                                                    <span class=" btn bg-purple pull-right" id="salary_span">${payment.total}</span>
                                                </li>
                                            </div>

                                            <!-- To pay -->
                                            <div id="salaryDiv">
                                                <li class="list-group-item">
                                                    <b>Total payed : </b>
                                                    <span class="btn bg-navy pull-right" id="total_span">${payment.amount}</span>
                                                </li>
                                            </div>

                                        </ul>

                                    </div>

                                </div>

                            </form>
                        </div>

                    </div>
                </div>

            </div>

        </section>

    </div>

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
<!-- Bootstrap 3.3.7 -->

<!-- Page script -->
<script>


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
