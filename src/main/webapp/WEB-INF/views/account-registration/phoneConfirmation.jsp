<%-- 
    Document   : register
    Created on : 16/01/2020, 01:34:47 PM
    Author     : HP PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <div class="pt-2 pb-2 pl-3 color-hover" style="color:black;"><a href="${pageContext.request.contextPath}/signup" style="color:black; display: flex; align-items: center;"><i class="fas fa-arrow-left fa-2x pr-1" style="color:lightgrey;"></i>Go Back</a></div>
        <div class="container">
            <div class="alert alert-danger" role="alert" style="display:${failedAttempt?"":"none"}">
                Wrong confirmation number. Please try again.
            </div>
            <div class="alert alert-success" role="alert" style="display:${newAttempt?"":"none"}">
                A new confirmation code has been sent to your phone number.
            </div>
            <form class="form-signin" method="POST" action="${pageContext.request.contextPath}/phone-confirmation">
                <div class="row card card-signin mb-5 ml-2 mr-2" >
                    <div class="col-lg-6 col-10 mx-auto text-center">
                    <div class="pt-4" style="font-size: large;">We've sent a confirmation SMS to your phone number.</div>
                    <div class="mb-4 mt-3" style="font-size: large;"><strong>Please provide the confirmation code to continue:</strong></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-9 col-md-7 col-lg-4 col-11 mx-auto">
                            <div class="mb-2">
                                <div class="form-group">
                                    <input type="text" id="inputCode" class="form-control" placeholder="SMS Code" name="code" required autofocus>
                                </div>
                            </div>
                        </div>
                    </div>
                        <div class="col-lg-3 col-sm-5 col-8 mx-auto text-center mt-2">
                            <div><a href="${pageContext.request.contextPath}/user-signup?resend=true">Resend code</a></div>
                            <div class="mt-2 mb-3"><a href="${pageContext.request.contextPath}/change-number">Change number</a></div>
                        </div>
                    <div class="col-lg-2 mx-auto mt-3 mb-4">
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Confirm</button>
                    </div>
                </div>
            </form>
        </div>

        <script>
        </script>
    </body>
</html>
