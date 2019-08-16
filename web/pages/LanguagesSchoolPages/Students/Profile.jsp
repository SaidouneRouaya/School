<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <!-- Select2 -->
    <link rel="stylesheet" href="../../../bower_components/select2/dist/css/select2.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../../dist/css/skins/_all-skins.min.css">
    <!-- Modules style -->
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
<div  class="wrapper">
    <%@ include file="../CommunFiles/header.jsp" %>
    <%@ include file="../CommunFiles/menu-side.jsp" %>


    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                User Profile
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Examples</a></li>
                <li class="active">User profile</li>
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
                                    <h3 class="widget-user-username"><b>${studentProfile.familyname} ${studentProfile.name}</b></h3>
                                    <h5 class="widget-user-desc">Student</h5>
                                </div>

                            <div class="col-md-4">

                                <a href="updateProfile.j?query=${studentProfile.id}"
                                   class="btn btn-app bg-navy pull-right">
                                    <i class=" fa fa-edit"></i>
                                    Update profile
                                </a>

                                <a id="addModule" href="" class="btn btn-app bg-teal pull-right" data-toggle="modal"
                                   data-target="#modal-default2">
                                    <i class=" fa fa-plus"></i>
                                    Add modules
                                </a>
                            </div>
                        </div>
                        <div class="widget-user-image">
                            <img class="img-circle"  src="${studentProfile.picture}"  alt="User Avatar">
                        </div>
                        <div class="box-footer">
                            <div class="row">
                                <div class="col-sm-12 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">Subscription date</h5>
                                        <span class="description-text"><c:out value ="${studentProfile.subscriptionDate}"/></span>
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
                            <h3 class="box-title">Student information</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">


                                <table class="table table-striped">
                                    <tbody>

                                    <tr>
                                        <td>Full name</td>
                                        <td><c:out value ="${studentProfile.name}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Phone number</td>
                                        <td><c:out value ="${studentProfile.phoneNumber1}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Phone number 2</td>
                                        <td><c:out value ="${studentProfile.phoneNumber2}"/></td>
                                    </tr>
                                    <tr>
                                         <td>Modules</td>
                                         <td><tg:forEach items="${listOfModules}"  var="item">
                                                <c:out value="${item.name}"/>,
                                             </tg:forEach></td>
                                    </tr>

                                    <tr>
                                        <td>Type</td>
                                        <td><c:out value ="${studentProfile.type}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Discount</td>
                                        <td><span >

                                            <c:choose>
                                              <c:when test="${empty studentProfile.discount}">
                                                  No discount
                                              </c:when>
                                              <c:otherwise>
                                                  <c:out value ="${studentProfile.discount}"/>
                                              </c:otherwise>
                                          </c:choose>
                                        </span></td>
                                    </tr>



                                    </tbody>
                                </table>

                                <div>
                                 <!--   <button type="submit" class="btn  bg-red-gradient pull-left">Delete</button>-->
                                    <button type="submit" class="btn bg-red-active pull-right"
                                            data-toggle="modal" data-target="#modal-default1" >Delete</button>

                                </div>


                        </div>
                        <!-- /.box-body -->

                    </div>

                </div>
                <div class="col-md-7">

                    <div class="box box-default">
                        <div class="box-header  with-border">
                            <h3 class="box-title">Payment history</h3>
                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                </button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                                <table class="table table-striped">
                                    <tbody>

                                    <tr>
                                        <th>#</th>
                                        <th>Module</th>
                                        <th>Date</th>
                                        <th>Amount</th>
                                    </tr>
                                <c:set var="number" value="0"/>

                                    <c:if test="${payments.size() gt 0}">

                                        <tg:forEach begin="0" end="${payments.size()-1}" var="i">

                                            <c:set var="number" value="${number+1}"/>

                                            <tr>
                                                <td><c:out value="${number}"/></td>
                                                <td><c:out value="${payments[i].module}"/></td>
                                                <td><c:out value="${payments[i].date}"/></td>
                                                <td><c:out value="${payments[i].amount}"/></td>
                                                <td>
                                                    <a type="submit" class="btn btn-default pull-right"
                                                            href="studentVoucher.j?p=${payments[i].id}"
                                                       target="_blank"><i class="fa fa-print"></i> print
                                                </a></td>
                                            </tr>

                                        </tg:forEach>
                                    </c:if>

                                    </tbody>
                                    <h4 class="pull-right">Total :${total}0 DZD</h4>

                                </table>


                        </div>
                        <!-- /.box-body -->

                    </div>

                </div>
            </div>
            <!-- /.row -->


            <!-- The Modal -->
            <form role="form" method="post" action="deleteStudent.j?query=${studentProfile.id}">
                <div class="modal fade" id="modal-default1">
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

            <form role="form" method="post" action="addModuleToStudent.j?id_student=${studentProfile.id}">
                <div class="modal fade" id="modal-default2">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                 <h4 class="modal-title">Add module</h4>
                            </div>
                            <div class="modal-body">
                                <div id="Student" class="form-group">
                                    <label>Modules</label>
                        <c:if test="${modules.size() gt 0}">
                                    <select  name="modules" id="modules" class="form-control select2" style="width: 100%;" >

                                        <tg:forEach begin="0" end="${modules.size()-1}"  var="i">

                                            <option name="module" value="${modules[i].id}"><c:out value="${modules[i].name}"/></option>

                                        </tg:forEach>

                                    </select>
                        </c:if>
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
<script src="../../../dist/js/addModule.js"></script>
<script>

</script>
</body>
</html>
