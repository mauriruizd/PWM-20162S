<%@page import="java.sql.Date"%>
<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Cliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionCliente" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Cliente cliente = (Cliente) request.getAttribute("object");
	if (cliente == null) {
		cliente = new Cliente();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Cliente</title>
<link rel="stylesheet" href="css/forms.css">
<link rel="stylesheet" href="css/styles.css">

<script src="js/scripts.js"></script>
<script>
	function confirmarExclusao() {
		return $('#codigo').value != "" && confirm('Confirma exclusao?');
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
	
	<h1>Manter Cliente</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			<input name="entityName" id="entityName" value="Cliente" type="hidden">
			<label for="idcliente">Código</label>
			<input class="limpavel" id="idcliente" name="idcliente" style="width: 200px;" 
			value="<%= cliente.getIdCliente() == null ? "" : cliente.getIdCliente() %>" type="text" readonly>
			<label for="descricao">Nome</label>
			<input class="limpavel" id="nome" name="nome" 
			value="<%= cliente.getNome() == null ? "" : cliente.getNome() %>" type="text">
			<label for="rg">RG</label>
			<input class="limpavel" id="rg" name="rg" 
			value="<%= cliente.getRg() == null ? "" : cliente.getRg() %>" type="text">
			<label for="cpf">CPF</label>
			<input class="limpavel" id="cpf" name="cpf" 
			value="<%= cliente.getCpf() == null ? "" : cliente.getCpf() %>" type="text">
			<label for="data_nascimento">Data de Nascimento</label>
			<input class="limpavel" id="datanascimento" name="datanascimento" 
			value="<%= cliente.getDataNascimento() == null ? "" : cliente.getDataNascimento() %>" type="text">
			<label for="logradouro">Logradouro</label>
			<input class="limpavel" id="logradouro" name="logradouro" 
			value="<%= cliente.getLogradouro() == null ? "" : cliente.getLogradouro() %>" type="text">
			<label for="numero">Número</label>
			<input class="limpavel" id="numero" name="numero" 
			value="<%= cliente.getNumero() == null ? "" : cliente.getNumero() %>" type="text">
			<label for="bairro">Bairro</label>
			<input class="limpavel" id="bairro" name="bairro" 
			value="<%= cliente.getBairro() == null ? "" : cliente.getBairro() %>" type="text">
			
			<label for="cidade">Cidade</label>
			<input class="limpavel" id="cidade" name="cidade" 
			value="<%= cliente.getCidade() == null ? "" : cliente.getCidade() %>" type="text">
			
			<label for="estado">Estado</label>
			<input class="limpavel" id="estado" name="estado" 
			value="<%= cliente.getEstado() == null ? "" : cliente.getEstado() %>" type="text">
			
			<label for="cep">CEP</label>
			<input class="limpavel" id="cep" name="cep" 
			value="<%= cliente.getCep() == null ? "" : cliente.getCep() %>" type="text">
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Salvar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a class="btn btn-default" href="dispatcher?newAction=remove&entityName=Cliente&id=<%= cliente.getIdCliente() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=goFind&entityName=Cliente" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>