<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un nouveau membre à l'équipe</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
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
            display: block;
            margin-bottom: 5px;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #d1d3e2;
            border-radius: 5px;
            font-size: 14px;
        }

        .form-actions {
            text-align: center;
        }

        .form-actions button {
            padding: 10px 20px;
            background-color: #4e73df;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .form-actions button:hover {
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
                    <h1>Ajouter un nouveau membre à l'équipe</h1>
                    <form action="PersonneServlet" method="post">
                       <div class="form-group">
						    <label for="nom">Nom :</label>
						    <input type="text" name="nom" id="nom" placeholder="Entrez le nom" required>
						</div>
						
						<div class="form-group">
						    <label for="prenom">Prénom :</label>
						    <input type="text" name="prenom" id="prenom" placeholder="Entrez le prénom" required>
						</div>
						
						<div class="form-group">
						    <label for="email">Email :</label>
						    <input type="email" name="email" id="email" placeholder="Entrez l'email" required>
						</div>
						
						<div class="form-group">
						    <label for="password">Password: </label>
						    <input type="text" name="password" id="password" placeholder="Entrez le password" required>
						</div>

						
                        <div class="form-group">
                            <label for="chef_eq">Chef d'équipe :</label>
                            <select name="chef_eq" id="chef_eq" class="form-control">
                                <option value="1">Oui</option>
                                <option value="0">Non</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="id_eq">Équipe :</label>
                            <select name="id_eq" id="id_eq" class="form-control" required>
                                <c:forEach var="equipe" items="${equipes}">
                                    <option value="${equipe.id}">${equipe.nom}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Ajouter</button>
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
