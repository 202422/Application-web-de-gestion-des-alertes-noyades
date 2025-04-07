<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Choix du Type d'Alerte</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <style>
        .btn-group {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 15px; /* Espacement entre les boutons */
        }
    </style>
</head>
<body id="page-top">

<div id="wrapper">
    <%@ include file="sidebar.jsp" %>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@ include file="header.jsp" %>

            <div class="container-fluid center-choice">
                <div class="choice-card card shadow mb-4">
                    <div class="card-header py-3">
                        <h1 class="text-center">Choisir le type d'alerte a traiter </h1>
                    </div>
                    <div class="card-body text-center">
                        <!-- Groupe de boutons -->
                        <div class="btn-group">
                            <!-- Bouton pour traiter les alertes de batterie faible -->
                            <a href="AlerteServlet?action=traiterBatterie" class="btn btn-warning btn-lg">
                                Traiter les Alertes Batterie Faible
                            </a>

                            <!-- Bouton pour traiter les alertes de noyade -->
                            <a href="AlerteNoyadeServlet2" class="btn btn-danger btn-lg">
                                Traiter les Alertes Noyade
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="js/sb-admin-2.min.js"></script>
</body>
</html>
