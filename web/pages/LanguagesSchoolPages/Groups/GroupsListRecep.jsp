<%@ taglib prefix="tg" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 17/07/2019
  Time: 15:12
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
    <!-- Theme style -->
    <link rel="stylesheet" href="../../../dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../../../dist/css/skins/_all-skins.min.css">
    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    <!-- demo style -->
    <style>
        /* FROM HTTP://WWW.GETBOOTSTRAP.COM
         * Glyphicons
         *
         * Special styles for displaying the icons and their classes in the docs.
         */

        .bs-glyphicons {
            padding-left: 0;
            padding-bottom: 1px;
            margin-bottom: 20px;
            list-style: none;
            overflow: hidden;
        }

        .bs-glyphicons li {
            float: left;
            width: 25%;
            height: 115px;
            padding: 10px;
            margin: 0 -1px -1px 0;
            font-size: 12px;
            line-height: 1.4;
            text-align: center;
            border: 1px solid #ddd;
        }

        .bs-glyphicons .glyphicon {
            margin-top: 5px;
            margin-bottom: 10px;
            font-size: 24px;
        }

        .bs-glyphicons .glyphicon-class {
            display: block;
            text-align: center;
            word-wrap: break-word; /* Help out IE10+ with class names */
        }

        .bs-glyphicons li:hover {
            background-color: rgba(86, 61, 124, .1);
        }

        @media (min-width: 768px) {
            .bs-glyphicons li {
                width: 12.5%;
            }
        }
    </style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body  class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="../CommunFiles/header.jsp" %>
    <%@ include file="../CommunFiles/menu-side.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Groups
                <small>Groups list</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Groups</a></li>
                <li class="active">Groups list</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">

                    <div class="box box-default">
                        <a type="button"  href="addGroup.j" class="btn btn-block btn-primary btn-lg">Add new group of students</a>
                    </div>


                </div>
            </div>


            <div class="row">


                <c:set var="Number" value="${0}"/>

                    <tg:forEach begin="0" end="${groupsList.size() -1}" var="i">


                    <div class="col-lg-3 col-xs-6">
                                        <!-- small box -->
                                        <div class="small-box
                                       <c:choose>
                                        <c:when test="${Number eq 0}">
                                        bg-aqua
                                        </c:when>
                                        <c:when test="${Number eq 1}">
                                        bg-orange
                                        </c:when>

                                         <c:when test="${Number eq 2}">
                                        bg-olive
                                         </c:when>
                                        <c:when test="${Number eq 3}">
                                        bg-maroon
                                        </c:when>

                                        <c:when test="${Number eq 4}">
                                        bg-lime
                                        </c:when>

                                       <c:when test="${Number eq 5}">
                                        bg-blue
                                        </c:when>

                                        <c:when test="${Number eq 6}">
                                        bg-purple
                                        </c:when>

                                        <c:when test="${Number eq 7}">
                                        bg-navy
                                        </c:when>
                                        <c:when test="${Number eq 8}">
                                        bg-gray
                                        </c:when>
                                        <c:when test="${Number eq 9}">
                                        bg-black
                                        </c:when>

                                        <c:when test="${Number eq 10}">
                                        bg-teal
                                        </c:when>
                                        <c:when test="${Number eq 11}">
                                        bg-primary
                                        </c:when>
                                        <c:when test="${Number eq 12}">
                                        bg-yellow
                                        </c:when>
                                        <c:when test="${Number eq 13}">
                                        bg-red
                                        </c:when>
                                        <c:when test="${Number eq 14}">
                                        bg-green
                                        </c:when>
                                        <c:otherwise>
                                              <c:set var="Number" value="${0}"/>
                                        </c:otherwise>
                                    </c:choose> ">
                                            <div class="inner">
                                                <c:set var="Number" value="${Number+1}"/>
                                                <h3><c:out value="${groupsList[i].name}" /></h3>
                                                <p><c:out value="${groupsList[i].module.name}" /></p>
                                            </div>
                                            <div class="icon">
                                                <i class="ion ion-person-add"></i>
                                            </div>
                                            <a href="GroupDetails.j?id_group=${groupsList[i].id}" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                                        </div>
                                    </div>

                    </tg:forEach>

                <!-- ./col -->
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


<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="../../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../../dist/js/demo.js"></script>
</body>
</html>
