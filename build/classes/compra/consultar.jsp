<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Fornecedor" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Compra" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionFornecedor" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionCompra" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.ItemCompra" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = null;
	
	Compra compra = (Compra) request.getSession().getAttribute("filterCompra");
	if (compra == null) {
		compra = new Compra();
	}
	Object list[] = (Object[]) request.getAttribute("list");
	if (list == null) {
		list = new Object[0];
	}
	
	Object listaFornecedor[] = (Object[]) request.getAttribute("listaFornecedor");
	
	/*String action = request.getParameter("newAction");
	SessionCompra sessionCompra = new SessionCompra();
	
	SessionFornecedor sessionFornecedor = new SessionFornecedor();
	Compra compra = new Compra();
	Object listCompra[] = new Compra[0];
	Fornecedor fornecedor = new Fornecedor();
	Object listaFornecedor[] = sessionFornecedor.find(fornecedor);
	
	if( action != null && !action.equals("") ) {	
		try {
			fornecedor.setIdFornecedor(Integer.parseInt(request.getParameter("idFornecedor")));
		} catch(Exception e) {}
		try {
			compra.setIdCompra(Integer.parseInt(request.getParameter("codigo")));
		} catch(Exception e) {}
		
		compra.setDescricao(request.getParameter("descricao"));
		compra.setFornecedor(fornecedor);
		
		switch (action) {
		case "find":
			listCompra = sessionCompra.find(compra);
			break;
		case "remove":
			try {
				sessionCompra.remove(compra);
				infoMsg = "Remoção efeituada com sucesso";
			} catch (PSQLException e) {
				switch (e.getSQLState()) {
					case "23503":
						errorMsg = "Existe um registro associado a esta fornecedor, portanto não foi possível efetuar a remoção";
						break;
					case "23505":
						errorMsg = "Já existe um compra com essa descrição";
						break;
					default:
						errorMsg = "Houve um erro no momento de concretar a operação";
				}
				errorDetail = e.getMessage();
			}
			compra = new Compra();
		}
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Compra</title>
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
	
	<h1>Consultar Compra</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<input name="entityName" id="entityName" value="Compra" type="hidden">
			
			<label for="idcompra">Código</label>
			<input class="limpavel" id="idcompra" name="idcompra" style="width: 200px;" 
			value="<%= compra.getIdCompra() == null ? "" : compra.getIdCompra() %>" type="text">
			
			<label for="fornecedor">Fornecedor</label><br>
			<select id="fornecedor" class="limpavel" name="fornecedor">
				<option value="">Selecione Fornecedor</option>
				<%
				for(Object nObj : listaFornecedor) { 
					Fornecedor nFornecedor = (Fornecedor) nObj;
				%>
					<option <%= compra.getFornecedor() == null ? "" : (
							compra.getFornecedor().equals(nFornecedor) ? "selected" : ""
							) %> value="<%= nFornecedor.getIdFornecedor() %>">
						<%= nFornecedor.getRazaoSocial() %>
					</option>
				<% } %>
			</select><br>
			
			<label for="descricao">Descrição</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= compra.getDescricao() == null ? "" : compra.getDescricao() %>" type="text">
			
			<label for="data">Data</label>
			<input class="limpavel" id="data" name="data" 
			value="<%= compra.getData() == null ? "" : compra.getData() %>" type="text">
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Consultar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a href="dispatcher?newAction=goNew&entityName=Compra" class="btn btn-default">Novo</a>
			</div>
		</form>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th class="short-cell">Código</th>
				<th>Fornecedor</th>
				<th>Descrição</th>
				<th>Valor</th>
				<th>Data</th>
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% 
		for(Object nObj : list) {
			Compra nCompra = (Compra) nObj;
		%>
		<tr>
			<td><%= nCompra.getIdCompra() %></td>
			<td><%= nCompra.getFornecedor().getRazaoSocial() %></td>
			<td><%= nCompra.getDescricao() %></td>
			<td><%= nCompra.getValor() %></td>
			<td><%= nCompra.getData() %></td>
			<td>
				<a class="btn btn-default" href="dispatcher?newAction=find&entityName=ItemCompra&compra=<%= nCompra.getIdCompra() %>">Ver Ítens</a>
				<a class="btn btn-delete" href="dispatcher?newAction=remove&entityName=Compra&id=<%= nCompra.getIdCompra() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=detail&entityName=Compra&id=<%= nCompra.getIdCompra() %>" class="btn btn-edit">Editar</a>
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>