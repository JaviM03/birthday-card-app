<%-- 
    Document   : referralLandingPage
    Created on : 28/03/2020, 09:30:06 PM
    Author     : HP PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Info</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/resources/main.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/tel-input/build/css/intlTelInput.css" />">
        <style type="text/css">
            .input-phone {
                float:right; 
                width:345px;
                height: 50px;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;    
                box-sizing: border-box; 
            }
            @media only screen and (max-width: 600px) {
                .input-phone{
                    width:285px;
                }
            }
            @media only screen and (min-width: 992px) {
                input-phone{
                }
            }
        </style>
        <style type="text/css">

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

            body {
                background: #AAABBC;
                background: linear-gradient(to right, #AAABBC, #8d8fa6);
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
        <script src="<c:url value="/resources/font-awesome/js/all.js"/>"></script>
        <div class="pt-2 pb-2 pl-3 color-hover" style="color:black;"><a href="${pageContext.request.contextPath}" style="color:black; display: flex; align-items: center;"><i class="fas fa-arrow-left fa-2x pr-1" style="color:lightgrey;"></i>Go to Index</a></div>
        <div class="container">
                <div class="row card card-signin mb-5 form-signin">
                    <div class="mx-auto card-title">Welcome!</div>
                    <div class="text-center">Please log in into your account to fill out your info.</div>
                    <div class="row justify-content-md-center ">
                        <div class="col-lg-4 mt-4 row align-items-center">
                            <div class="col-2 col-sm-4 col-md-2"></div>
                            <div class="col-8 col-sm-4 col-md-8 ">
                                <a href="${pageContext.request.contextPath}/login"><button class="btn btn-lg btn-primary btn-block">Log In</button></a>
                            </div>
                            <div class="col-2 col-sm-4 col-md-2"></div>
                        </div>

                        <div class="col-lg-4 mt-4 row pb-4">
                            <div class="row col-12 pb-3">
                                <div class="col-2 col-lg-1"></div>
                                <div class="col-8 col-lg-10 text-center" style="color:#696969">If you don't have an account you can go ahead and create one:</div>
                                <div class="col-2 col-lg-1"></div>
                            </div>
                            <div class="row col-12 pt-3 pb-4">
                                <div class="col-2 col-sm-4 col-md-2"></div>
                                <div class="col-8 col-sm-4 col-md-8 ">
                                    <a href="${pageContext.request.contextPath}/signup"><button class="btn btn-lg btn-primary btn-block">Register</button></a>
                                </div>
                                <div class="col-2 col-sm-4 col-md-2"></div>
                            </div>
                        </div>
                    </div>
                                
                </div>
        </div>
        <script src="https://code.jquery.com/jquery-latest.min.js"></script>
        <script src="<c:url value="/resources/tel-input/build/js/intlTelInput-jquery.min.js"/>"></script> 
        <script src="<c:url value="/resources/tel-input/build/js/intlTelInput.js"/>"></script>
    </body>
</html>