<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Marca" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionMarca" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Marca marca = (Marca) request.getSession().getAttribute("filterMarca");
	if (marca == null) {
		marca = new Marca();
	}
	Object list[] = (Object[]) request.getAttribute("list");
	if (list == null) {
		list = new Object[0];
	}
	
	
	/*String action = request.getParameter("newAction");
	
	Marca marca = new Marca();
	Object listaMarca[] = new Marca[0];
	
	if( action != null && !action.equals("") ) {
		SessionMarca sessionMarca = new SessionMarca();
		try {
			marca.setIdMarca(Integer.parseInt(request.getParameter("idmarca")));
		} catch(Exception e) {}
		marca.setDescricao(request.getParameter("descricao"));
		
		switch (action) {
		case "find":
			listaMarca = sessionMarca.find(marca);
			break;
		case "remove":
			try {
				sessionMarca.remove(marca);
				infoMsg = "Remoção efeituada com sucesso";
			} catch (PSQLException e) {
				switch (e.getSQLState()) {
					case "23503":
						errorMsg = "Existe um registro associado a esta marca, portanto não foi possível efetuar a remoção";
						break;
					case "23505":
						errorMsg = "Já existe uma marca com essa descrição";
						break;
					default:
						errorMsg = "Houve um erro no momento de concretar a operação";
				}
				errorDetail = e.getMessage();
			}
			marca = new Marca();
			break;
		}
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Marca</title>
<link rel="stylesheet" href="css/forms.css">
<link rel="stylesheet" href="css/styles.css">

<script src="js/scripts.js"></script>
<script>
	function confirmarExclusao() {
		return confirm('Confirma exclusao?');
	}

	function validate() {
		return true;
	}
</script>
</head>
<body>
	<% if(errorMsg != null) { %>
		<div class="server-msg error-msg">
			<%= errorMsg %>
			<% if(errorDetail != null) { %>
			<div class="error-detail">
				<b>Detalhe:</b>
				<p>
					<%= errorDetail %>
				</p>
			</div>
			<% } %>
		</div>
	<% } %>
	
	<% if(infoMsg != null) { %>
		<div class="server-msg info-msg">
			<%= infoMsg %>
		</div>
	<% } %>
	
	<h1>Consultar Marca</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<input name="entityName" id="entityName" value="Marca" type="hidden">
			<label for="codigo">Código</label>
			<input class="limpavel" id="idMarca" name="idMarca" style="width: 200px;" 
			value="<%= marca.getIdMarca() == null ? "" : marca.getIdMarca() %>" type="text">
			<label for="descricao">Descrição</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= marca.getDescricao() == null ? "" : marca.getDescricao() %>" type="text">
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Consultar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a href="dispatcher?newAction=goNew&entityName=Marca" class="btn btn-default">Novo</a>
			</div>
		</form>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th class="short-cell">Código</th>
				<th>Descrição</th>
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% 
		for(Object nObj : list) {
			Marca nMarca = (Marca) nObj;
		%>
		<tr>
			<td><%= nMarca.getIdMarca() %></td>
			<td><%= nMarca.getDescricao() %></td>
			<td>
				<a class="btn btn-delete" href="dispatcher?newAction=remove&entityName=Marca&id=<%= nMarca.getIdMarca() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=detail&entityName=Marca&id=<%= nMarca.getIdMarca() %>" class="btn btn-edit">Editar</a>
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>