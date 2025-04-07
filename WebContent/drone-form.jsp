<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire Drone</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">

<div id="wrapper">
    <%@ include file="sidebar.jsp" %>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@ include file="header.jsp" %>

            <div class="container-fluid center-list">
                <div class="list-card card shadow mb-4">
                    <div class="card-header py-3">
                        <h1 class="text-center">Formulaire Drone</h1>
                    </div>
                    <form action="DroneServlet" method="post" class="p-4">
                        <input type="hidden" name="action" value="${drone != null ? 'update' : 'add'}">
                        <c:if test="${drone != null}">
                            <input type="hidden" name="id_dr" value="${drone.id}">
                        </c:if>

                        <div class="form-group">
                            <label for="Nom">Nom :</label>
                            <input type="text" id="Nom" name="Nom" class="form-control" value="${drone != null ? drone.nom : ''}" required>
                        </div>

                        <div class="form-group">
                            <label for="Pour_bat">Pourcentage de Batterie :</label>
                            <input type="number" id="Pour_bat" name="Pour_bat" class="form-control" value="${drone != null ? drone.pour_bat : ''}" required>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Enregistrer</button>
                        </div>
                    </form>
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
