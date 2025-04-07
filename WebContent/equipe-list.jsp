
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Équipes</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <style>
        /* Centrer uniquement la liste */
        .center-list {
            display: flex;
            justify-content: center;
        }

        .list-card {
            width: 80%; /* Ajustez la largeur si nécessaire */
        }
    </style>
</head>
<body id="page-top">

<div id="wrapper">
    <%@ include file="sidebar.jsp" %>
    
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@ include file="header.jsp" %>
            
            <!-- Centrer la liste -->
            <div class="container-fluid center-list">
                <div class="list-card card shadow mb-4">
                    <div class="card-header py-3">
                        <h1 class="text-center">Liste des Équipes</h1>
                    </div>
                    <a href="EquipeServlet?action=addForm" class="btn btn-primary mb-3">Ajouter une équipe</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Taille</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="equipe" items="${equipes}">
                                <tr>
                                    <td>${equipe.id}</td>
                                    <td>${equipe.nom}</td>
                                    <td>${equipe.taille}</td>
                                    <td>
                                        <!-- Bouton Détails -->
<a href="EquipeServlet?action=details&id_eq=${equipe.id}" class="btn btn-info">Détails</a>
                                        <!-- Bouton Modifier -->
                                        <a href="EquipeServlet?action=updateForm&id_eq=${equipe.id}" class="btn btn-sm btn-warning">Modifier</a>
                                        <!-- Bouton Supprimer -->
                                        <a href="EquipeServlet?action=delete&id_eq=${equipe.id}" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette équipe ?');">Supprimer</a>
                                        <!-- Bouton Ajouter un membre -->
                                        <a href="PersonneServlet?id_eq=${equipe.id}" class="btn btn-sm btn-success">Ajouter un membre</a>
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

<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="js/sb-admin-2.min.js"></script>
</body>
</html>
