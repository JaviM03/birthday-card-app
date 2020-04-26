<%-- 
    Document   : edit-referral
    Created on : 19/04/2020, 07:21:27 PM
    Author     : HP PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Language" content="en">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Christmas Card App</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
        <meta name="msapplication-tap-highlight" content="no">
        <link rel="stylesheet" href="<c:url value="/resources/dashboard.css" />">   
        <style>
            .hamburger-box .hamburger-inner,
            .hamburger-box .hamburger-inner::before,
            .hamburger-box .hamburger-inner::after {
                background-color: white !important;
            }

            .hamburger--emphatic.is-active .hamburger-inner::before {
                background-color: white !important;
            }

            .hamburger--emphatic.is-active .hamburger-inner::after {
                background-color: white !important;
            }

            .btn-add {
                background-color:blue;
            }
            .fas .fa-plus :hover{
                background-color: transparent !important;
            }

            svg{
                background-color: transparent !important;
            }

            .modal-body-long{
                max-height: calc(100vh - 200px);
                overflow-y: auto;
            }

        </style>
        <script src="<c:url value="/resources/moment.min.js"/>"></script>
        <script src="https://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/jquery-ui-1.12.1.custom/jquery-ui.min.js"/>"></script>
        <link href="<c:url value="/resources/jquery-ui-1.12.1.custom/jquery-ui.min.css"/>" rel="stylesheet"/>
        <script src="//geodata.solutions/includes/countrystatecity.js"></script>
    </head>
    <body>

        <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
            <div class="app-header header-shadow" style="background-color: #0062cc; color: white;">
                <div class="app-header__logo">
                    <div class="logo-src" style="color:white; text-align: right; font-size: 24px; margin-bottom: 20px">Logo</div>
                    <div class="header__pane ml-auto">
                        <div>
                            <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                                <span class="hamburger-box" >
                                    <span class="hamburger-inner" ></span>
                                </span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="app-header__mobile-menu">
                    <div>
                        <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
                <div class="app-header__menu">
                    <span>
                        <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                            <span class="btn-icon-wrapper">
                                <i class="fa fa-ellipsis-v fa-w-6"></i>
                            </span>
                        </button>
                    </span>
                </div>    <div class="app-header__content">
                    <div class="app-header-left">
                    </div>
                    <div class="app-header-right">
                        <div class="header-btn-lg pr-0">
                            <div class="widget-content p-0">
                                <div class="widget-content-wrapper">
                                    <div class="widget-content-left">
                                        <div class="btn-group">
                                            <img width="42" class="rounded-circle" src="<c:url value="/resources/img/default-profile2.jpg"/>" alt="">
                                        </div>
                                    </div>
                                    <div class="widget-content-left  ml-3 header-user-info">
                                        <div class="widget-heading">
                                            ${username}
                                        </div>
                                        <div class="widget-subheading">
                                            Free User
                                        </div>
                                    </div>
                                    <div class="widget-content-right header-user-info ml-3">
                                    </div>
                                </div>
                            </div>
                        </div>        </div>
                </div>
            </div>               <div class="app-main">
                <div class="app-sidebar sidebar-shadow">
                    <div class="app-header__logo">
                        <div class="logo-src"></div>
                        <div class="header__pane ml-auto">
                            <div>
                                <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                                    <span class="hamburger-box">
                                        <span class="hamburger-inner"></span>
                                    </span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="app-header__mobile-menu">
                        <div>
                            <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                            </button>
                        </div>
                    </div>
                    <div class="app-header__menu">
                        <span>
                            <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                                <span class="btn-icon-wrapper">
                                    <i class="fa fa-ellipsis-v fa-w-6"></i>
                                </span>
                            </button>
                        </span>
                    </div>    <div class="scrollbar-sidebar">
                        <div class="app-sidebar__inner">
                            <ul class="vertical-nav-menu">
                                <li class="app-sidebar__heading">My Account</li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/dashboard" class="">
                                        <i class="metismenu-icon pe-7s-id"></i>
                                        Friend List
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/logout" class="">
                                        <i class="metismenu-icon pe-7s-back"></i>
                                        Logout
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div> 
                <div class="app-main__outer">
                    <div class="app-main__inner">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="main-card mb-3 card">
                                    <div class="card-header">Editing Referred Information
                                       <!-- <p style="color: red">Please note that saving the changes will override all previous information.</p></div> -->
                                    <form method="POST" action="${pageContext.request.contextPath}/update/referral/input">
                                        <div class="row mt-4 mb-5">
                                            <div class="col-lg-1"></div> 
                                            <div class="col-sm-9 col-md-7 col-lg-4 col-11 mx-auto">
                                                <div class="form-label-group">
                                                    <label for="firstName">First Name <font color="red">*</font></label>
                                                    <input type="text" id="firstName" class="form-control" placeholder="First Name" name="firstName" value="${refOccasion.friendFirstName}" required>
                                                </div>
                                                <div class="form-label-group">
                                                    <label for="lastName">Last Name</label>
                                                    <input type="text" id="lastName" class="form-control" placeholder="Last Name" value="${refOccasion.friendLastName}" name="lastName">
                                                </div>
                                                <div class="form-label-group">
                                                    <label for="emailAddress">Email Address <font color="red">*</font></label>
                                                    <input type="text" id="emailAddress" class="form-control" placeholder="Email Address" value="${refOccasion.email}" name="emailAddress" required>
                                                </div>
                                                <div class="form-label-group">
                                                    <label for="occasion">Occasion <font color="red">*</font></label>
                                                    <input type="text" id="occasion" class="form-control" placeholder="Occasion" value="${refOccasion.occasion}" name="occasion" required>
                                                </div>
                                                <div class="form-label-group ">
                                                    <label for="occasionDate">Occasion Date <font color="red">*</font></label>
                                                    <input type="date" id="occasionDate" class="form-control" placeholder="Occasion Date" name="occasionDate"
                                                           value="<fmt:formatDate value="${refOccasion.occasionDate.time}" pattern="yyyy-MM-dd" type="date" />" required >
                                                </div>
                                                <input type="hidden" name="referredOccasionId" value="${refOccasion.referredOccasionId}">
                                            </div>
                                            <div class="col-sm-9 col-md-7 col-lg-4 col-11 mx-auto">
                                                <label for="countryId">Country</label>
                                                <select name="country" class="countries form-control" id="countryId">
                                                    <option value="">Select Country</option>
                                                </select>
                                                <label for="stateId">State</label>
                                                <select name="state" class="states form-control" id="stateId">
                                                    <option value="">Select State</option>
                                                </select>
                                                <label for="cityId">City</label>
                                                <select name="city" class="cities form-control" id="cityId">
                                                    <option value="">Select City</option>
                                                </select>
                                                <div class="form-label-group">
                                                    <label for="addressline1">Address Line 1</label>
                                                    <input type="text" id="addressline1" class="form-control" placeholder="Address Line 1" value="${refOccasion.addressLine1}" name="addressLine1">
                                                </div>
                                                <div class="form-label-group">
                                                    <label for="addressLine2">Address Line 2</label>
                                                    <input type="text" id="addressLine2" class="form-control" placeholder="Address Line 2" value="${refOccasion.addressLine2}" name="addressLine2">
                                                </div>
                                                <div class="form-label-group">
                                                    <label for="zipCode">Zip Code ${refOccasion.zipCode!=null?'<font color="red">*</font>':""}</label>
                                                    <input type="text" id="zipCode" class="form-control" placeholder="Zip Code" value="${refOccasion.zipCode}" name="zipCode" ${refOccasion.zipCode!=null?"required":""}>
                                                </div>
                                            </div>
                                            <div class="col-lg-1"></div>
                                        </div>
                                        <div class="text-center mb-5 mt-3">
                                            <button type="submit" class="btn btn-outline-success btn-lg" style="padding-top:10px;padding-bottom:10px;padding-left:30px;padding-right:30px;font-size: 18px">Save</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="app-wrapper-footer">
                        <div class="app-footer">
                            <div class="app-footer__inner">
                                <div class="app-footer-left">
                                    <!--<ul class="nav">
                                        <li class="nav-item">
                                            <a href="javascript:void(0);" class="nav-link">
                                                Footer Link 1
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a href="javascript:void(0);" class="nav-link">
                                                Footer Link 2
                                            </a>
                                        </li>
                                    </ul>-->
                                </div>
                                <div class="app-footer-right">
                                    <!--<ul class="nav">
                                        <li class="nav-item">
                                            <a href="javascript:void(0);" class="nav-link">
                                                Footer Link 3
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a href="javascript:void(0);" class="nav-link">
                                                <div class="badge badge-success mr-1 ml-0">
                                                    <small>NEW</small>
                                                </div>
                                                Footer Link 4
                                            </a>
                                        </li>
                                    </ul>-->
                                </div>
                            </div>
                        </div>
                    </div>    
                </div>
            </div>
        </div>                                      
        <script src="<c:url value="/resources/font-awesome/js/all.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/dashboard.js"/>"></script>
    </body>
</html>