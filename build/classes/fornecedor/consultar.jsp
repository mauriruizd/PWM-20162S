<%@page import="java.util.Calendar"%>
<%@page import="org.postgresql.util.PSQLException"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Fornecedor" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionFornecedor" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Fornecedor fornecedor = (Fornecedor) request.getSession().getAttribute("filterFornecedor");
	if (fornecedor == null) {
		fornecedor = new Fornecedor();
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
<title>Consultar Fornecedor</title>
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
	
	<h1>Consultar Fornecedor</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<input name="entityName" id="entityName" value="Fornecedor" type="hidden">
			<label for="idfornecedor">Código</label>
			<input class="limpavel" id="idfornecedor" name="idfornecedor" style="width: 200px;" 
			value="<%= fornecedor.getIdFornecedor() == null ? "" : fornecedor.getIdFornecedor() %>" type="text">
			<label for="razaosocial">Razao Social</label>
			<input class="limpavel" id="razaosocial" name="razaosocial" 
			value="<%= fornecedor.getRazaoSocial() == null ? "" : fornecedor.getRazaoSocial() %>" type="text">
			
			<label for="cidade">Cidade</label>
			<input class="limpavel" id="cidade" name="cidade" 
			value="<%= fornecedor.getCidade() == null ? "" : fornecedor.getCidade() %>" type="text">
			
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Consultar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a href="dispatcher?newAction=goNew&entityName=Fornecedor" class="btn btn-default">Novo</a>
			</div>
		</form>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th class="short-cell">Código</th>
				<th>Razao Social</th>
				<th>Logradouro</th>
				<th>Número</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>CEP</th>
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% 
		for(Object obj : list) {
			Fornecedor nFornecedor = (Fornecedor) obj;
		%>
		<tr>
			<td><%= nFornecedor.getIdFornecedor() %></td>
			<td><%= nFornecedor.getRazaoSocial() %></td>
			<td><%= nFornecedor.getLogradouro() %></td>
			<td><%= nFornecedor.getNumero() %></td>
			<td><%= nFornecedor.getBairro() %></td>
			<td><%= nFornecedor.getCidade() %></td>
			<td><%= nFornecedor.getEstado() %></td>
			<td><%= nFornecedor.getCep() %></td>
			<td>
				<a class="btn btn-delete" href="dispatcher?newAction=remove&entityName=Fornecedor&id=<%= nFornecedor.getIdFornecedor() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=detail&entityName=Fornecedor&id=<%= nFornecedor.getIdFornecedor() %>" class="btn btn-edit">Editar</a>
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>