<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 13/07/2019
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="ISO-8859-1"%>
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
    </style>
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
                Add Student
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#" class="active">Add student</a></li>
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
                            <h3 class="box-title">Add new student</h3>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body">
                            <form role="form" method="post" action="addNewStudent.j" >

                                <div>
                                   <!-- <img class="profile-user-img img-responsive img-circle" src="${pageContext.request.contextPath}/resources/dist/img/user4-128x128.jpg" alt="User profile picture">-->
                                    <img class="profile-user-img img-responsive img-circle" src="${pageContext.request.contextPath}/resources/dist/img/avatar5.png" alt="Student picture">


                                </div>


                                <!-- text input -->
                                <div class="form-group">
                                    <label>Name</label>
                                    <input type="text" class="form-control" name="name"  placeholder="Enter name ..." required="required">
                                </div>

                                <div class="form-group">
                                    <label>Family name</label>
                                    <input type="text" class="form-control" name="familyName" placeholder="Enter family name ..." required="required">
                                </div>

                                <!-- Phone number -->

                                <div class="form-group">
                                    <label>Phone number 1:</label>

                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-phone"></i>
                                        </div>

                                            <input type="number" class="form-control"
                                                    name="phoneNumber1" required="required">

                                    </div>


                                    <label>Phone number 2:</label>

                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-phone"></i>
                                        </div>

                                            <input type="number" class="form-control"
                                                  name="phoneNumber2" required="required">

                                    </div>

                                </div>

                                <!-- Subscritption date -->
                                <div class="form-group">
                                    <label>Subscription date:</label>

                                    <div class="input-group date">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <label for="datepicker"></label><input type="text" class="form-control pull-right" name="subscriptionDate" id="datepicker" required="required">
                                    </div>
                                </div>

                                <!-- radio -->
                                <div class="form-group">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="r3" value="free" required="required">Free
                                        </label>
                                        <label>
                                            <input type="radio" name="r3"  value="payee" >Payee
                                        </label>
                                    </div>
                                </div>


                                <div>
                                    <button type="submit" class="btn bg-green-gradient pull-right">Add</button>
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
                            <a href="addNewStudent.j" class="btn bg-green  pull-right">Save changes</a>
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
<!-- Bootstrap 3.3.7 -->

<!-- Page script -->
<script>
    function enable(){ document.getElementById("discount").disabled = false;}
    function disable(){ document.getElementById("discount").disabled = true;}
   /*function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            document.getElementById("picture").value= reader.readAsDataURL(input.files[0]);

        }
    }

    $("#picture").change(function() {
        readURL(this);
    });*/
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


    })
</script>

</body>
</html>
