<%@page import="java.util.Calendar"%>
<%@page import="org.postgresql.util.PSQLException"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Cliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionCliente" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Cliente cliente = (Cliente) request.getSession().getAttribute("filterCliente");
	if (cliente == null) {
		cliente = new Cliente();
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
<title>Consultar Cliente</title>
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
	
	<h1>Consultar Cliente</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<input name="entityName" id="entityName" value="Cliente" type="hidden">
			<label for="idcliente">Código</label>
			<input class="limpavel" id="idcliente" name="idcliente" style="width: 200px;" 
			value="<%= cliente.getIdCliente() == null ? "" : cliente.getIdCliente() %>" type="text">
			<label for="descricao">Nome</label>
			<input class="limpavel" id="nome" name="nome" 
			value="<%= cliente.getNome() == null ? "" : cliente.getNome() %>" type="text">
			<label for="rg">RG</label>
			<input class="limpavel" id="rg" name="rg" 
			value="<%= cliente.getRg() == null ? "" : cliente.getRg() %>" type="text">
			<label for="cpf">CPF</label>
			<input class="limpavel" id="cpf" name="cpf" 
			value="<%= cliente.getCpf() == null ? "" : cliente.getCpf() %>" type="text">
			<label for="cidade">Cidade</label>
			<input class="limpavel" id="cidade" name="cidade" 
			value="<%= cliente.getCidade() == null ? "" : cliente.getCidade() %>" type="text">
			
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Consultar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a href="dispatcher?newAction=goNew&entityName=Cliente" class="btn btn-default">Novo</a>
			</div>
		</form>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th class="short-cell">Código</th>
				<th>Nome</th>
				<th>RG</th>
				<th>CPF</th>
				<th>Data de nascimento</th>
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
			Cliente nCliente = (Cliente) obj;
		%>
		<tr>
			<td><%= nCliente.getIdCliente() %></td>
			<td><%= nCliente.getNome() %></td>
			<td><%= nCliente.getRg() %></td>
			<td><%= nCliente.getCpf() %></td>
			<td><%= nCliente.getDataNascimento() %></td>
			<td><%= nCliente.getLogradouro() %></td>
			<td><%= nCliente.getNumero() %></td>
			<td><%= nCliente.getBairro() %></td>
			<td><%= nCliente.getCidade() %></td>
			<td><%= nCliente.getEstado() %></td>
			<td><%= nCliente.getCep() %></td>
			<td>
				<a class="btn btn-delete" href="dispatcher?newAction=remove&entityName=Cliente&id=<%= nCliente.getIdCliente() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=detail&entityName=Cliente&id=<%= nCliente.getIdCliente() %>" class="btn btn-edit">Editar</a>
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>