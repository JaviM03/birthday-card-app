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

        </style>
        <script src="<c:url value="/resources/moment.min.js"/>"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <link href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="<c:url value="/resources/papaparse.min.js"/>"></script>    
        <script type="text/javascript">
            $(document).ajaxStop(function(){
                window.location.reload();
            });
            $(document).ready(function(){

              $('#submit-file').on("click",function(e){
                          e.preventDefault();
                          $('#files').parse({
                                  config: {
                                          delimiter: "auto",
                                          complete: insertToDB,
                                  },
                                  before: function(file, inputElem)
                                  {
                                          //console.log("Parsing file...", file);
                                  },
                                  error: function(err, file)
                                  {
                                          //console.log("ERROR:", err, file);
                                  },
                                  complete: function()
                                  {
                                          //console.log("Done with all files");
                                  }
                          });
              });
              

                  function insertToDB(results){

                          var data = results.data;
                         //ajax to w ork with
                            $.ajax({
                                url: `${pageContext.request.contextPath}/addByCSV/`,
                                            type: 'POST',
                                            data: {csv: JSON.stringify(data)},
                                            success: function () {
                                                //document.getElementById("test").innerHTML = "Funciono";
                                                //console.log(csv);
                                            },
                                            error: function(xhr,status,error){
//                                                console.log(xhr.responseText);
//                                                document.getElementById("test").innerHTML = "No Funciono";
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
                                            <a data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="p-0 btn">
                                                <img width="42" class="rounded-circle" src="<c:url value="/resources/img/default-profile2.jpg"/>" alt="">
                                            </a>
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
                                                                            '${referral.email}', '<fmt:formatDate type="date" dateStyle="short" value="${referral.lastEmailDate.time}"/>', '${referral.referredOccasionId}', ${referral.emailCanBeResent})">Details</button>
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
                    
                    
                    
                    <!--
                    <form method="POST" action="${pageContext.request.contextPath}/addOccasion">
                        <div class="modal-body">
                            <div class="form-group">

                                <label for="firstNameModal">First Name <font color="red">*</font></label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="First Name" id="firstNameModal" name="firstName" maxLength="12" required/>
                                <label for="lastNameModal">Last Name</label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Last Name" id="lastNameModal" name="lastName" maxLength="12"/>
                                <label for="firstNameModal">Address</label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Address" id="firstNameModal" name="address" />
                                <label for="emailAddressModal">Email Address <font color="red">*</font></label>
                                <input type="email" autocomplete="off" class="form-control" placeholder="Email Address" id="emailAddressModal" name="email" required/>
                                <label for="dateModal">Date</label> 
                                <input type="date" class="form-control" id="dateModal" name="occasionDate"/>
                                <label for="occasionModal">Occasion <font color="red">*</font></label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Occasion" id="ocassionModal" maxLength="12" name="occasion" required/>
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
                        -->
                        
                        
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
                                <div class='text-center'><strong>Email: </strong></div>
                                <div class='text-center' id="contactDetailEmail"></div>
                            </div>
                            <div>
                                <div class='text-center'><strong>Last email was sent on:</strong></div>
                                <div class='text-center' id="contactDetailLastEmail"></div>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <form method="POST" action="${pageContext.request.contextPath}/sendEmail">
                            <input type='hidden' name='friendId' id='modalFriendId' value=''>
                            <button type="submit" class="btn btn-primary" id="buttonSubmit">Re-Send Email</button>
                        </form>
                    </div>

                </div>
            </div>
                            
        </div>
                            
         <!-- Add Contact Manual-->
        <div class="modal fade" id="addContactManual" tabindex="-1" role="dialog" aria-labelledby="addContactModalLable" aria-hidden="true">
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
                                <label for="firstNameModal">Address</label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Address" id="firstNameModal" name="address" />
                                <label for="emailAddressModal">Email Address <font color="red">*</font></label>
                                <input type="email" autocomplete="off" class="form-control" placeholder="Email Address" id="emailAddressModal" name="email" required/>
                                <label for="dateModal">Date</label> 
                                <input type="date" class="form-control" id="dateModal" name="occasionDate"/>
                                <label for="occasionModal">Occasion <font color="red">*</font></label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Occasion" id="ocassionModal" maxLength="12" name="occasion" required/>
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
                    
                    <!-- Addcontact Form to move 
                    <form method="POST" action="${pageContext.request.contextPath}/addOccasion">
                        <div class="modal-body">
                            <div class="form-group">

                                <label for="firstNameModal">First Name <font color="red">*</font></label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="First Name" id="firstNameModal" name="firstName" maxLength="12" required/>
                                <label for="lastNameModal">Last Name</label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Last Name" id="lastNameModal" name="lastName" maxLength="12"/>
                                <label for="firstNameModal">Address <font color="red">*</font></label>
                                <input type="text" autocomplete="off" class="form-control" placeholder="Address" id="firstNameModal" name="address"  required/>
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
                    </form> -->
                        
          
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
                            
                            
                            
        <script>
            function detailModal(name, referredDate, occasionDate, address,
                    email, lastEmailDate, friendId, emailCanBeResent) {
                        
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
                
                if(!emailCanBeResent){
                    document.getElementById('buttonSubmit').style.visibility = 'hidden';
                }

                $("#modalFriendId").val(friendId);
                console.log($("#modalFriendId").val() + " FriendId: " + friendId);
                $("#contactDetailModal").modal('show');

            }


        </script>
        <script src="<c:url value="/resources/font-awesome/js/all.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/dashboard.js"/>"></script>
    </body>
</html>
