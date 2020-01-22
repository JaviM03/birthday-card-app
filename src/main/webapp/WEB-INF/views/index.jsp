<%-- 
    Document   : index
    Created on : 13/01/2020, 06:03:37 PM
    Author     : HP PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Christmas Card</title>
        <link rel="stylesheet" href="<c:url value="/resources/main.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap.css" />">
        <link rel="stylesheet" href="<c:url value="/resources/animate.css" />">        
    </head>    
    <body>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/resources/font-awesome/js/all.js"/>"></script>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark primary fixed-top">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Logo</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="mt-5 header" style="">
            <div class="lg-content-header">
                <img src="<c:url value="/resources/cards3.jpg" />" style="width: 100%; height: auto; " class="header-image">
                <div class="centered text-center header-text" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">
                    <h1 class="display-4 font-weight-normal">Card headline</h1>
                    <p class="lead font-weight-normal">This is a referrence content only. It's only here to fill out the space and for general referrence.</p>
                    <a class="btn btn-outline-secondary" href="#">Start Your Calendar</a>
                </div>
            </div>
            <div class="sm-content-header container" style="display: none">
                <div class="row">
                    <div class="col-9 mx-auto text-center">
                        <h1 class="display-4 font-weight-normal">Card headline</h1>
                        <p class="lead font-weight-normal">This is a referrence content only. It's only here to fill out the space and for general referrence.</p>
                        <a class="btn btn-outline-secondary" href="#">Start Your Calendar</a>
                    </div>
                </div>
            </div>
        </div>


        <div class="container marketing text-center pt-5">

            <!-- Three columns of text below the carousel -->
            <div class="row">
                <div class="col-lg-4">
                    <div class='rounded-circle primary shadow ' style='height: 140px; width:140px; display: inline-block;'>
                        <div style="margin-top: 32%" class="text-center">
                            <i class="far fa-id-card my-auto fa-3x" style=""></i>
                        </div>
                    </div>
                    <h2 class="pt-3 pb-1">Contacts</h2>
                    <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.</p>
                    <p><a class="btn btn-secondary" href="#" role="button">Contacts info &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <div class='rounded-circle primary shadow ' style='height: 140px; width:140px; display: inline-block;'>
                        <div style="margin-top: 32%" class="text-center">
                            <i class="far fa-calendar-alt my-auto fa-3x" style=""></i>
                        </div>
                    </div>
                    <h2 class="pt-3 pb-1">Calendar</h2>
                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.</p>
                    <p><a class="btn btn-secondary" href="#" role="button">Calendar info &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <div class='rounded-circle primary shadow ' style='height: 140px; width:140px; display: inline-block;'>
                        <div style="margin-top: 32%" class="text-center">
                            <i class="far fa-envelope my-auto fa-3x" style=""></i>
                        </div>
                    </div>
                    <h2 class="pt-3 pb-1">Send</h2>
                    <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum mass.</p>
                    <p><a class="btn btn-secondary" href="#" role="button">Send info &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
            </div><!-- /.row -->

        </div>

        <div class="container marketing pt-5">
            <hr class="featurette-divider pb-3">

            <div class="row featurette pt-4 pb-4">
                <div class="col-md-7 my-auto">
                    <h2 class="featurette-heading">Product of the Month. <br> <span class="text-muted">Get ready for it!</span></h2>
                    <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
                </div>
                <div class="col-md-5">
                    <img class="featurette-image img-fluid mx-auto" src="https://cdn3.volusion.com/p47su.y695r/v/vspfiles/photos/9824623-2.jpg?v-cache=1574532263" alt="Generic placeholder image">
                </div>
            </div>
            <hr class="featurette-divider pt-3">
        </div>

        <section class="card-container py-5 pt-1" >
            <div class="container">
                <div class="row">
                    <div class="col-lg-1"></div>
                    <div class="col-lg-10">
                        <div class="card mb-5 mb-lg-0">
                            <div class="card-body">
                                <h3 class="card-title text-muted text-center">Get Started!</h3>
                                <hr>
                                <ul class="fa-ul">
                                    <li style="font-size: 20px"><span class="fa-li"><i class="fas fa-check"></i></span>Get unlimited referrals for friends.</li>
                                    <li style="font-size: 20px"><span class="fa-li"><i class="fas fa-check"></i></span>Organize your calendar for every kind of present.</li>
                                    <li style="font-size: 20px"><span class="fa-li"><i class="fas fa-check"></i></span>All-in-one site for your dates.</li>
                                    <li style="font-size: 20px"><span class="fa-li"><i class="fas fa-check"></i></span>Ask your friends for their most important celebrations.</li>
                                    <li style="font-size: 20px" class="pt-4"><span class="fa-li"><i class="fas fa-check"></i></span><strong>Never miss a date again!</strong></li>
                                </ul>
                                <div class="row">
                                    <div class="col-lg-8 col-sm-1 col-1"></div>
                                    <div class="col-lg-3 col-sm-10 col-10">
                                        <a href="${pageContext.request.contextPath}/signup" class="btn btn-block text-uppercase btn-secondary">Register</a>
                                    </div>
                                    <div class="col-lg-1 col-sm-1 col-10"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-1"></div>
                </div>
            </div>
        </section>

        <div class="pb-5"></div>
        <!-- Footer -->
        <footer class="secondary pt-3">

            <!-- Footer Elements -->
            <div class="container">

                <!-- Grid row-->
                <div class="row">

                    <!-- Grid column -->
                    <div class="col-md-12 pt-4 pb-1">
                        <div class="mb-5 text-center">

                            <!-- Facebook -->
                            <a class="fb-ic">
                                <i class="fab fa-facebook-f fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                            </a>
                            <!-- Twitter -->
                            <a class="tw-ic">
                                <i class="fab fa-twitter fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                            </a>
                            <!-- Google +-->
                            <a class="gplus-ic">
                                <i class="fab fa-google-plus-g fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                            </a>
                            <!--Linkedin -->
                            <a class="li-ic">
                                <i class="fab fa-linkedin-in fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                            </a>
                            <!--Instagram-->
                            <a class="ins-ic">
                                <i class="fab fa-instagram fa-lg white-text mr-md-5 mr-3 fa-2x"> </i>
                            </a>
                            <!--Pinterest-->
                            <a class="pin-ic">
                                <i class="fab fa-pinterest fa-lg white-text fa-2x"> </i>
                            </a>
                        </div>
                    </div>
                    <!-- Grid column -->

                </div>
                <!-- Grid row-->

            </div>
            <!-- Footer Elements -->

            <!-- Copyright -->
            <div class="text-center pb-3 pt-1 secondary-dark" style="background-color: #5a84bb">© 2020 Copyright: Christmas Cards
            </div>
            <!-- Copyright -->

        </footer>
        <!-- Footer -->
    </body>
</html>
