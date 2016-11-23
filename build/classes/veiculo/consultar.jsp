<%@page import="org.postgresql.util.PSQLException"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Modelo" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Cliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Veiculo" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionModelo" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionCliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionVeiculo" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Veiculo veiculo = (Veiculo) request.getSession().getAttribute("filterVeiculo");
	if (veiculo == null) {
		veiculo = new Veiculo();
	}
	Object list[] = (Object[]) request.getAttribute("list");
	if (list == null) {
		list = new Object[0];
	}
	
	Object listCliente[] = (Object[]) request.getAttribute("listaCliente");
	Object listModelo[] = (Object[]) request.getAttribute("listaModelo");
	
	
	/*String action = request.getParameter("newAction");
	
	Modelo modelo = new Modelo();
	Modelo listModelo[] = new Modelo[0];
	Cliente cliente = new Cliente();
	Cliente listCliente[] = new Cliente[0];
	Veiculo veiculo = new Veiculo();
	Veiculo listVeiculo[] = new Veiculo[0];
	
	listCliente = SessionCliente.find(cliente);
	listModelo = SessionModelo.find(modelo);
	
	if(action != null && !action.equals("")) {
		try {
			veiculo.setIdVeiculo(Integer.parseInt(request.getParameter("codigo")));
		} catch(Exception e) {}
		try {
			modelo.setIdModelo(Integer.parseInt(request.getParameter("idModelo")));
		} catch(Exception e) {}
		try {
			cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
		} catch(Exception e) {}
		veiculo.setPlaca(request.getParameter("placa"));
		veiculo.setCor(request.getParameter("cor"));
		try {
			veiculo.setAno(Integer.parseInt(request.getParameter("ano")));
		} catch(Exception e) {}
		veiculo.setModelo(modelo);
		veiculo.setCliente(cliente);
		
		switch(action) {
		case "find":
			listVeiculo = SessionVeiculo.find(veiculo);
			break;
		case "remove":
			try {
				SessionVeiculo.remove(veiculo);
				infoMsg = "Remoção efeituada com sucesso";
			} catch(PSQLException e) {
				switch(e.getSQLState()) {
				case "23503":
					errorMsg = "Existe um registro associado a este veículo, portanto não foi possível efetuar a remoção";
					break;
				default:
					errorMsg = "Houve um erro no momento de concretar a operação";
				}
				errorDetail = e.getMessage();
			}
			veiculo = new Veiculo();
			break;
		}
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Veículo</title>
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
	
	<h1>Consultar Veículo</h1>
	<div>
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<input name="entityName" id="entityName" value="Veiculo" type="hidden">
			<label for="idveiculo">Código</label>
			<input class="limpavel" id="idveiculo" name="idveiculo" style="width: 200px;" 
			value="<%= veiculo.getIdVeiculo() == null ? "" : veiculo.getIdVeiculo() %>" type="text">
			<label for="placa">Placa</label>
			<input class="limpavel" id="placa" name="placa" 
			value="<%= veiculo.getPlaca() == null ? "" : veiculo.getPlaca() %>" type="text">
			<label for="cor">Cor</label>
			<input class="limpavel" id="cor" name="cor" 
			value="<%= veiculo.getCor() == null ? "" : veiculo.getCor() %>" type="text">
			<label for="ano">Ano</label>
			<input class="limpavel" id="ano" name="ano" 
			value="<%= veiculo.getAno() == null ? "" : veiculo.getAno() %>" type="text">
			
			<label for="cliente">Cliente</label><br>
			<select id="cliente" class="limpavel" name="cliente">
				<option value="">Selecione Cliente</option>
				<% 
				for(Object obj : listCliente) {
					Cliente nCliente = (Cliente) obj;
				%>
					<option <%= veiculo.getCliente() == null ? "" : (
							veiculo.getCliente().equals(nCliente) ? "selected" : ""
							) %> value="<%= nCliente.getIdCliente() %>">
						<%= nCliente.getNome() %>
					</option>
				<% } %>
			</select><br>
			
			<label for="modelo">Modelo</label><br>
			<select id="modelo" class="limpavel" name="modelo">
				<option value="">Selecione Modelo</option>
				<% 
				for(Object obj : listModelo) {
					Modelo nModelo = (Modelo) obj;
				%>
					<option <%= veiculo.getModelo() == null ? "" : (
							veiculo.getModelo().equals(nModelo) ? "selected" : ""
							) %> value="<%= nModelo.getIdModelo() %>">
						<%= nModelo.toString() %>
					</option>
				<% } %>
			</select><br>
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Consultar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a href="dispatcher?newAction=goNew&entityName=Veiculo" class="btn btn-default">Novo</a>
			</div>
		</form>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th class="short-cell">Código</th>
				<th>Placa</th>
				<th>Cor</th>
				<th>Ano</th>
				<th>Cliente</th>
				<th>Modelo</th>
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% 
		for(Object obj : list) {
			Veiculo nVeiculo = (Veiculo) obj;
		%>
		<tr>
			<td><%= nVeiculo.getIdVeiculo() %></td>
			<td><%= nVeiculo.getPlaca() %></td>
			<td><%= nVeiculo.getCor() %></td>
			<td><%= nVeiculo.getAno() %></td>
			<td><%= nVeiculo.getCliente().getNome() %></td>
			<td><%= nVeiculo.getModelo().getDescricao() %></td>
			<td>
				<a class="btn btn-delete" href="dispatcher?newAction=remove&entityName=Veiculo&id=<%= nVeiculo.getIdVeiculo() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=detail&entityName=Veiculo&id=<%= nVeiculo.getIdVeiculo() %>" class="btn btn-edit">Editar</a>
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>