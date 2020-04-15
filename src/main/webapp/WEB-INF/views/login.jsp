<%-- 
    Document   : login
    Created on : 16/01/2020, 11:30:58 AM
    Author     : HP PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
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
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">
                                Login
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- !Navigation Bar -->

        <div class="limiter" >
            <div class="container-login100" >
                <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
                    <form class="login100-form validate-form" method="POST" action="${pageContext.request.contextPath}/attempt-login">
                        <span class="login100-form-title p-b-33" style="font-family: 'Open Sans', sans-serif;">
                            Account Login
                        </span>
                        <c:if test="${failedLogin}">
                            <div class="alert alert-warning" role="alert">
                                <div class="text-center">Wrong email or password.</div> 
                            </div>
                        </c:if>
                        <c:if test="${referalCode}">
                            <div class="alert alert-warning" role="alert">
                                <div class="text-center">Sorry, your referral token does not match with our existing data.</div> 
                            </div>
                        </c:if>
                        <c:if test="${invalidLink}">
                            <div class="alert alert-danger" role="alert">
                                <div class="text-center">Your password link has either expired or has already being used. Please try again.</div> 
                            </div>
                        </c:if>
                        <c:if test="${successfulReset}">
                            <div class="alert alert-success" role="alert">
                                <div class="text-center">Your password has being succesfuly reset.</div> 
                            </div>
                        </c:if>
                        <div class="wrap-input100">
                            <input class="input100" type="email" name="email" placeholder="Email" style="font-family: 'Open Sans', sans-serif;" required="">
                            <span class="focus-input100-1"></span>
                            <span class="focus-input100-2"></span>
                        </div>

                        <div class="wrap-input100 rs1 validate-input" data-validate="Password is required">
                            <input class="input100" type="password" name="password" placeholder="Password" style="font-family: 'Open Sans', sans-serif;">
                            <span class="focus-input100-1"></span>
                            <span class="focus-input100-2"></span>
                        </div>

                        <div class="container-login100-form-btn m-t-40">
                            <button class="login100-form-btn" style="font-family: 'Open Sans', sans-serif;">
                                Sign in
                            </button>
                        </div>

                        <!--
                        <div class="text-center p-t-45 p-b-4" style="font-family: 'Open Sans', sans-serif;">
                            <a href="#" class="txt2 hov1" style="font-family: 'Open Sans', sans-serif;">Forgot Password?</a>
                        </div>
                        -->
                        <div class="text-center pt-5">
                            <div>
                            <span class="txt1" style="font-family: 'Open Sans', sans-serif;">
                                Don't have an account?
                            </span>

                            <a href="${pageContext.request.contextPath}/signup" class="txt2 hov1" style="font-family: 'Open Sans', sans-serif;">
                                Sign up
                            </a>
                            </div>
                                <div>    
                            <span class="txt1" style="font-family: 'Open Sans', sans-serif;">
                                Forgot your password?
                            </span>

                            <a href="${pageContext.request.contextPath}/forgot-password" class="txt2 hov1" style="font-family: 'Open Sans', sans-serif;">
                                Click here
                            </a>
                                </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
