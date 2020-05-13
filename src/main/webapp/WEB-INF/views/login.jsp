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
        <!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
        <title>My Digital Address Book | Login</title>
        <link rel="shortcut icon" href="<c:url value="/resources/favicon.ico" />" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="<c:url value="/resources/main.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/util.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/tel-input/build/css/intlTelInput.css" />">
        <style type="text/css">
            
            @media only screen and (max-width: 767px) {
               .md-and-higher-only{
                  display:none ;
               }
           }
           
           @media only screen and (max-width: 767px) {
               .md-and-higher-only{
                  display:none ;
               }
           }
           @media only screen and (min-width: 767px) {
               .md-and-smaller-only{
                  display:none ;
               }
           }
           
           .input-phone {
                float:right; 
                width:350px;
                height: 50px;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;    
                box-sizing: border-box;
                margin-bottom: 40px;
            }
           @media only screen and (max-width: 960px) {
               .input-phone{
                  width:200px
               }
               
           }
            .intl-tel-input {
                display: table-cell;
              }
              .intl-tel-input .selected-flag {
                z-index: 4;
              }
              .intl-tel-input .country-list {
                z-index: 5;
              }
              .input-group .intl-tel-input .form-control {
                border-top-left-radius: 4px;
                border-top-right-radius: 0;
                border-bottom-left-radius: 4px;
                border-bottom-right-radius: 0;
              }
            :root {
                --input-padding-x: 1.5rem;
                --input-padding-y: .75rem;
            }

            .card-signin {
                border: 0;
                border-radius: 1rem;
                box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
            }

            .card-signin .card-title {
                margin-bottom: 2rem;
                font-weight: 300;
                font-size: 1.5rem;
            }

            .card-signin .card-body {
                padding: 2rem;
            }

            .form-signin {
                width: 100%;
            }

            .form-signin .btn {
                font-size: 80%;
                border-radius: 5rem;
                letter-spacing: .1rem;
                font-weight: bold;
                padding: 1rem;
                transition: all 0.2s;
            }

            .form-label-group {
                position: relative;
                margin-bottom: 1rem;
            }

            .form-label-group input {
                height: auto;
                border-radius: 2rem;
            }

            .form-label-group>input,
            .form-label-group>label {
                padding: var(--input-padding-y) var(--input-padding-x);
            }

            .form-label-group>label {
                position: absolute;
                top: 0;
                left: 0;
                display: block;
                width: 100%;
                margin-bottom: 0;
                /* Override default `<label>` margin */
                line-height: 1.5;
                color: #495057;
                border: 1px solid transparent;
                border-radius: .25rem;
                transition: all .1s ease-in-out;
            }

            .form-label-group input::-webkit-input-placeholder {
                color: transparent;
            }

            .form-label-group input:-ms-input-placeholder {
                color: transparent;
            }

            .form-label-group input::-ms-input-placeholder {
                color: transparent;
            }

            .form-label-group input::-moz-placeholder {
                color: transparent;
            }

            .form-label-group input::placeholder {
                color: transparent;
            }

            .form-label-group input:not(:placeholder-shown) {
                padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
                padding-bottom: calc(var(--input-padding-y) / 3);
            }

            .form-label-group input:not(:placeholder-shown)~label {
                padding-top: calc(var(--input-padding-y) / 3);
                padding-bottom: calc(var(--input-padding-y) / 3);
                font-size: 12px;
                color: #777;
            }

            .btn-google {
                color: white;
                background-color: #ea4335;
            }

            .btn-facebook {
                color: white;
                background-color: #3b5998;
            }

            .color-hover:hover svg{
                color: grey !important;
            }

            /* Fallback for Edge
            -------------------------------------------------- */

            @supports (-ms-ime-align: auto) {
                .form-label-group>label {
                    display: none;
                }
                .form-label-group input::-ms-input-placeholder {
                    color: #777;
                }
            }

            /* Fallback for IE
            -------------------------------------------------- */

            @media all and (-ms-high-contrast: none),
            (-ms-high-contrast: active) {
                .form-label-group>label {
                    display: none;
                }
                .form-label-group input:-ms-input-placeholder {
                    color: #777;
                }
            }
        </style>  
    </head>
    <body>
        <script src="https://code.jquery.com/jquery-latest.min.js"></script>
        <script src="<c:url value="/resources/bootstrap.min.js"/>"></script>
        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg navbar-dark primary fixed-top">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">
                    <!--<img src="<c:url value="/resources/img/Logo.jpg"/>" class="logo-img">-->
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Home                               
                            </a>
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
                <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50" style="width:80%">
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
                    <c:if test="${emailOrPhoneTaken}">
                        <div class="alert alert-danger" role="alert">
                            <div class="text-center">Either the email or phone you submited has already being taken. Please try again.</div> 
                        </div>
                    </c:if>
                    <c:if test="${successfulReset}">
                        <div class="alert alert-success" role="alert">
                            <div class="text-center">Your password has being succesfuly reset.</div> 
                        </div>
                    </c:if>
                    <div class="row justify-content-center">
                        <!--------------------- Container sign up ------------------------->
                        <div class="row col-md-6">
                            
                            <div class="col-md-12">
                                <span class="login100-form-title p-b-33" style="font-family: 'Open Sans', sans-serif;">
                                    Register
                                </span>
                                
                            </div>
                            
                            <div class="col-md-10 col-12 mx-auto">
                                <form id="form-register" method="POST" action="${pageContext.request.contextPath}/user-signup">
                                <div class="form-label-group ">
                                        <input type="text" id="inputFirstName" class="form-control" placeholder="First Name" name="firstName" required >
                                        <label for="inputFirstName">First Name</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input type="text" id="inputLastName" class="form-control" placeholder="Last Name" name="lastName"  required>
                                        <label for="inputLastName">Last Name</label>
                                    </div>
                                
                                <div class="input-group justify-content-center" style="margin-bottom:20px">
                                        <input type="tel" class="form-control input-phone" id="phone" placeholder="Phone Number"   required>
                                        <input type="hidden" id="phoneForm" name="phoneNumber"  placeholder="Phone Number"> 
                                    </div>
                                    
                                <div class="form-label-group">
                                        <input type="email" id="inputEmail" class="form-control" placeholder="Email" name="email" required>
                                        <label for="inputEmail">Email</label>
                                    </div>
                                <div class="form-label-group">
                                        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
                                        <label for="inputPassword">Password</label>
                                    </div>
                                
                                <div class="container-login100-form-btn m-t-40">
                                        <button class="login100-form-btn" style="font-family: 'Open Sans', sans-serif;">
                                            Register
                                        </button>
                                    
                                    </div>
                                </form>
                            </div>
                            
                        </div>
                        <div style="border-left:1px solid #a7a7a7;height:auto; " class="md-and-higher-only" ></div>
                        <hr style="width:80%" class="md-and-smaller-only mb-3 mt-5">
                        <!--------------------- Container login -------------------------->
                        <div class="row col-md-6 col-12 align-items-start justify-content-center">
                            
                                <div class="col-12">
                                    <span class="login100-form-title p-b-33" style="font-family: 'Open Sans', sans-serif;">
                                        Login
                                    </span>
                                </div>
                            <form class="login100-form validate-form" method="POST" action="${pageContext.request.contextPath}/attempt-login">
                                <div class="row col-12 align-items-center justify-content-center mx-auto" >
                                <div class="col-12 col-md-11 text-center mx-auto">
                                    <div class="wrap-input100" style="margin-bottom:40px">
                                        <input class="input100" type="email" name="email" placeholder="Email" style="font-family: 'Open Sans', sans-serif;" required="">
                                        <span class="focus-input100-1"></span>
                                        <span class="focus-input100-2"></span>
                                    </div>

                                    <div class="wrap-input100 " data-validate="Password is required">
                                        <input class="input100" type="password" name="password" placeholder="Password" style="font-family: 'Open Sans', sans-serif;" required>
                                        <span class="focus-input100-1"></span>
                                        <span class="focus-input100-2"></span>
                                    </div>

                                    <div class="container-login100-form-btn m-t-40">
                                        <button class="login100-form-btn" style="font-family: 'Open Sans', sans-serif;">
                                            Sign in
                                        </button>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="text-center pt-5">
                                        <div>    
                                            <span class="txt1" style="font-family: 'Open Sans', sans-serif;">
                                                Forgot your password?
                                            </span>

                                            <a href="${pageContext.request.contextPath}/forgot-password" class="txt2 hov1" style="font-family: 'Open Sans', sans-serif;">
                                                Click here
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                </div>
                            </form>
                        </div>
                    </div>



                    <!--
                    <div class="text-center p-t-45 p-b-4" style="font-family: 'Open Sans', sans-serif;">
                        <a href="#" class="txt2 hov1" style="font-family: 'Open Sans', sans-serif;">Forgot Password?</a>
                    </div>
                    -->
                </div>
            </div>
        </div>                         
        <script src="<c:url value="/resources/tel-input/build/js/intlTelInput-jquery.min.js"/>"></script> 
        <script src="<c:url value="/resources/tel-input/build/js/intlTelInput.js"/>"></script>
        
        <script>
            
             var phoneInput = document.querySelector("#phone");
             var iti = window.intlTelInput(phoneInput);
               $("#form-register").submit(function(e){
                    var phoneForm = document.querySelector("#phoneForm");
                    phoneForm.value = iti.getSelectedCountryData().dialCode + phoneInput.value;
                })
                
        </script>
    </body>
</html>
