<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.JAVA.Beans.Personne" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Gestion Alerte</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.css" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

</head>

<body id="page-top">


    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="#">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class='bx bx-clipboard'></i>
                </div>
                <div class="sidebar-brand-text mx-3"> Gestion Alerte </div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Accueil -->
            <li class="nav-item ">
                <a class="nav-link" href="Login?action=home">
                   <i class='bx bxs-home'></i>
                    <span>Accueil</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">


            <!-- Nav Item - Alertes -->
            <li class="nav-item">
                <a class="nav-link " href="Consulter_Alerte?action=list">
                   <i class='bx bxs-bell'></i>
                    <span>Consulter les alertes</span></a>
            </li>

			<!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Alertes -->
            <li class="nav-item active">
                <a class="nav-link " href="Feedback?action=fairefdb">
                   <i class='bx bxs-comment'></i>
                    <span>Faire un Feedback</span></a>
            </li>

            
           

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                   

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        

                        

                        
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"></span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    
                      <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h4 class="m-0 font-weight-bold text-primary">Formulaire pour le Feedback</h4>
                                </div>
                                <div class="card-body">
                                
                 
                                
                                <p>Coordonnées géographiques: <span class="text-gray-800 p-3 m-0">(${alertenoy.latitude},${alertenoy.longitude})</span></p>
                                <p>Date de l'alerte: <span class="text-gray-800 p-3 m-0">${alertenoy.date }</span></p>
                                <p>Heure de l'alerte: <span class="text-gray-800 p-3 m-0">${alertenoy.heure }</span><p>
                                <p>Nombre de personnes se noyant: <span class="text-gray-800 p-3 m-0">${alertenoy.nb_pers }</span><p>
                                   <form class="user" action = "Feedback?action=addfd" method = "post">
                         				
                         				<div class="text-gray-800 p-3 m-0">
                         				<div class="form-group">
                         				
                         				<label for="etat">Sélectionnez l'état de votre sauvetage</label>
                                            <select name="Etat" required class="form-control form-control-user" id = "etat">
                                            	
									            <option value="Succès">Succès</option>
									            <option value="Echec">Echec</option>
									            
									        </select>
                                        </div>
                         				
                                        <div class="form-group">
                                        <label for="durée">Quelle est la durée du sauvetage: </label>
                                            <input type="time" class="form-control form-control-user" 
                                           id = "" name = "durée">
                                        </div>
                                        
                                        <input type="hidden" value = "${alertenoy.id}"
                                            name = "id_al_N">
                                        
                                        <div class="form-group">
                                        <label for="Nbpers">Combien de personnes ont pu être sauvées: </label>
                                            <input type="Number" class="form-control form-control-user"
                                                 id = "Nbpers" name = "Nbpers">
                                        </div>
                                        <input type ="submit" value= "Soumettre" class= "btn btn-primary btn-user btn-block">
                                        </div>
                                      
                                        </form>
                                </div>
                            </div>
                    
                    
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="Logout">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>