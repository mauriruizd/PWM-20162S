<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Marca" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Modelo" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionMarca" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionModelo" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = null;
	
	Modelo modelo = (Modelo) request.getSession().getAttribute("filterModelo");
	if (modelo == null) {
		modelo = new Modelo();
	}
	Object list[] = (Object[]) request.getAttribute("list");
	if (list == null) {
		list = new Object[0];
	}
	
	Object listaMarca[] = (Object[]) request.getAttribute("listaMarca");
	
	/*String action = request.getParameter("newAction");
	SessionModelo sessionModelo = new SessionModelo();
	
	SessionMarca sessionMarca = new SessionMarca();
	Modelo modelo = new Modelo();
	Object listModelo[] = new Modelo[0];
	Marca marca = new Marca();
	Object listaMarca[] = sessionMarca.find(marca);
	
	if( action != null && !action.equals("") ) {	
		try {
			marca.setIdMarca(Integer.parseInt(request.getParameter("idMarca")));
		} catch(Exception e) {}
		try {
			modelo.setIdModelo(Integer.parseInt(request.getParameter("codigo")));
		} catch(Exception e) {}
		
		modelo.setDescricao(request.getParameter("descricao"));
		modelo.setMarca(marca);
		
		switch (action) {
		case "find":
			listModelo = sessionModelo.find(modelo);
			break;
		case "remove":
			try {
				sessionModelo.remove(modelo);
				infoMsg = "Remoção efeituada com sucesso";
			} catch (PSQLException e) {
				switch (e.getSQLState()) {
					case "23503":
						errorMsg = "Existe um registro associado a esta marca, portanto não foi possível efetuar a remoção";
						break;
					case "23505":
						errorMsg = "Já existe um modelo com essa descrição";
						break;
					default:
						errorMsg = "Houve um erro no momento de concretar a operação";
				}
				errorDetail = e.getMessage();
			}
			modelo = new Modelo();
		}
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Modelo</title>
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
	
	<h1>Consultar Modelo</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<input name="entityName" id="entityName" value="Modelo" type="hidden">
			<label for="idmodelo">Código</label>
			<input class="limpavel" id="idmodelo" name="idmodelo" style="width: 200px;" 
			value="<%= modelo.getIdModelo() == null ? "" : modelo.getIdModelo() %>" type="text">
			<label for="descricao">Descrição</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= modelo.getDescricao() == null ? "" : modelo.getDescricao() %>" type="text">
			<label for="marca">Marca</label><br>
			<select id="marca" class="limpavel" name="marca">
				<option value="">Selecione Marca</option>
				<%
				for(Object nObj : listaMarca) { 
					Marca nMarca = (Marca) nObj;
				%>
					<option <%= modelo.getMarca() == null ? "" : (
							modelo.getMarca().equals(nMarca) ? "selected" : ""
							) %> value="<%= nMarca.getIdMarca() %>">
						<%= nMarca.getDescricao() %>
					</option>
				<% } %>
			</select>
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Consultar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a href="manter.jsp" class="btn btn-default">Novo</a>
			</div>
		</form>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th class="short-cell">Código</th>
				<th>Descrição</th>
				<th>Marca</th>
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% 
		for(Object nObj : list) {
			Modelo nModelo = (Modelo) nObj;
		%>
		<tr>
			<td><%= nModelo.getIdModelo() %></td>
			<td><%= nModelo.getDescricao() %></td>
			<td><%= nModelo.getMarca().getDescricao() %></td>
			<td>
				<a class="btn btn-delete" href="dispatcher?newAction=remove&entityName=Modelo&id=<%= nModelo.getIdModelo() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=detail&entityName=Modelo&id=<%= nModelo.getIdModelo() %>" class="btn btn-edit">Editar</a>
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>