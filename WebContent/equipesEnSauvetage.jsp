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
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body id="page-top">

<div id="wrapper">
    <%@ include file="sidebar.jsp" %>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@ include file="header.jsp" %>

            

                <!-- Liste des équipes en sauvetage -->
                <div class="choice-card card shadow mb-4">
                    <div class="card-header py-3">
                        <h2 class="text-center">Liste des Équipes en Sauvetage</h2>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty equipes}">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Nom de l'équipe</th>
                                        <th>Taille</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="equipe" items="${equipes}">
                                        <tr>
                                            <td>${equipe.nom}</td>
                                            <td>${equipe.taille}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>

                        <c:if test="${empty equipes}">
                            <p style="text-align: center;">Aucune équipe en sauvetage n'a été trouvée.</p>
                        </c:if>
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
