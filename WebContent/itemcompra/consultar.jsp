<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.ItemCompra" %>
<%
	Object list[] = (Object[]) request.getAttribute("list");
	if (list == null) {
		list = new Object[0];
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Itens de Compra</title>
<link rel="stylesheet" href="css/forms.css">
<link rel="stylesheet" href="css/styles.css">

<script src="js/scripts.js"></script>
</head>
<body>
	<a class="btn btn-default" href="javascript:history.back()">Voltar</a>	
	<h1>Ítens de compra</h1>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th>Produto</th>
				<th>Quantidade</th>
				<th>Valor</th>
			</tr>
		</thead>
		<tbody>
		<% 
		for(Object nObj : list) {
			ItemCompra nItemCompra = (ItemCompra) nObj;
		%>
		<tr>
			<td><%= nItemCompra.getProduto().getDescricao() %></td>
			<td><%= nItemCompra.getQuantidade() %></td>
			<td><%= nItemCompra.getValor() %></td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>