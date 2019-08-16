<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: pc
Date: 11/07/2019
Time: 16:20
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
    <!-- DataTables -->
    <link rel="stylesheet" href="../../../bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
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
                Payment List
                <small>all students</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Payments</a></li>
                <li class="active">Payments list</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">


            <div class="row">
                <div class="col-xs-12">

                    <!-- Add new student
                    <button type="button" class="btn-app .btn-lg"></button>-->


                    <!-- /.box -->

                    <div class="box">
                        <!--  <div class="box-header">
                              <h3 class="box-title">Data Table With Full Features</h3>
                          </div>
                          <!-- /.box-header -->
                        <div class="box-body" >
                            <div id="printableArea">
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>

                                        <th>Full name</th>
                                        <th>Module</th>
                                        <th>Price</th>
                                        <th>Receiver</th>

                                    </tr>

                                    </thead>
                                    <tbody>
                                    <tg:forEach begin="0" end="${studentPaymentList.size() -1}" var="i">

                                        <tr>
                                            <td><c:out value="${studentPaymentList[i].studentPay.name}"/> <c:out value="${studentPaymentList[i].studentPay.familyname}"/></td>
                                            <td><c:out value="${studentPaymentList[i].module}"/></td>
                                            <td><c:out value="${studentPaymentList[i].amount.toString()}"/></td>
                                            <td><c:out value="${studentPaymentList[i].receiver}"/></td>
                                        </tr>

                                    </tg:forEach>

                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th>Full name</th>
                                        <th>Module</th>
                                        <th>Price</th>

                                        <th>Receiver</th>
                                    </tr>
                                    </tfoot>
                                </table>
                                <h4 class="pull-right">Total : ${totalPayStudent} 0 DZD</h4>
                            </div>

                            <div>

                                <a  target="_blank" class="btn btn-default pull-left" onclick="printDiv('printableArea')" ><i class="fa fa-print"></i> Print</a>
                            </div>

                        </div>


                        <!--   <a  target="_blank" class="btn btn-default" onclick="window.print();return false;"><i class="fa fa-print"></i> Print</a>

                        /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>


        <section class="content">
            <div class="row">
                <div class="col-md-12">

                    <div class="box">
                        <!--  <div class="box-header">
                              <h3 class="box-title">Data Table With Full Features</h3>
                          </div>
                          <!-- /.box-header -->
                        <div class="box-body">


                            <!-- Custom Tabs -->
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs" role="tablist">
                                    <c:set var="tabNumber" value="${0}"/>
                                    <tg:forEach var="entryDates" items="${studentPaymentListSorted.entrySet()}">

                                        <c:set var="tabNumber" value="${tabNumber+1}"/>

                                        <!-- <= 7 -->
                                        <li role="presentation" <c:if test="${tabNumber eq 1}">class="active"</c:if> >
                                            <a
                                                    href="#tab_${tabNumber}"
                                                    aria-controls="tab_${tabNumber}"
                                                    role="tab"
                                                    data-toggle="tab">
                                                <c:out value="${entryDates.key}"/></a>
                                        </li>
                                    </tg:forEach>
                                </ul>


                                <c:set var="tabNumber" value="${0}"/>
                                <div id="printableArea2">
                                    <div class="tab-content">

                                        <tg:forEach var="entryPayments" items="${studentPaymentListSorted.entrySet()}">

                                            <c:set var="tabNumber" value="${tabNumber+1}"/>
                                            <div role="tabpanel"
                                                 class="tab-pane  <c:if test="${tabNumber eq 1}">active</c:if> "
                                                 id="tab_${tabNumber}">


                                                <table id="example11" class="table table-bordered table-striped">
                                                    <thead>
                                                    <h3>Date : <c:out value="${entryPayments.key}"/></h3>
                                                    <br/>
                                                    <tr>

                                                        <th>Full name</th>
                                                        <th>Module</th>
                                                        <th>Price</th>
                                                        <th>Receiver</th>
                                                    </tr>

                                                    </thead>
                                                    <tbody>


                                                    <tg:forEach begin="0" end="${entryPayments.value.size() -1}"
                                                                var="k">


                                                        <tr>
                                                            <td><c:out
                                                                    value="${entryPayments.value.get(k).getStudentPay().getName()}"/>
                                                                <c:out value="${entryPayments.value.get(k).getStudentPay().getFamilyname()}"/></td>

                                                            <td><c:out
                                                                    value="${entryPayments.value.get(k).module}"/></td>
                                                            <td><c:out
                                                                    value="${entryPayments.value.get(k).amount}"/></td>
                                                            <td><c:out
                                                                    value="${entryPayments.value.get(k).receiver}"/></td>
                                                        </tr>

                                                    </tg:forEach>

                                                    </tbody>
                                                    <tfoot>
                                                    <tr>
                                                        <th>Full name</th>
                                                        <th>Module</th>
                                                        <th>Price</th>

                                                        <th>Receiver</th>
                                                    </tr>
                                                    </tfoot>
                                                </table>

                                                <h4 class="pull-right">Total : ${totalsByDate.get(entryPayments.key)}
                                                    0 DZD</h4>

                                                <div>

                                                    <a target="_blank" class="btn btn-default pull-left"
                                                       onclick="printDiv('printableArea2')"><i class="fa fa-print"></i>
                                                        Print</a>
                                                </div>

                                            </div>

                                        </tg:forEach>

                                    </div>

                                </div>

                                <!-- /.tab-content -->
                            </div>
                            <!-- nav-tabs-custom -->
                        </div>
                    </div>
                </div>
            </div>


            <!-- /.row -->
        </section>
        <!-- /.content -->
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
<!-- DataTables -->
<script src="../../../bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../../../bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="../../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../../dist/js/demo.js"></script>

<!-- page script -->
<script>
    $(function () {
        $('#example1').DataTable();
        $('#example11').DataTable();
        $('#example2').DataTable({
            'paging': true,
            'lengthChange': false,
            'searching': false,
            'ordering': true,
            'info': true,
            'autoWidth': false
        })
    });
    function printDiv(divName) {
        var printContents = document.getElementById(divName).innerHTML;
        var originalContents = document.body.innerHTML;

        document.body.innerHTML = printContents;

        window.print();

        document.body.innerHTML = originalContents;
    }
/*
    $('#Select').on('change', function (e) {
        $('.tab-pane').hide();
        $('.tab-pane').eq($(this).val()).show();
    });
*/

</script>
</body>
</html>
