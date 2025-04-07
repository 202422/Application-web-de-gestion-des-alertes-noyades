<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails de l'Équipe</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">

<div id="wrapper">
    <%@ include file="sidebar.jsp" %>
    
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@ include file="header.jsp" %>
            
            <div class="container-fluid">
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h1 class="text-center">Membres de l'équipe </h1>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Chef d'Équipe</th>
                                </tr>
                            </thead>
                          <tbody>
    <c:forEach var="membre" items="${membres}">
        <tr>
            <td>${membre.id}</td>
            <td>${membre.nom}</td>
            <td>${membre.prenom}</td>
            <td>
                <c:choose>
                    <c:when test="${membre.chef_eq == 1}">
                        Oui
                    </c:when>
                    <c:otherwise>
                        Non
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</tbody>

                        </table>
                        <div class="text-center mt-4">
                            <a href="EquipeServlet?action=list" class="btn btn-primary">Retour à la liste des équipes</a>
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
