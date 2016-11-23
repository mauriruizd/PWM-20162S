<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Cliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Venda" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionCliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionVenda" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.ItemVenda" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = null;
	
	Venda compra = (Venda) request.getSession().getAttribute("filterVenda");
	if (compra == null) {
		compra = new Venda();
	}
	Object list[] = (Object[]) request.getAttribute("list");
	if (list == null) {
		list = new Object[0];
	}
	
	Object listaCliente[] = (Object[]) request.getAttribute("listaCliente");
	
	/*String action = request.getParameter("newAction");
	SessionVenda sessionVenda = new SessionVenda();
	
	SessionCliente sessionCliente = new SessionCliente();
	Venda compra = new Venda();
	Object listVenda[] = new Venda[0];
	Cliente cliente = new Cliente();
	Object listaCliente[] = sessionCliente.find(cliente);
	
	if( action != null && !action.equals("") ) {	
		try {
			cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
		} catch(Exception e) {}
		try {
			compra.setIdVenda(Integer.parseInt(request.getParameter("codigo")));
		} catch(Exception e) {}
		
		compra.setDescricao(request.getParameter("descricao"));
		compra.setCliente(cliente);
		
		switch (action) {
		case "find":
			listVenda = sessionVenda.find(compra);
			break;
		case "remove":
			try {
				sessionVenda.remove(compra);
				infoMsg = "Remoção efeituada com sucesso";
			} catch (PSQLException e) {
				switch (e.getSQLState()) {
					case "23503":
						errorMsg = "Existe um registro associado a esta cliente, portanto não foi possível efetuar a remoção";
						break;
					case "23505":
						errorMsg = "Já existe um compra com essa descrição";
						break;
					default:
						errorMsg = "Houve um erro no momento de concretar a operação";
				}
				errorDetail = e.getMessage();
			}
			compra = new Venda();
		}
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Venda</title>
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
	
	<h1>Consultar Venda</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<input name="entityName" id="entityName" value="Venda" type="hidden">
			
			<label for="idcompra">Código</label>
			<input class="limpavel" id="idcompra" name="idcompra" style="width: 200px;" 
			value="<%= compra.getIdVenda() == null ? "" : compra.getIdVenda() %>" type="text">
			
			<label for="cliente">Cliente</label><br>
			<select id="cliente" class="limpavel" name="cliente">
				<option value="">Selecione Cliente</option>
				<%
				for(Object nObj : listaCliente) { 
					Cliente nCliente = (Cliente) nObj;
				%>
					<option <%= compra.getCliente() == null ? "" : (
							compra.getCliente().equals(nCliente) ? "selected" : ""
							) %> value="<%= nCliente.getIdCliente() %>">
						<%= nCliente.getNome() %>
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
				<a href="dispatcher?newAction=goNew&entityName=Venda" class="btn btn-default">Novo</a>
			</div>
		</form>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th class="short-cell">Código</th>
				<th>Cliente</th>
				<th>Descrição</th>
				<th>Valor</th>
				<th>Data</th>
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% 
		for(Object nObj : list) {
			Venda nVenda = (Venda) nObj;
		%>
		<tr>
			<td><%= nVenda.getIdVenda() %></td>
			<td><%= nVenda.getCliente().getNome() %></td>
			<td><%= nVenda.getDescricao() %></td>
			<td><%= nVenda.getValor() %></td>
			<td><%= nVenda.getData() %></td>
			<td>
				<a class="btn btn-default" href="dispatcher?newAction=find&entityName=ItemVenda&compra=<%= nVenda.getIdVenda() %>">Ver Ítens</a>
				<a class="btn btn-delete" href="dispatcher?newAction=remove&entityName=Venda&id=<%= nVenda.getIdVenda() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=detail&entityName=Venda&id=<%= nVenda.getIdVenda() %>" class="btn btn-edit">Editar</a>
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>