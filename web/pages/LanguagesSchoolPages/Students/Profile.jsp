<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                                    <h3 class="widget-user-username">Alexander Pierce</h3>
                                    <h5 class="widget-user-desc">Student</h5>
                                </div>

                                <div class="col-md-4">

                                    <a href="updateProfile.j?query=${studentProfile.id}" class="btn btn-app btn-default pull-right" >
                                        <i class=" fa fa-edit"></i>
                                        Update profile
                                    </a>
                                    <a id="addModule" href="#" class="btn btn-app btn-default pull-right" >
                                        <i class=" fa fa-plus"></i>
                                      Add modules
                                    </a>
                                </div>
                        </div>
                        <div class="widget-user-image">
                            <img class="img-circle" src="<c:out value ="${studentProfile.picture}"/>"  alt="User Avatar">
                        </div>
                        <div class="box-footer">
                            <div class="row">
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">Subscription date</h5>
                                        <span class="description-text"><c:out value ="${studentProfile.subscriptionDate}"/></span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                                <div class="col-sm-4 border-right">
                                    <div class="description-block">
                                        <h5 class="description-header">13,000</h5>
                                        <span class="description-text">information</span>
                                    </div>
                                    <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                                <div class="col-sm-4">
                                    <div class="description-block">
                                        <h5 class="description-header">35</h5>
                                        <span class="description-text">information</span>
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

                            <form>
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
                                        <td>Modules : </td>

                                        <td>English  ( many to many)..

                                        </td>

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
                                                  studentProfile.discount
                                              </c:otherwise>
                                          </c:choose>
                                        </span></td>
                                    </tr>



                                    </tbody>
                                </table>

                                <div>
                                    <a href="#" class="btn  bg-red-gradient pull-left">Delete</a>

                                </div>


                            </form>
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

                            <form>
                                <table class="table table-striped">
                                    <tbody>

                                    <tr>
                                        <th>#</th>
                                        <th>Module</th>
                                        <th>Date</th>
                                        <th>Amount</th>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>English</td>
                                        <td>12/04/2018</td>
                                        <td>3000.00 DZD</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>English</td>
                                        <td>12/05/2018</td>
                                        <td>3000.00 DZD</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>English</td>
                                        <td>12/06/2018</td>
                                        <td>3500.00 DZD</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>French</td>
                                        <td>12/07/2018</td>
                                        <td>3000.00 DZD</td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>French</td>
                                        <td>12/08/2018</td>
                                        <td>2500.00 DZD</td>
                                    </tr>

                                    </tbody>
                                </table>

                            </form>
                        </div>
                        <!-- /.box-body -->

                    </div>

                </div>
            </div>
            <!-- /.row -->
            <!-- The Modal -->




        </section>








        <!-- /.content -->
</div>
    <!-- /.content-wrapper -->

    <!-- Modal -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Add module</h4>
                </div>
                <form role="form">
                    <div class="modal-body">

                        <div id="printableArea">

                            <div id="module" class="form-group">
                                <label>Module</label>

                                    <select  class="form-control select2" style="width: 100%;" >
                                        <option onchange="isSelected(this)">module 1</option>
                                        <option onchange="isSelected(this)">module 2</option>
                                        <option onchange="isSelected(this)">module 3</option>
                                        <option onchange="isSelected(this)">module 4</option>
                                        <option onchange="isSelected(this)">module 5</option>
                                        <option onchange="isSelected(this)">module 6</option>
                                        <option onchange="isSelected(this)">module 7</option>

                                    </select>

                            </div>

                            <!-- radio -->
                            <div class="form-group">
                                <div class="radio">
                                    <label>
                                        <!--  <input type="radio" name="r3"  onclick="disable()">Free-->
                                        <input type="radio" name="r3" onclick="isChecked(this)">Free
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <!--   <input type="radio" name="r3" onclick="enable()">Payee-->
                                        <input type="radio" name="r3" onclick="isChecked(this)">Payee
                                    </label>
                                </div>

                            </div>

                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>Total (without discount) :</b> <span class=" btn bg-purple pull-right"> 55 500.00 $</span>
                                </li>
                                <li class="list-group-item">
                                    <div id="discount">

                                        <b>Discount : </b>
                                        <input type="number" class="col-xs-2 btn bg-navy pull-right"
                                               onchange="this.setAttribute('value', this.value)" value="">

                                        <!--<a class="pull-right">5 500.00 $</a>-->
                                    </div>

                                </li>
                                <li class="list-group-item">
                                    <b>Payed : </b> <span class="btn bg-teal pull-right">50 000.00 $</span>
                                </li>
                            </ul>

                        </div>


                    </div>

                    <div class="modal-footer">
                        <div>
                            <a  target="_blank" class="btn btn-default pull-right" onclick="printDiv('printableArea')" ><i class="fa fa-print"></i> Submit</a>
                            <!--  <a target="_blank" class="btn btn-default pull-right"><i class="fa fa-print"></i> Submit</a>-->

                         </div>
                         <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Close</button>
                     </div>

                 </form>
             </div>

         </div>
     </div>

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
