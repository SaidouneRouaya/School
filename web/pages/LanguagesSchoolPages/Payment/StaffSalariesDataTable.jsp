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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/Ionicons/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
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
    <%@ include file="../CommunFiles/menu-side.jsp" %>


    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Staff Salaries List
                <small>all staff</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Staff</a></li>
                <li class="active">Salaries list</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">


            <div class="row">
                <div class="col-xs-12">

                    <!-- /.box -->

                    <div class="box">
                        <!--  <div class="box-header">
                              <h3 class="box-title">Data Table With Full Features</h3>
                          </div>
                          <!-- /.box-header -->
                        <div class="box-body">
                            <div id="printableArea">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                <h3>Payment history</h3>
                                <br/>

                                <tr>
                                    <th>Full name</th>
                                    <th>Function</th>
                                    <th>Salary</th>
                                    <th>Receiver</th>
                                    <th>Date</th>

                                </tr>

                                </thead>
                                <tbody>

                                <tg:forEach items="${staffSalariesList}" var="item">

                                    <tr>
                                        <td># <c:out value="${item.staff.name}"/> <c:out value="${item.staff.familyname}"/></td>
                                        <td><c:out value="${item.staff.job}"/></td>
                                        <td><c:out value="${item.ammount.toString()}"/></td>
                                        <td><c:out value="${item.receiver}"/></td>
                                        <td><c:out value="${item.date}"/></td>

                                    </tr>

                                </tg:forEach>


                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Full name</th>
                                    <th>Function</th>
                                    <th>Salary</th>
                                    <th>Receiver</th>
                                    <th>Date</th>

                                </tr>
                                </tfoot>
                            </table>
                            <h4 class="pull-right">Total : ${totalPayStaff} 0 DZD</h4>
                            </div>
                            <div>

                                <a  target="_blank" class="btn btn-default pull-left" onclick="printDiv('printableArea')" ><i class="fa fa-print"></i> Print</a>
                            </div>
                        </div>
                        <!-- /.box-body -->

                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
        <section class="content">
            <div class="row">

                <div class="col-md-12">
                    <div class="box box-solid">
                        <div class="box-header with-border">
                            <h3 class="box-title"> Payment history by date</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">

                            <div class="col-md-6">
                                <div class="box-group" id="accordion">
                                    <!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->

                                    <c:set var="collapseNumber" value="${0}"/>

                                    <tg:forEach var="entryPayments" items="${staffPaymentListSorted1.entrySet()}">

                                        <c:set var="collapseNumber" value="${collapseNumber+1}"/>

                                        <div class="panel box box-primary">
                                            <div class="box-header with-border">
                                                <h4 class="box-title">
                                                    <a data-toggle="collapse" data-parent="#accordion"
                                                       href="#collapse${collapseNumber}">
                                                        Date : <c:out value="${entryPayments.key}"/>
                                                    </a>
                                                </h4>
                                            </div>

                                            <div id="collapse${collapseNumber}" class="panel-collapse collapse">

                                                <div class="box-body">

                                                    <div id="printableArea2">
                                                        <table id="example11"
                                                               class="table table-bordered table-striped">
                                                            <thead>
                                                            <h3>Date : <c:out value="${entryPayments.key}"/></h3>
                                                            <br/>

                                                            <tr>

                                                                <th>Full name</th>
                                                                <th>Function</th>
                                                                <th>Salary</th>
                                                                <th>Receiver</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tg:forEach begin="0" end="${entryPayments.value.size() -1}"
                                                                        var="k">
                                                                <tr>

                                                                    <td><c:out value="${entryPayments.value.get(k).getStaff().getName()}"/>
                                                                        <c:out value="${entryPayments.value.get(k).getStaff().getFamilyname()}"/></td>
                                                                    <td><c:out value="${entryPayments.value.get(k).getStaff().getJob()}"/></td>
                                                                    <td><c:out value="${entryPayments.value.get(k).getAmmount()}"/></td>
                                                                    <td><c:out value="${entryPayments.value.get(k).getReceiver()}"/></td>
                                                                </tr>
                                                            </tg:forEach>


                                                            </tbody>

                                                            <tfoot>
                                                            <tr>
                                                                <th>#</th>
                                                                <th>Full name</th>
                                                                <th>Function</th>
                                                                <th>Salary</th>
                                                                <th>Receiver</th>
                                                            </tr>
                                                            </tfoot>
                                                        </table>
                                                        <h4 class="pull-right">Total
                                                            :${totalsByDate.get(entryPayments.key)}0 DZD</h4>

                                                    </div>
                                                    <div>

                                                        <a target="_blank" class="btn btn-default pull-left"
                                                           onclick="printDiv('printableArea2')"><i
                                                                class="fa fa-print"></i>
                                                            Print</a>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>


                                    </tg:forEach>
                                </div>

                            </div>




                            <div class="col-md-6">
                                <div class="box-group" id="accordion2">
                                    <!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->

                                    <tg:forEach var="entryPayments" items="${staffPaymentListSorted2.entrySet()}">

                                        <c:set var="collapseNumber" value="${collapseNumber+1}"/>

                                        <div class="panel box box-success">
                                            <div class="box-header with-border">
                                                <h4 class="box-title">
                                                    <a data-toggle="collapse" data-parent="#accordion2"
                                                       href="#collapse${collapseNumber}">
                                                        Date : <c:out value="${entryPayments.key}"/>
                                                    </a>
                                                </h4>
                                            </div>

                                            <div id="collapse${collapseNumber}" class="panel-collapse collapse">

                                                <div class="box-body">

                                                    <div id="printableArea3">
                                                        <table id="example12"
                                                               class="table table-bordered table-striped">
                                                            <thead>
                                                            <h3>Date : <c:out value="${entryPayments.key}"/></h3>
                                                            <br/>
                                                            <tr>
                                                                <th>#</th>
                                                                <th>Full name</th>
                                                                <th>Function</th>
                                                                <th>Salary</th>
                                                                <th>Receiver</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>

                                                            <tg:forEach begin="0" end="${entryPayments.value.size() -1}" var="k">
                                                                <tr>

                                                                     <td><c:out value="${entryPayments.value.get(k).getStaff().getName()}"/>
                                                                        <c:out value="${entryPayments.value.get(k).getStaff().getFamilyname()}"/></td>
                                                                    <td><c:out value="${entryPayments.value.get(k).getStaff().getJob()}"/></td>
                                                                    <td><c:out value="${entryPayments.value.get(k).getAmmount()}"/></td>
                                                                    <td><c:out value="${entryPayments.value.get(k).getReceiver()}"/></td>
                                                                </tr>
                                                            </tg:forEach>

                                                            </tbody>

                                                            <tfoot>
                                                            <tr>

                                                                <th>Full name</th>
                                                                <th>Function</th>
                                                                <th>Salary</th>
                                                                <th>Receiver</th>
                                                            </tr>

                                                            </tfoot>
                                                        </table>
                                                        <h4 class="pull-right">Total
                                                            :${totalsByDate.get(entryPayments.key)}0 DZD</h4>

                                                    </div>
                                                    <div>

                                                        <a target="_blank" class="btn btn-default pull-left"
                                                           onclick="printDiv('printableArea3')"><i
                                                                class="fa fa-print"></i>
                                                            Print</a>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>


                                    </tg:forEach>
                                </div>

                            </div>

                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
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
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/resources/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/resources/dist/js/demo.js"></script>
<!-- page script -->
<script>
    $(function () {
        $('#example1').DataTable();
        $('#example11').DataTable();
        $('#example12').DataTable();
        $('#example2').DataTable({
            'paging': true,
            'lengthChange': false,
            'searching': false,
            'ordering': true,
            'info': true,
            'autoWidth': false
        })
    })
    function printDiv(divName) {
        var printContents = document.getElementById(divName).innerHTML;
        var originalContents = document.body.innerHTML;

        document.body.innerHTML = printContents;

        window.print();

        document.body.innerHTML = originalContents;
    }
</script>
</body>
</html>
