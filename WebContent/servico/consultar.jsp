<%@page import="java.util.Calendar"%>
<%@page import="org.postgresql.util.PSQLException"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Servico" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionServico" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Servico servico = (Servico) request.getSession().getAttribute("filterServico");
	if (servico == null) {
		servico = new Servico();
	}
	Object list[] = (Object[]) request.getAttribute("list");
	if (list == null) {
		list = new Object[0];
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Servico</title>
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
	
	<h1>Consultar Servico</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<input name="entityName" id="entityName" value="Servico" type="hidden">

			<label for="idservico">Código</label>
			<input class="limpavel" id="idservico" name="idservico" style="width: 200px;" 
			value="<%= servico.getIdServico() == null ? "" : servico.getIdServico() %>" type="text">
			
			<label for="descricao">Descricao</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= servico.getDescricao() == null ? "" : servico.getDescricao() %>" type="text">
			
			<label for="cidade">Valor</label>
			<input class="limpavel" id="valor" name="valor" 
			value="<%= servico.getValor() == null ? "" : servico.getValor() %>" type="text">
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Consultar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a href="dispatcher?newAction=goNew&entityName=Servico" class="btn btn-default">Novo</a>
			</div>
		</form>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th class="short-cell">Código</th>
				<th>Descricao</th>
				<th>Valor</th>
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% 
		for(Object obj : list) {
			Servico nServico = (Servico) obj;
		%>
		<tr>
			<td><%= nServico.getIdServico() %></td>
			<td><%= nServico.getDescricao() %></td>
			<td><%= nServico.getValor() %></td>
			<td>
				<a class="btn btn-delete" href="dispatcher?newAction=remove&entityName=Servico&id=<%= nServico.getIdServico() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=detail&entityName=Servico&id=<%= nServico.getIdServico() %>" class="btn btn-edit">Editar</a>
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>