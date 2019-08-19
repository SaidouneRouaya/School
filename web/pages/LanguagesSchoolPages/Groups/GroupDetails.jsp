<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/Ionicons/css/ionicons.min.css">
    <!-- daterange picker -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
    <!-- iCheck for checkboxes and radio inputs -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/iCheck/all.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/select2/dist/css/select2.min.css">

    <!-- Bootstrap Color Picker -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
    <!-- Bootstrap time Picker -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/plugins/timepicker/bootstrap-timepicker.min.css">
    <!-- Select2 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bower_components/select2/dist/css/select2.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/modalStyle2.css">

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
        <%@ include file="../CommunFiles/menu-side.jsp" %>

    </c:if>

    <c:if test="${profile.type eq 'Receptionist'}">
        <%@ include file="../CommunFiles/menu-side-receptionist.jsp" %>
    </c:if>


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
                                <p>Timing : <c:out value="${group.getTime(group.startTime)}"/> - <c:out
                                        value="${group.getTime(group.endTime)}"/></p>
                            </div>
                            <div class="col-md-4">
                                <div class="box-tools pull-right">
                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                            class="fa fa-minus"></i>
                                    </button>
                                </div>

                                <a href="" class="btn btn-app bg-teal pull-right" data-toggle="modal"
                                   data-target="#modal-default3">
                                    <i class=" fa fa-calendar"></i>Add session</a>

                                <a href="updateGroup.j?query=${group.id}"
                                   class="btn btn-app bg-purple  pull-right">
                                    <i class=" fa fa-plus"></i>
                                    Update group
                                </a>


                                <a href="" class="btn btn-app bg-red pull-right" data-toggle="modal"
                                   data-target="#modal-default2">
                                    <i class=" fa fa-minus"></i>Delete Group</a>

                            </div>
                            <!-- /.box-tools -->
                        </div>
                    </div>
                </div>
            </div>


            <tg:forEach begin="0" end="${size-1}" var="i">
                <div class="row">
                    <div class="col-md-3"></div>

                    <div class="col-md-12">

                        <div class="box  box-solid box-default ">

                            <div class="box-header with-border">

                                <div class="col-md-12">

                                    <h4>Session of : <c:out value="${sessions[i].startDate}"/></h4>
                                    <div class="box-tools pull-right">
                                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                                class="fa fa-minus"></i>
                                        </button>
                                    </div>


                                </div>
                                <!-- /.box-tools -->
                            </div>

                            <!-- /.box-header -->
                            <div class="box-body">


                                <div class="table-responsive mailbox-messages">
                                    <table id="example1" class="table table-hover table-striped">
                                        <thead>

                                        <br/>

                                        <a href="" class="btn btn-app bg-yellow pull-right"
                                           data-toggle="modal"
                                           data-target="#modal-default1">
                                            <i class=" fa fa-edit"></i>Add student</a>


                                        <tr bgcolor="#deb887">
                                          <!--  <th>#</th>-->
                                            <th>N°</th>
                                            <th>Full name</th>
                                            <th>Phone number</th>

                                            <tg:forEach items="${sessions[i].seancesSet}" var="seance">
                                                <th>S:
                                                    <c:choose>
                                                        <c:when test="${seance.date ne null}">
                                                            <c:out value="${seance.date}"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            no date
                                                        </c:otherwise>
                                                    </c:choose>
                                                </th>
                                            </tg:forEach>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <form id="session_form${i}" method="post">


                                            <c:set var="number" value="${0}"/>

                                            <tg:forEach items="${students.get(sessions[i].id)}"
                                                        var="student">

                                                <c:set var="number" value="${number+1}"/>
                                                <tr
                                                        <c:choose>
                                                            <c:when test="${student.type eq 'free'}">
                                                                class="bg-purple disabled"
                                                            </c:when>

                                                            <c:otherwise>
                                                                <tg:forEach begin="0"
                                                                            end="${unpaidStudent.size()-1}"
                                                                            var="m">

                                                                    <c:if test="${unpaidStudent[m].id eq student.id}">
                                                                        class="bg-red disabled"
                                                                    </c:if>

                                                                </tg:forEach>
                                                            </c:otherwise>
                                                        </c:choose>
                                                >
                                               <!--     <td>
                                                        <a class="btn  btn-box-tool  bg-red-active"
                                                           href="deleteStudentFromSession.j?query=${student.id}&id_session=${sessions[i].id}&group=${group.id}">
                                                            <i class="fa fa-remove"></i>
                                                        </a>
                                                    </td>-->
                                                    <td><c:out value="${number}"/></td>
                                                    <!-- nom du type de la stat  -->
                                                    <td><a class="text-black"
                                                           href="Profile.j?query=${student.id}">
                                                        <b><c:out value="${student.name}"/> <c:out
                                                                value="${student.familyname}"/></b>
                                                    </a></td>


                                                    <td><c:out value="${student.phoneNumber1}"/></td>

                                                    <c:set var="contains" value="${false}"/>

                                                    <tg:forEach items="${sessions[i].seancesSet}"
                                                                var="seance">

                                                        <td>
                                                            <label>Present
                                                                <input type="checkbox" name="sess" id="sess"
                                                                       value="${seance.id} ${student.id}"
                                                                       onclick="isChecked(this)"

                                                                        <tg:forEach begin="0"
                                                                                    end="${presences.get(student.id).size()-1}"
                                                                                    var="k">

                                                                            <c:choose>
                                                                                <c:when test="${(seance.date eq null)}">

                                                                                </c:when>

                                                                                <c:when test="${(presences.get(student.id).get(k).id_session eq seance.id)
                                                                            and (presences.get(student.id).get(k).isPresent()) and (seance.date.toString() eq now)}">

                                                                                    checked disabled
                                                                                </c:when>

                                                                                <c:when test="${(presences.get(student.id).get(k).id_session eq seance.id)
                                                                            and (presences.get(student.id).get(k).isPresent())}">

                                                                                    checked
                                                                                </c:when>
                                                                                <c:otherwise>

                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </tg:forEach>

                                                                        <c:if test="${(seance.date ne null) and (seance.date.toString() lt now) }">
                                                                            disabled
                                                                        </c:if>
                                                                />

                                                            </label>
                                                        </td>

                                                    </tg:forEach>
                                                </tr>
                                                <td></td>

                                            </tg:forEach>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <tg:forEach items="${sessions.get(i).seancesSet}"
                                                            var="seance">

                                                    <td>
                                                        <button
                                                                type="submit" class="btn bg-olive"
                                                                <c:choose>
                                                                    <c:when test="${(seance.date ne null)}">
                                                                        disabled
                                                                    </c:when>
                                                                    <c:otherwise>

                                                                    </c:otherwise>
                                                                </c:choose>


                                                                onclick="markPresence(${group.id}, ${i})">Submit
                                                        </button>
                                                    </td>

                                                </tg:forEach>
                                            </tr>
                                        </form>
                                        </tbody>

                                    </table>
                                </div>


                            </div>
                        </div>

                    </div>

                    <div class="col-md-3"></div>

                </div>
                <!-- /.box-body -->


                <!-- The Modal -->

                <form role="form" method="post"
                      action="addStudentToSession.j?query=${sessions[i].id}&id_group=${group.id}">
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

                                        <select name="students" id="students" class="form-control select2"
                                                style="width: 100%;">

                                            <option name="empty" value="${0}" selected>
                                                Select a student
                                            </option>

                                            <tg:forEach begin="0" end="${addStudents.size()-1}" var="j">

                                                <option name="student" value="${addStudents[j].id}"><c:out
                                                        value="${addStudents[j].name}"/>
                                                    <c:out value="${addStudents[j].familyname}"/></option>

                                            </tg:forEach>

                                        </select>

                                    </div>

                                    <!-- Subscritption date -->
                                    <div class="form-group">
                                        <label>Start date:</label>

                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input type="text" name="startDate" class="form-control pull-right"
                                                   id="datepicker">
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
            </tg:forEach>



                    <form role="form" method="post" action="addSessionToGroup.j?query=${group.id}">

                <div class="modal fade" id="modal-default3">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Warning </h4>
                            </div>
                            <div class="modal-body">


                                <!-- Subscritption date -->
                                <div class="form-group">
                                    <label>Start date:</label>

                                    <div class="input-group date">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <input type="text" name="date" class="form-control pull-right" id="datepicker2">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label>Number of seances</label>
                                    <input type="number" name="seancesNumber" class="form-control"
                                           placeholder="Enter number of seances ..." required>
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

            <form role="form" method="post" action="">

                <div class="modal fade" id="modal-default4">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Warning </h4>
                            </div>
                            <div class="modal-body">
                                <p>Do you really want to delete this student ?</p>
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


    function isChecked(event) {
        if (event.getAttribute('checked') === null) {
            event.setAttribute('checked', '');
        }
        else {
            event.removeAttribute('checked');
        }
    }

    function markPresence(id_group, i) {

        var form = document.getElementById("session_form" + i.toString());

        var action = "markPresence.j?id_group=" + id_group;

        form.setAttribute("action", action);

        console.log(document.getElementById("buttonSubmit"));
        console.log(document.getElementById("buttonSubmit").disabled);

        document.getElementById("buttonSubmit").disabled = true;

        console.log(document.getElementById("buttonSubmit").disabled);

        form.submit();
    }

    function formatDate(date) {

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

    var button = $('#buttonSubmit');
    //   $(button).attr('disabled', 'disabled');

    $('.click').click(function () {
        /*if ($(button).attr('disabled')) $(button).removeAttr('disabled');
        else
          */
        $(button).attr('disabled', 'disabled');
    });

    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2();

        $('#datepicker2').datepicker({
            autoclose: true
        });
        $('#datepicker').datepicker({
            autoclose: true
        });
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
