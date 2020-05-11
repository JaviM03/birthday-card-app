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
        <title>My Digital Address Book | Login</title>
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
                    <div class='mt-5 text-center'><h2>Thank you for joining us!</h2></div>
                <div class="mt-2 text-center" style="color:#AAABBC"><h4>Your account has been created. You'll be shortly redirected to the dashboard.</h4></div>
                <div class="text-center mb-5" style='font-size:22px'><a href="${pageContext.request.contextPath}/dashboard">Go now!</a></div>
                </div>
            </div>
        </div>
          <script>
            window.setTimeout(function () {
                window.location.href = '${pageContext.request.contextPath}/dashboard';
            }, 8000);
        </script>                             
    </body>
</html>