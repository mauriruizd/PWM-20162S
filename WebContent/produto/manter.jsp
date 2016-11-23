<%@page import="java.sql.Date"%>
<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Produto" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionProduto" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Produto produto = (Produto) request.getAttribute("object");
	if (produto == null) {
		produto = new Produto();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Produto</title>
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
	
	<h1>Manter Produto</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			<input name="entityName" id="entityName" value="Produto" type="hidden">
			<label for="idproduto">Código</label>
			<input class="limpavel" id="idproduto" name="idproduto" style="width: 200px;" 
			value="<%= produto.getIdProduto() == null ? "" : produto.getIdProduto() %>" type="text" readonly>
			<label for="descricao">Descricao</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= produto.getDescricao() == null ? "" : produto.getDescricao() %>" type="text">
			<label for="valor">Valor</label>
			<input class="limpavel" id="valor" name="valor" 
			value="<%= produto.getValor() == null ? "" : produto.getValor() %>" type="text">
			<label for="quantidade">Quantidade</label>
			<input class="limpavel" id="quantidade" name="quantidade" 
			value="<%= produto.getQuantidade() == null ? "" : produto.getQuantidade() %>" type="text">
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Salvar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a class="btn btn-default" href="dispatcher?newAction=remove&entityName=Produto&id=<%= produto.getIdProduto() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=goFind&entityName=Produto" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>