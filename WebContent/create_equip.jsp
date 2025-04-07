<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Ajout d'une nouvelle Equipe</h1>
<form action="Equip_servlet?action=add_equip" method="post">
        Nom: <input type="text" name="nom_form">
        <br>
        <br>
        <input type="submit" value="Créer">
</form>
</body>
</html>