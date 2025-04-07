<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Alertes Noyades</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="css/sb-admin-2.css" rel="stylesheet">
</head>
<body id="page-top">

<div id="wrapper">
    <%@ include file="sidebar.jsp" %>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@ include file="header.jsp" %>

            <div class="container-fluid center-list">
                <!-- Card pour la liste des alertes -->
                <div class="list-card card shadow mb-4">
                    <div class="card-header py-3">
                        <h1 class="text-center">Liste des Alertes de Noyades</h1>
                    </div>
                    <div class="card-body">
                      
                        <!-- Tableau des alertes -->
                        <table class="table table-bordered table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Date</th>
                                    <th>Heure</th>
                                    <th>Temps écoulé</th>
                                    <th>Latitude</th>
                                    <th>Longitude</th>
                                    <th>Nombre de Personnes</th>
                                    <th>État</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="alerte" items="${alertes}">
                                    <tr style="${alerte.etat == 'Non Traité' ? 'background-color: #ffcccc; color: black;' : ''}">
                                        <td>${alerte.date}</td>
                                        <td>${alerte.heure}</td>
     									<td>
     									<c:if test="${alerte.etat != 'Traité'}">
     									${alerte.tempsEcoule}
     									</c:if>
     									<c:if test="${alerte.etat == 'Traité'}">
     									--
     									</c:if>
     									</td> <!-- Appel de la méthode getTempsEcoule() -->
                                        <td>${alerte.latitude}</td>
                                        <td>${alerte.longitude}</td>
                                        <td>${alerte.nb_pers}</td>
                                        <td>${alerte.etat}</td>
                                        <td>
                                        <c:if test="${alerte.etat == 'Non Traité'}">
                                            <!-- Boutons Modifier et Supprimer -->
                                            <a href="AlerteNoyadeServlet2?action=setEnCours&id=${alerte.id}" 
       class="btn btn-success btn-sm">Traiter</a>
      									 </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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
