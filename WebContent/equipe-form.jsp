<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${equipe != null ? "Modifier une équipe" : "Ajouter une équipe"}</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f8f9fc;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .form-container h1 {
            text-align: center;
            margin-bottom: 20px;
            font-weight: bold;
            color: #4e73df;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #d1d3e2;
            border-radius: 5px;
            font-size: 14px;
        }

        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #4e73df;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .form-group button:hover {
            background-color: #375a7f;
        }
    </style>
</head>
<body id="page-top">
<div id="wrapper">
    <%@ include file="sidebar.jsp" %>
    
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@ include file="header.jsp" %>
            <div class="container-fluid">
                <div class="form-container">
                    <h1>${equipe != null ? "Modifier une équipe" : "Ajouter une équipe"}</h1>
                    <form action="EquipeServlet" method="post">
                        <c:if test="${equipe != null}">
                            <input type="hidden" name="id_eq" value="${equipe.id}" />
                        </c:if>
                        <div class="form-group">
                            <label for="nom">Nom :</label>
                            <input type="text" name="nom" id="nom" value="${equipe != null ? equipe.nom : ''}" required />
                        </div>
                        <div class="form-group">
                            <label for="taille">Taille :</label>
                            <input type="number" name="taille" id="taille" value="${equipe != null ? equipe.taille : ''}" required />
                        </div>
                        <div class="form-group">
                            <button type="submit" name="action" value="${equipe != null ? 'update' : 'add'}">
                                ${equipe != null ? "Modifier" : "Ajouter"}
                            </button>
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
