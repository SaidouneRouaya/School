<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 18/07/2019
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Languages school</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords"
          content="Unique Login Form Widget Responsive, Login form web template,Flat Pricing tables,Flat Drop downs  Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- font files  -->
    <link href='fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <link href='fonts.googleapis.com/css?family=Raleway:400,100,200,300,500,600,700,800,900' rel='stylesheet'
          type='text/css'>
    <!-- /font files  -->
    <!-- css files -->
    <link href="../dist/css/animate-custom.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="../dist/css/style.css" rel='stylesheet' type='text/css' media="all"/>
    <!-- /css files -->
</head>
<body>
<div class="header">
    <h1>Profile Login Form</h1>
</div>
<div class="content">
    <section>
        <div id="container_demo">
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div id="wrapper">
                <div id="login" class="animate form">
                    <div class="content-img">
                        <img src="../dist/img/user1-128x128.jpg" alt="img" class="w3l-img">
                    </div>
                    <form action="index.j" autocomplete="on" method="post">
                        <h2>Login</h2>
                        <p>
                            <label for="username" class="uname" data-icon="u"> Your email or username </label>
                            <input id="username" name="username" required="required" type="text"
                                   placeholder="myusername or mymail@mail.com"/>
                        </p>
                        <p>
                            <label for="password" class="youpasswd" data-icon="p"> Your password </label>
                            <input id="password" name="password" required="required" type="password"
                                   placeholder="eg:password123!@#$%"/>
                        </p>
                        <p class="keeplogin">
                            <input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping"/>
                            <label for="loginkeeping">Keep me logged in</label>
                        </p>
                        <p class="login button">
                            <input type="submit" value="Login"/>
                        </p>
                        <!-- <p class="change_link">
                             Not a member yet ?
                             <a href="#toregister" class="to_register">Join us</a>
                         </p>-->
                    </form>
                </div>
                <!-- <div id="register" class="animate form">
                     <div class="content-img">
                         <img src="../dist/img/profile.png" alt="img" class="w3l-img">
                     </div>
                     <form  action="#" autocomplete="on" method="post">
                         <h2> Sign up </h2>
                         <p>
                             <label for="usernamesignup" class="uname" data-icon="u">Your username</label>
                             <input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="mysuperusername690" />
                         </p>
                         <p>
                             <label for="emailsignup" class="youmail" data-icon="e" > Your email</label>
                             <input id="emailsignup" name="emailsignup" required="required" type="email" placeholder="mysupermail@mail.com"/>
                         </p>
                         <p>
                             <label for="passwordsignup" class="youpasswd" data-icon="p">Your password </label>
                             <input id="passwordsignup" name="passwordsignup" required="required" type="password" placeholder="eg:password123!@#$%"/>
                         </p>
                         <p>
                             <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Please confirm your password </label>
                             <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" placeholder="eg:password123!@#$%"/>
                         </p>
                         <p class="signin button">
                             <input type="submit" value="Sign up"/>
                         </p>
                         <p class="change_link">
                             Already a member ?
                             <a href="#tologin" class="to_register">Log in</a>
                         </p>
                     </form>
                 </div>-->
            </div>
        </div>
    </section>
</div>


</body>
</html>