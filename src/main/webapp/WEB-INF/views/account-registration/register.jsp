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

            .color-hover:hover .fas{
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
        <!-- This snippet uses Font Awesome 5 Free as a dependency. You can download it at fontawesome.io! -->
        <div class="pt-2 pb-2 pl-3 color-hover" style="color:black;"><a href="${pageContext.request.contextPath}" style="color:black;"><i class="fas fa-arrow-left fa-2x" style="color:lightgrey;"></i> Go Back</a></div>
        <div class="container">
            <form class="form-signin" id="form-register" method="POST" action="${pageContext.request.contextPath}/user-signup">
                <div class="row card card-signin mb-5 ml-2 mr-2" >
                    <div class="mx-auto card-title pt-3">Sign up</div>
                    <div class="row">
                        <div class="col-lg-1"></div>                       
                        <div class="col-sm-9 col-md-7 col-lg-4 col-11 mx-auto">
                            <div>
                                <div class="mb-3">
                                    <div class="form-label-group">
                                        <input type="email" id="inputEmail" class="form-control" placeholder="Email" name="email" required autofocus>
                                        <label for="inputEmail">Email</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
                                        <label for="inputPassword">Password</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input type="password" id="inputPassword2" class="form-control" placeholder="Re-Type Password" name="passwordConfirm" required>
                                        <label for="inputPassword2">Re-Type Password</label>
                                        <div class="text-center" style="color:red; display:none;" id="mismatch-pass-message">Passwords do not match</div>
                                    </div>                                   
                                </div>                           
                            </div>
                        </div>
                        <div class="col-sm-9 col-md-7 col-lg-4 col-11 mx-auto">
                            <div class="mb-5">
                                <div class="">
                                    <div class="form-label-group ">
                                        <input type="text" id="inputFirstName" class="form-control" placeholder="First Name" name="firstName" required >
                                        <label for="inputFirstName">First Name</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input type="text" id="inputLastName" class="form-control" placeholder="Last Name" name="lastName" required>
                                        <label for="inputLastName">Last Name</label>
                                    </div>                                   
                                    <div class="form-label-group">
                                        <input type="text" id="inputNumber" class="form-control" placeholder="Phone Number" pattern="([0-9]{3})-([0-9]{3})-([0-9]{4})" name="phoneNumber" title="Us Phone Number Format" required>
                                        <label for="inputNumber">Phone Number</label> 
                                        <div class="text-center"> Ex. 877-503-0830 </div>
                                    </div>                                    
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1"></div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8 col-sm-8 col-8 mx-auto text-center" id="hr-container">
                            <div><hr></div>
                            Already have an account? <a href="${pageContext.request.contextPath}/login">Log in</a>
                        </div>
                    </div>
                    <div class="col-lg-2 mx-auto mt-4 mb-5">
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign up</button>
                    </div>
                </div>
            </form>
        </div>
        <script>
            document.getElementById('form-register').addEventListener("submit", function (e) {
                 password = document.getElementById('inputPassword');
                confirm_password = document.getElementById('inputPassword2');
                
                if(password.value !== confirm_password.value){
                    confirm_password.className += " is-invalid";
                    passwordMessage = document.getElementById("mismatch-pass-message");
                    passwordMessage.removeAttribute("display");
                    e.preventDefault();
                }
            })
        </script>
    </body>
</html>
