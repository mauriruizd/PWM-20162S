<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Marca" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionMarca" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = null;
	
	String action = request.getParameter("newAction");
	
	Marca marca = new Marca();
	Marca listaMarca[] = new Marca[0];
	
	if( action != null && !action.equals("") ) {	
		try {
			marca.setIdMarca(Integer.parseInt(request.getParameter("codigo")));
		} catch(Exception e) {}
		marca.setDescricao(request.getParameter("descricao"));
		
		switch (action) {
		case "find":
			listaMarca = SessionMarca.find(marca);
			break;
		case "remove":
			//
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Marca</title>
<link rel="stylesheet" href="../css/forms.css">
<link rel="stylesheet" href="../css/styles.css">

<script src="../js/scripts.js"></script>
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
	<h1>Consultar Marca</h1>
	<div>
		<form action="consultar.jsp" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<label for="codigo">Código</label>
			<input class="limpavel" id="codigo" name="codigo" style="width: 200px;" 
			value="<%= marca.getIdMarca() == null ? "" : marca.getIdMarca() %>" type="text">
			<label for="descricao">Descrição</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= marca.getDescricao() == null ? "" : marca.getDescricao() %>" type="text">
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
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% for(Marca nMarca : listaMarca) { %>
		<tr>
			<td><%= nMarca.getIdMarca() %></td>
			<td><%= nMarca.getDescricao() %></td>
			<td>
				<a class="btn btn-delete" href="consultar.jsp?newAction=remove&amp;codigo=<%= nMarca.getIdMarca() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="manter.jsp?newAction=detail&amp;codigo=<%= nMarca.getIdMarca() %>" class="btn btn-edit">Editar</a>
				
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>