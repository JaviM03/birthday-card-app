<%-- 
    Document   : password-reset
    Created on : 22/03/2020, 11:46:45 AM
    Author     : HP PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password</title>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="<c:url value="/resources/main.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/util.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap.css" />">
    </head>
    <body>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/font-awesome/js/all.js"/>"></script>
        
        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-dark primary fixed-top">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Logo</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Home                               
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- !Navigation Bar -->

        <div class="limiter" >
            <div class="container-login100" >
                <div class="wrap-login100 p-l-55 p-r-55 p-t-40 p-b-50">
                    <form class="login100-form validate-form" method="POST" action="${pageContext.request.contextPath}/reset">
                        <span class="login100-form-title p-b-20" style="font-family: 'Open Sans', sans-serif;">
                            Password Reset
                        </span>
                        <div class="text-center p-b-30">
                            <strong>Please type in your new password</strong>
                        </div>
                        
                        <c:if test="${failedPass!=null}">
                            <div class="alert alert-warning" role="alert">
                                <div class="text-center">Passwords must match.</div> 
                            </div>
                        </c:if>
                        
                        <div class="wrap-input100">
                            <input class="input100" type="password" name="passwordNew" id="inputPassword" placeholder="New Password" style="font-family: 'Open Sans', sans-serif;" required="true">
                            <span class="focus-input100-1"></span>
                            <span class="focus-input100-2"></span>
                        </div>
                        
                        <div class="wrap-input100">
                            <input class="input100" type="password" name="passwordConfirm" id="inputPasswordConfirm" placeholder="Re-type New password" style="font-family: 'Open Sans', sans-serif;" required="true">
                            <span class="focus-input100-1"></span>
                            <span class="focus-input100-2"></span>
                        </div>
                        
                        <input type="hidden" class="form-control form-control-user" name="tokenId" id="theToken" value="${resetToken}">

                        <div class="container-login100-form-btn m-t-40">
                            <button class="login100-form-btn" style="font-family: 'Open Sans', sans-serif;">
                                Reset Password
                            </button>
                        </div>
                        
                        ${response}

                        <!--
                        <div class="text-center p-t-45 p-b-4" style="font-family: 'Open Sans', sans-serif;">
                            <a href="#" class="txt2 hov1" style="font-family: 'Open Sans', sans-serif;">Forgot Password?</a>
                        </div>
                        -->                       
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

