<%-- 
    Document   : referralSuccess
    Created on : 30/03/2020, 01:13:25 AM
    Author     : HP PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Digital Address Book | Successful Info Submit</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="<c:url value="/resources/favicon.ico" />" type="image/x-icon" />
        <link rel="stylesheet" href="<c:url value="/resources/main.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap.css" />">
        <style type="text/css">
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

            .color-hover:hover i{
                color: grey !important;
            }
            
            @keyframes blue-pulse
                {
                  0% {
                    box-shadow: 0 0 0 0px rgba(0, 0, 255, 0.3);
                  }
                  100% {
                    box-shadow: 0 0 0 15px rgba(0, 0, 0, 0);
                  }
                }

                @keyframes shadow-pulse-big
                {
                  0% {
                    box-shadow: 0 0 0 0px rgba(0, 0, 0, 0.1);
                  }
                  100% {
                    box-shadow: 0 0 0 70px rgba(0, 0, 0, 0);
                  }
}
            .pulsing-button{
                  animation: blue-pulse 2s 5;
                }

            /* Fallback for Edge
            -------------------------------------------------- */

            @media screen and (max-width: 991px){
                .mg-sm-size{
                    margin-bottom: 50px !important;
                    margin-top: 30px !important;
                }
            }

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
        <div class="container">
            <div class="row card card-signin mb-5 ml-2 mr-2 mt-5" >
                <div class='mt-5 text-center'> <h2><p class="font-weight-light">Thank you for confirming your information.</p></h2> </div>
                <div class="mt-2 text-center row justify-content-md-center" style="color:#AAABBC">
                    <div class="col-10 mx-auto">
                        <h4><p class="font-weight-light">You can use this service for free too. Simply click on Register below.</p></h4>
                    </div>
                </div>
                <div class="text-center row mb-5 pb-3 mx-auto" >
                    <form method="GET" action="${pageContext.request.contextPath}/referral/register">
                            <input type="hidden" value="${referredOccasionId}" name="id">
                            <button class="btn-primary btn btn-block btn-lg pulsing-button" type="submit">Register</button>
                        </form>
                    <!--<div class="col-lg-4 col-12"></div>
                    <div class="col-lg-4 col-11 mx-auto" >
                        <form method="GET" action="${pageContext.request.contextPath}/referral/register">
                            <input type="hidden" value="${referredOccasionId}" name="id">
                            <button class="btn-primary btn btn-block btn-lg pulsing-button" type="submit">Register</button>
                        </form>
                    </div>
                    <div class="col-lg-4 col-12"></div>-->
                </div>
            </div>
        </div>
    </body>
</html>
