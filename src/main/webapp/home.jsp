<%-- 
    Document   : livros
    Created on : 10/07/2016, 03:00:56
    Author     : kieckegard
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.ItemLivro"%>
<%@page import="br.edu.ifpb.padroes.projeto.sisbiblioteca.model.QueryItemLivroBo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>sisBiblioteca</title>
	<link rel="stylesheet" type="text/css" href="css/estilo.css">
	<script type="text/javascript" src='js/jquery-1.12.3.js'></script>
	<script type="text/javascript" src='js/script.js'></script>
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">

	<!-- BOOTSTRAP-->

	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>

	<!-- END OF BOOTSTRAP -->
</head>
<body>
	<header>
		<a href="index.html"><h1>SisBiblioteca</h1></a>
	</header>
	<aside class='vertical-nav'>
		<p class='title'>Navigation</p>
		<ul class='navigation'>
			<li><a href="alunos.jsp"><i class="fa fa-user" aria-hidden="true"></i>  Alunos</a></li>
			<li><a href="livros.jsp"><i class="fa fa-book" aria-hidden="true"></i> Livros</a></li>
			<li><a href="#"><i class="fa fa-sticky-note-o" aria-hidden="true"></i> Empréstimos</a></li>
		</ul>
	</aside>
	<div class='content-container'>
	</div>
</body>
</html>
