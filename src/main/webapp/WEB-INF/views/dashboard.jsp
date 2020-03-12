<%-- 
    Document   : dashboard
    Created on : 21/01/2020, 08:28:12 PM
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
            .hamburger-inner,
            .hamburger-inner::before,
            .hamburger-inner::after{
                background-color: white;
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

        </style>
        <link href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>


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
                                            <a data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="p-0 btn">
                                                <img width="42" class="rounded-circle" src="<c:url value="/resources/imgs/default-profile2.jpg"/>" alt="">
                                                <i class="fa fa-angle-down ml-2 opacity-8"></i>
                                            </a>
                                            <div tabindex="-1" role="menu" aria-hidden="true" class="dropdown-menu dropdown-menu-right">
                                                <button type="button" tabindex="0" class="dropdown-item">User Account</button>
                                                <button type="button" tabindex="0" class="dropdown-item">Settings</button>
                                                <h6 tabindex="-1" class="dropdown-header">Header</h6>
                                                <button type="button" tabindex="0" class="dropdown-item">Actions</button>
                                                <div tabindex="-1" class="dropdown-divider"></div>
                                                <button type="button" tabindex="0" class="dropdown-item">Dividers</button>
                                            </div>
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
                                        <button type="button" class="btn-shadow p-1 btn btn-primary btn-sm show-toastr-example">
                                            <i class="fa text-white fa-calendar pr-1 pl-1"></i>
                                        </button>
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
                                    <a href="${pageContext.request.contextPath}/dashboard" class="mm-active">
                                        <i class="metismenu-icon pe-7s-id"></i>
                                        Friend List
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>    <div class="app-main__outer">
                    <div class="app-main__inner">


                        <div class="row">
                            <div class="col-md-12">
                                <div class="main-card mb-3 card">
                                    <div class="card-header">My Contacts
                                        <button class="btn-wide btn btn-secondary btn-add ml-4" style="" data-toggle="modal" data-target="#addContactModal"><i class="fas fa-plus"></i></button>
                                        <div class="btn-actions-pane-right">
                                            <div role="group" class="btn-group-sm btn-group">
                                                <a href="${pageContext.request.contextPath}/dashboard?dateRange=weekly"><button class="${dateRange=='weekly'?'active':''} btn btn-focus">Last Week</button></a>
                                                <a href="${pageContext.request.contextPath}/dashboard?dateRange=monthly"><button class="${dateRange=='monthly'?'active':''} btn btn-focus">All Month</button></a>
                                                <a href="${pageContext.request.contextPath}/dashboard?dateRange=none"><button class="${dateRange=='none'?'active':''} btn btn-focus">All Time</button></a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="align-middle mb-0 table table-borderless table-striped table-hover">
                                            <thead>
                                                <tr>                                               
                                                    <th><div class="pl-4">Name</div></th>                                               
                                                    <th class="text-center">Date</th>
                                                    <th class="text-center">Occasion</th>
                                                    <th class="text-center">City</th>
                                                    <th class="text-center">Status</th>
                                                    <th class="text-center"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${friends}" var="friend" varStatus="index">
                                                    <tr>                                               

                                                        <td class="text-muted">
                                                            <div class="widget-content p-0">
                                                                <div class="widget-content-wrapper">
                                                                    <div class="widget-content-left mr-3">
                                                                        <div class="widget-content-left">
                                                                            <img width="40" class="rounded-circle" src="<c:url value="/resources/imgs/default-profile.jpg"/>" alt="">
                                                                        </div>
                                                                    </div>
                                                                    <div class="widget-content-left flex2">
                                                                        <div class="widget-heading">${friend.friend.firstName} ${friend.friend.lastName}</div>
                                                                        <div class="widget-subheading opacity-7">${friend.relationship?friend.relationship:'Aquintance'}</div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="text-center"><fmt:formatDate type="date" dateStyle="short" value="${friend.occasionDate.time}"/></td>
                                                        <td class="text-center">${friend.occasion}</td>
                                                        <td class="text-center">
                                                            City
                                                        </td>
                                                        <td class="text-center"><div class="badge ${friend.friendIsRegistered?'badge-success':'badge-warning'}">${friend.friendIsRegistered?'Reday':'Pending'}</div></td>
                                                        <td class="text-center">
                                                            <button type="button" id="PopoverCustomT-1" class="btn btn-primary btn-sm">Details</button>
                                                        </td>

                                                    </tr>                                                    
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="d-block text-center card-footer">
                                        <nav aria-label="...">
                                            <ul class="pagination justify-content-center">
                                                <li class="page-item ${firstPage?'disabled':''}">
                                                    <a class="page-link" href="${lastPageUrl}" tabindex="-1">Previous</a>
                                                </li>
                                                <li class="page-item ${firstPage?'active':''}"><a class="page-link" href="${firstPageUrl}">${firstPageNumber!=null?firstPageNumber:1}</a></li>
                                                <c:if test="${secondPageExist}">
                                                    <li class="page-item ${secondPage?'active':''}">
                                                        <a class="page-link" href="${secondPageUrl}">${secondPageNumber}</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${thirdPageExist}">
                                                    <li class="page-item ${thirdPage?'active':''}"><a class="page-link" href="${thirdPageUrl}">${thirdPageNumber}</a></li>
                                                </c:if>
                                                <li class="page-item ${nextPageExist?'':'disabled'}">
                                                    <a class="page-link" href="${nextPageUrl}">Next</a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                    <div class="app-wrapper-footer">
                        <div class="app-footer">
                            <div class="app-footer__inner">
                                <div class="app-footer-left">
                                    <ul class="nav">
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
                                    </ul>
                                </div>
                                <div class="app-footer-right">
                                    <ul class="nav">
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
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>    </div>
            </div>
        </div>
        <!-- Modals Section -->
        <!-- Add Contact Modal -->
        <div class="modal fade" id="addContactModal" tabindex="-1" role="dialog" aria-labelledby="addContactModalLable" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Add Contact</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="POST" action="${pageContext.request.contextPath}/addOccasion">
                        <div class="modal-body">
                            <div class="form-group">

                                <label for="firstNameModal">First Name <font color="red">*</font></label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="First Name" id="firstNameModal" name="firstName" maxLength="12" required/>
                                <label for="lastNameModal">Last Name</label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Last Name" id="lastNameModal" name="lastName" maxLength="12"/>
                                <label for="emailAddressModal">Email Address <font color="red">*</font></label>
                                <input type="email" autocomplete="off" class="form-control" placeholder="Email Address" id="emailAddressModal" name="email" required/>
                                <label for="dateModal">Date <font color="red">*</font></label>
                                <input type="date" class="form-control" id="dateModal" name="occasionDate"/>
                                <label for="occasionModal">Occasion <font color="red">*</font></label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Occasion" id="ocassionModal" maxLength="12" name="occasion" required/> 
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
                        <!-- Add Contact Modal -->
        <div class="modal fade" id="contactDetailModal" tabindex="-1" role="dialog" aria-labelledby="addContactModalLable" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="contactDetailModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="POST" action="${pageContext.request.contextPath}/addOccasion">
                        <div class="modal-body">
                            <div class="form-group">
                                <div>
                                    <div>Referred Date: </div>
                                    <div id="contactDetailReferredDate"></div>
                                </div>     
                                <div>
                                    <div>Occasion Date: </div>
                                    <div id="contactDetailOccasionDate"></div>
                                </div>
                                <div>
                                    <div>Address: </div>
                                    <div id="contactDetailAddress"></div>
                                </div>
                                <div>
                                    <div>Email: </div>
                                    <div id="contactDetailEmail"></div>
                                </div>
                                <div>
                                    <div>Las email was sent on </div>
                                    <div id="contactDetailLasEmail"></div>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>

                </div>
            </div>
        </div>
                        <script>
                            
                        </script>
        <script src="<c:url value="/resources/font-awesome/js/all.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/dashboard.js"/>"></script>
    </body>
</html>
