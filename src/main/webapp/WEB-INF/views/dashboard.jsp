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
            @keyframes shadow-pulse
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
                  animation: shadow-pulse 1s 5;
                }

        </style>
        <script src="<c:url value="/resources/moment.min.js"/>"></script>
        <script src="https://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/jquery-ui-1.12.1.custom/jquery-ui.min.js"/>"></script>
        <link href="<c:url value="/resources/jquery-ui-1.12.1.custom/jquery-ui.min.css"/>" rel="stylesheet"/>
        <script src="<c:url value="/resources/papaparse.min.js"/>"></script>
        <script src="//geodata.solutions/includes/countrystatecity.js"></script>
        <script>
            var submitFileFlag = false;
            //Function for asigning sugestions to occasion input on modal
            $(function () {
                var availableTags = [
                    "Birthday",
                    "Wedding",
                    "Birth",
                    "Welcome",
                    "Goodbye",
                    "Christmas"
                ];
                $("#occasionModal").autocomplete({
                    source: availableTags
                });
                $("#occasionModal").autocomplete("option", "appendTo", "#ocassionFormModal")
            });
            
            /*
            $(document).ajaxStop(function () {
                if(submitFileFlag){
                    window.location.reload();
                }
            });*/

            $(document).ready(function () {
                $('#submit-file').on("click", function (e) {
                    submitFileFlag = true;
                    e.preventDefault();
                    $('#files').parse({
                        config: {
                            delimiter: "auto",/*
                            step: function(results, parser) {
                                    console.log("Row data:", results.data);
                                    console.log("Row errors:", results.errors);
                            },*/
                            //complete: insertToDB,
                            complete: function(results, file) {
                                    insertToDB(results);
                                    console.log("Parsing complete:", results, file);
                            },
                        },
                        before: function (file, inputElem)
                        {
                            console.log("Parsing file...", file);
                        },
                        error: function (err, file)
                        {
                            //console.log("ERROR:", err, file);
                        },
                        complete: function ()
                        {
                            console.log("Done with all files");
                        }
                    });
                });

                function insertToDB(results) {
                    
                    
                    var data = results.data;
                    console.log("data: ");
                    console.log(data);
                    console.log(data[0]);
                    //if there is a header row, you start at "header number" (number of row), if not, you start at 0
                    //ajax to w ork with
                    $.ajax({
                        url: '${pageContext.request.contextPath}/addByCSV',
                        type: 'POST',
                        data: {csv: JSON.stringify(data)},
                        success: function () {
                            //document.getElementById("test").innerHTML = "Funciono";
                            //console.log(csv);
                            //window.location.reload();
                        },
                        error: function (xhr, status, error) {
                                                console.log(xhr.responseText);
                                               // document.getElementById("test").innerHTML = "No Funciono";
                        }
                    });

                }
            });

        </script>
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
                                    <a href="${pageContext.request.contextPath}/dashboard" class="mm-active">
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
                        <c:if test="${emailSent}">
                            <div class="alert alert-primary" role="alert">
                                Email sent!
                            </div>
                        </c:if>
                        <c:if test="${userCreated}">
                            <div class="alert alert-success" role="alert">
                                Your referred information has being saved.
                            </div>
                        </c:if>
                        <c:if test="${contactDeleted}">
                            <div class="alert alert-danger" role="alert">
                                Contact Deleted!
                            </div>
                        </c:if>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="main-card mb-3 card">
                                    <div class="card-header">My Contacts
                                        <button class="btn-wide btn btn-secondary btn-add ml-4 pulsing-button" style="" data-toggle="modal" data-target="#addContactModal"><i class="fas fa-plus"></i></button>
                                        <div class="btn-actions-pane-right">
                                            <div role="group" class="btn-group-sm btn-group">
                                                <a href="${pageContext.request.contextPath}/dashboard?dateRange=weekly"><button class="${dateRange=='weekly'?'active':''} btn btn-focus">This Week</button></a>
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
                                                    <th class="text-center" style="width:7%"></th>
                                                    <th class="text-center" style="width:5%"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${referrals}" var="referral" varStatus="index">
                                                    <tr>                                               

                                                        <td class="text-muted">
                                                            <div class="widget-content p-0">
                                                                <div class="widget-content-wrapper">
                                                                    <div class="widget-content-left mr-3">
                                                                        <div class="widget-content-left">
                                                                            <img width="40" class="rounded-circle" src="<c:url value="/resources/img/default-profile.jpg"/>" alt="">
                                                                        </div>
                                                                    </div>
                                                                    <div class="widget-content-left flex2">
                                                                        <div class="widget-heading">${referral.friendFirstName} ${referral.friendLastName}</div>
                                                                        <div class="widget-subheading opacity-7">${referral.relationship?referral.relationship:'Aquintance'}</div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="text-center"><fmt:formatDate type="date" dateStyle="short" value="${referral.occasionDate.time}"/></td>
                                                        <td class="text-center">${referral.occasion}</td>
                                                        <td class="text-center">
                                                            City
                                                        </td>
                                                        <td class="text-center"><div class="badge ${referral.infoHasBeingFilled?'badge-success':'badge-warning'}">${referral.infoHasBeingFilled?'Ready':'Pending'}</div></td>
                                                        <td class="text-center">
                                                            <button type="button" id="PopoverCustomT-1" class="btn btn-primary btn-sm" onclick="detailModal('${referral.friendFirstName} ${referral.friendLastName}',
                                                                            '<fmt:formatDate type="date" dateStyle="short" value="${referral.referredDate.time}"/>', '<fmt:formatDate type="date" dateStyle="short" value="${referral.occasionDate.time}"/>', '${referral.addressLine1}',
                                                                            '${referral.email}', '<fmt:formatDate type="date" dateStyle="short" value="${referral.lastEmailDate.time}"/>', '${referral.referredOccasionId}', '${referral.lastEditedBy}',
                                                                            '<fmt:formatDate type="both" dateStyle="short" value="${referral.lastEditedDate.time}"/>', '${referral.emailCanBeResent}', '${referral.country}',
                                                                                                                                            '${referral.state}','${referral.city}')">Details</button>                                                                            
                                                        </td>
                                                        <td>
                                                            <button type="button" id="deleteRow" class="btn btn-danger btn-sm ml-1" onclick="deleteModal('${referral.referredOccasionId}', '${referral.friendFirstName} ${referral.friendLastName}',
                                                                            '${referral.occasion}', '<fmt:formatDate type="date" dateStyle="short" value="${referral.occasionDate.time}"/>')">&times;</button>
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
                        <h5 class="modal-title" id="exampleModalLabel">Add Referral</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!--modal buttons-->

                    <div class="mt-4 mb-2 row" >
                        <div class="col-sm-12">
                            <div class="text-center">
                                <button class="btn-wide btn btn-secondary btn-add ml-4" style="" data-toggle="modal" data-target="#addContactCSV" data-dismiss="modal">Import (CSV)</button>                            
                                <!--<button class="btn btn-primary btn-sm" style="" data-toggle="modal" data-target="#addContactCSV" data-dismiss="modal">Import (CSV)</button>-->
                            </div>
                        </div>
                    </div>

                    <div class="mt-2 mb-4 row">
                        <div class="col-sm-12">
                            <div class="text-center">
                                <button class="btn-wide btn btn-secondary btn-add ml-4" style="" data-toggle="modal" data-target="#addContactManual" data-dismiss="modal">Add contact Manually</button>
                                <!--<button class="btn-wide btn btn-secondary btn-add ml-4" style="" data-toggle="modal" data-target="#addContactManual" data-dismiss="modal">Add contact Manually</button>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Add Contact Manually Modal -->

        <div class="modal fade" id="addContactManual" tabindex="-1" role="dialog" aria-labelledby="addContactModalLable" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Add Referral</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method="POST" action="${pageContext.request.contextPath}/referral/add" id="ocassionFormModal">
                        <div class="modal-body modal-body-long">
                            <div class="form-group">
                                <label for="firstNameModal">First Name <font color="red">*</font></label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="First Name" id="firstNameModal" name="firstName" maxLength="12" required/>
                                <label for="lastNameModal">Last Name</label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Last Name" id="lastNameModal" name="lastName" maxLength="12"/>
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
                                <label for="addressLineModal">Address Line</label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Address" id="addressLineModal" name="address" />
                                <label for="zipCodeModal">Zip Code</label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Zip Code" id="zipCodeModal" name="zipCode" />
                                <label for="emailAddressModal">Email Address <font color="red">*</font></label>
                                <input type="email" autocomplete="off" class="form-control" placeholder="Email Address" id="emailAddressModal" name="email" required/>
                                <label for="occasionModal">Occasion <font color="red">*</font></label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Occasion" id="occasionModal" value="Christmas" maxLength="12" name="occasion" onchange="occasionHasChanged()" required/>
                                <label for="dateModal">Date</label> 
                                <input type="date" class="form-control" id="dateModal" value="2020-12-25" name="occasionDate"/>
                                <input type="hidden" name="timeZone" value="" id="timeZoneInput"/>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="sendEmailModal" name="sendEmail" checked>
                                    <label class="form-check-label" for="sendEmailModal">Request the rest of the information by email</label>
                                </div>
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

       

        <!-- Add Contact By CSV -->
        <div class="modal fade" id="addContactCSV" tabindex="-1" role="dialog" aria-labelledby="addContactModalLable" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Add Contact</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <!--csv form -->
                    <form>
                        <div class="modal-body">
                            <div class="form-group">
                                <div class="form-group">
                                    <label for="files">Upload a CSV formatted file:</label>
                                    <input type="file" id="files"  class="form-control" accept=".csv" required />
                                </div>                             
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" id="submit-file" class="btn btn-primary">Upload File</button>
                        </div>
                    </form>
                </div>
            </div>
        </div> 

        <!-- Delete Referral Modal -->
        <div class="modal fade" id="deleteReferalModal" tabindex="-1" role="dialog" aria-labelledby="addContactModalLable" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form method="POST" action="${pageContext.request.contextPath}/referral/delete">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteReferalModalLabel">Do you really want to delete this record?</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="row mt-4 mb-3">
                            <div class="col-6 text-center"><strong>Name:</strong></div>
                            <div class="col-6 text-center" id="deleteReferalModalReferralName"></div>
                        </div>
                        <div class="row">
                            <div class="col-6 text-center"><strong>Occasion:</strong></div>
                            <div class="col-6 text-center" id="deleteReferalModalOccasion"></div>
                        </div>
                        <div class="row mt-3 mb-4">
                            <div class="col-6 text-center"><strong>Date:</strong></div>
                            <div class="col-6 text-center" id="deleteReferalModalDate"></div>
                        </div>
                        <input type="hidden" name="id" value="" id="deleteModalInputId">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            <button type="submit" id="submit-file" class="btn btn-danger">Delete</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>   

        <!------------ Contact Detail Modal ------------>
        <div class="modal fade" id="contactDetailModal" tabindex="-1" role="dialog" aria-labelledby="contactDetailModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="contactDetailModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <div>
                                <div class='text-center'><strong>Referred Date:</strong> </div>
                                <div class='text-center' id="contactDetailReferredDate"></div>
                            </div>     
                            <div>
                                <div class='text-center'><strong>Occasion Date: </strong></div>
                                <div class='text-center' id="contactDetailOccasionDate"></div>
                            </div>
                            <div>
                                <div class='text-center'><strong>Address: </strong></div>
                                <div class='text-center' id="contactDetailAddress"></div>
                            </div>
                            <div>
                                <div class='text-center'><strong>Country: </strong></div>
                                <div class='text-center' id="contactDetailCountry"></div>
                            </div>
                            <div>
                                <div class='text-center'><strong>State: </strong></div>
                                <div class='text-center' id="contactDetailState"></div>
                            </div>
                            <div>
                                <div class='text-center'><strong>City: </strong></div>
                                <div class='text-center' id="contactDetailCity"></div>
                            </div>
                            
                            <div>
                                <div class='text-center'><strong>Email: </strong></div>
                                <div class='text-center' id="contactDetailEmail"></div>
                            </div>
                            <div>
                                <div class='text-center'><strong>Last email was sent on:</strong></div>
                                <div class='text-center' id="contactDetailLastEmail"></div>
                            </div>
                        </div>
                        <div class="text-left mt-3"> Last edited by <b id="lastEditedBy"></b>, <em id="lastEditedOn"></em></div>
                    </div>
                    <div class="modal-footer ">
                        <div class="mr-auto">
                            <form method="POST" action="${pageContext.request.contextPath}/update/referral">
                                <input type="hidden" name="referredOccasionId" id="editInfoReferredOccasionId" value="">
                                <button type="submit" class="btn btn-success">Edit Info.</button>
                            </form>
                        </div>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <form method="POST" action="${pageContext.request.contextPath}/sendEmail">
                            <input type='hidden' name='friendId' id='modalFriendId' value=''>
                            <button type="submit" class="btn btn-primary" id="buttonSubmit">Re-Send Email</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        <script src="<c:url value="/resources/font-awesome/js/all.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/dashboard.js"/>"></script>
        <script>
                                    function detailModal(name, referredDate, occasionDate, address,
                                            email, lastEmailDate, friendId, lastEditedBy, lastEditedOn, emailCanBeResent,
                                            country, state, city) {

                                        var labelDiv = document.getElementById("contactDetailModalLabel");
                                        while (labelDiv.firstChild) {
                                            labelDiv.removeChild(labelDiv.firstChild);
                                        }
                                        var labelContent = document.createTextNode(name);
                                        labelDiv.appendChild(labelContent);

                                        var labelDivRef = document.getElementById("contactDetailReferredDate");
                                        while (labelDivRef.firstChild) {
                                            labelDivRef.removeChild(labelDivRef.firstChild);
                                        }
                                        var labelContentRef = document.createTextNode(referredDate);
                                        labelDivRef.appendChild(labelContentRef);

                                        var labelDivOcc = document.getElementById("contactDetailOccasionDate");
                                        while (labelDivOcc.firstChild) {
                                            labelDivOcc.removeChild(labelDivOcc.firstChild);
                                        }
                                        var labelContentOcc = document.createTextNode(occasionDate);
                                        labelDivOcc.appendChild(labelContentOcc);

                                        var labelDivAddr = document.getElementById("contactDetailAddress");
                                        while (labelDivAddr.firstChild) {
                                            labelDivAddr.removeChild(labelDivAddr.firstChild);
                                        }
                                        var labelContentAddr = document.createTextNode(address);
                                        labelDivAddr.appendChild(labelContentAddr);
                                        
                                        var labelDivCountry = document.getElementById("contactDetailCountry");
                                        while (labelDivCountry.firstChild) {
                                            labelDivCountry.removeChild(labelDivCountry.firstChild);
                                        }
                                        var labelContentCountry = document.createTextNode(country);
                                        labelDivCountry.appendChild(labelContentCountry);
                                        
                                        var labelDivState = document.getElementById("contactDetailState");
                                        while (labelDivState.firstChild) {
                                            labelDivState.removeChild(labelDivState.firstChild);
                                        }
                                        var labelContentState = document.createTextNode(state);
                                        labelDivState.appendChild(labelContentState);
                                        
                                        var labelDivCity = document.getElementById("contactDetailCity");
                                        while (labelDivCity.firstChild) {
                                            labelDivCity.removeChild(labelDivCity.firstChild);
                                        }
                                        var labelContentCity = document.createTextNode(city);
                                        labelDivCity.appendChild(labelContentCity);

                                        var labelDivEmail = document.getElementById("contactDetailEmail");
                                        while (labelDivEmail.firstChild) {
                                            labelDivEmail.removeChild(labelDivEmail.firstChild);
                                        }
                                        var labelContentEmail = document.createTextNode(email);
                                        labelDivEmail.appendChild(labelContentEmail);

                                        var labelDivLast = document.getElementById("contactDetailLastEmail");
                                        while (labelDivLast.firstChild) {
                                            labelDivLast.removeChild(labelDivLast.firstChild);
                                        }
                                        var labelContentLast = document.createTextNode(lastEmailDate);
                                        labelDivLast.appendChild(labelContentLast);

                                        var labelDivLastEditedBy = document.getElementById("lastEditedBy");
                                        while (labelDivLastEditedBy.firstChild) {
                                            labelDivLastEditedBy.removeChild(labelDivLastEditedBy.firstChild);
                                        }
                                        var labelContentLastEditedBy = document.createTextNode(lastEditedBy);
                                        labelDivLastEditedBy.appendChild(labelContentLastEditedBy);

                                        var labelDivLastEditedOn = document.getElementById("lastEditedOn");
                                        while (labelDivLastEditedOn.firstChild) {
                                            labelDivLastEditedOn.removeChild(labelDivLastEditedOn.firstChild);
                                        }
                                        var labelContentLastEditedOn = document.createTextNode(moment(lastEditedOn, "DD/MM/YYYY hh:mm:ss").fromNow());
                                        labelDivLastEditedOn.appendChild(labelContentLastEditedOn);

                                        if (!emailCanBeResent) {
                                            document.getElementById('buttonSubmit').style.visibility = 'hidden';
                                        }
                                        console.log("Last Edited By: " + lastEditedBy + "   Last Edited on: " + lastEditedOn);
                                        $("#modalFriendId").val(friendId);
                                        $("#editInfoReferredOccasionId").val(friendId);
                                        $("#contactDetailModal").modal('show');

                                    }

                                    function deleteModal(id, name, occasion, date) {
                                        var idInput = document.getElementById("deleteModalInputId").value = id;

                                        var nameDivLabel = document.getElementById("deleteReferalModalReferralName")
                                        while (nameDivLabel.firstChild) {
                                            nameDivLabel.removeChild(nameDivLabel.firstChild);
                                        }
                                        var nameLabel = document.createTextNode(name);
                                        nameDivLabel.appendChild(nameLabel);

                                        var occasionDivLabel = document.getElementById("deleteReferalModalOccasion");
                                        while (occasionDivLabel.firstChild) {
                                            occasionDivLabel.removeChild(occasionDivLabel.firstChild);
                                        }
                                        var occasionLabel = document.createTextNode(occasion);
                                        occasionDivLabel.appendChild(occasionLabel);

                                        var dateDivLabel = document.getElementById("deleteReferalModalDate");
                                        while (dateDivLabel.firstChild) {
                                            dateDivLabel.removeChild(dateDivLabel.firstChild);
                                        }
                                        var dateLabel = document.createTextNode(date);
                                        dateDivLabel.appendChild(dateLabel);
                                        $("#deleteReferalModal").modal('show');
                                    }

                                    //Add time zone parameter to 
                                    $(function () {

                                        var dateVar = new Date();
                                        var timezone = dateVar.getTimezoneOffset() / 60 * (-1);
                                        document.getElementById("timeZoneInput").value = timezone;
                                    });

                                    function occasionHasChanged() {
                                        var now = new Date();

                                        var day = ("0" + now.getDate()).slice(-2);
                                        var month = ("0" + (now.getMonth() + 1)).slice(-2);

                                        var today = now.getFullYear() + "-" + (month) + "-" + (day);
                                        document.getElementById("dateModal").value = today;
                                        document.getElementById("occasionModal").removeAttribute("onchange");
                                    }

        </script>       
    </body>
</html>
