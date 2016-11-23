<%@page import="java.sql.Date"%>
<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Fornecedor" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionFornecedor" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Fornecedor fornecedor = (Fornecedor) request.getAttribute("object");
	if (fornecedor == null) {
		fornecedor = new Fornecedor();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Fornecedor</title>
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
	
	<h1>Manter Fornecedor</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			<input name="entityName" id="entityName" value="Fornecedor" type="hidden">
			<label for="idfornecedor">Código</label>
			<input class="limpavel" id="idfornecedor" name="idfornecedor" style="width: 200px;" 
			value="<%= fornecedor.getIdFornecedor() == null ? "" : fornecedor.getIdFornecedor() %>" type="text" readonly>
			<label for="razaosocial">Razao Social</label>
			<input class="limpavel" id="razaosocial" name="razaosocial" 
			value="<%= fornecedor.getRazaoSocial() == null ? "" : fornecedor.getRazaoSocial() %>" type="text">
			<label for="logradouro">Logradouro</label>
			<input class="limpavel" id="logradouro" name="logradouro" 
			value="<%= fornecedor.getLogradouro() == null ? "" : fornecedor.getLogradouro() %>" type="text">
			<label for="numero">Número</label>
			<input class="limpavel" id="numero" name="numero" 
			value="<%= fornecedor.getNumero() == null ? "" : fornecedor.getNumero() %>" type="text">
			<label for="bairro">Bairro</label>
			<input class="limpavel" id="bairro" name="bairro" 
			value="<%= fornecedor.getBairro() == null ? "" : fornecedor.getBairro() %>" type="text">
			
			<label for="cidade">Cidade</label>
			<input class="limpavel" id="cidade" name="cidade" 
			value="<%= fornecedor.getCidade() == null ? "" : fornecedor.getCidade() %>" type="text">
			
			<label for="estado">Estado</label>
			<input class="limpavel" id="estado" name="estado" 
			value="<%= fornecedor.getEstado() == null ? "" : fornecedor.getEstado() %>" type="text">
			
			<label for="cep">CEP</label>
			<input class="limpavel" id="cep" name="cep" 
			value="<%= fornecedor.getCep() == null ? "" : fornecedor.getCep() %>" type="text">
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Salvar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a class="btn btn-default" href="dispatcher?newAction=remove&entityName=Fornecedor&id=<%= fornecedor.getIdFornecedor() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=goFind&entityName=Fornecedor" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>