<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Cliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Modelo" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Veiculo" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionCliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionModelo" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionVeiculo" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = null;
	
	String action = request.getParameter("newAction");
	
	Veiculo veiculo = new Veiculo();
	Modelo modelo = new Modelo();
	Cliente cliente = new Cliente();
	Modelo listaModelo[] = SessionModelo.find(modelo);
	Cliente listaCliente[] = SessionCliente.find(cliente);
			
	if( action != null && !action.equals("") ) {
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
		
		switch (action) {
		case "save":
			try {
				SessionVeiculo.save(veiculo);
				infoMsg = "veículo salvo com sucesso";
			} catch(PSQLException e) {
				errorMsg = "Houve um erro no momento de concretar a operação";
				errorDetail = e.getMessage();
			}
			break;
		case "remove":
			try {
				SessionVeiculo.remove(veiculo);
				infoMsg = "Remoção efeituada com sucesso";
			} catch (PSQLException e) {
				switch (e.getSQLState()) {
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
		case "detail":
			Veiculo veiculoAux = new Veiculo();
			try {
				veiculoAux.setIdVeiculo(Integer.parseInt(request.getParameter("codigo")));
				veiculoAux = SessionVeiculo.findByPrimary(veiculoAux);
				if (veiculoAux != null) {
					veiculo = veiculoAux;
				}
			} catch(Exception e) {
				errorMsg = "O veículo especificado não foi encontrado.";
			}
			break;
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Veículo</title>
<link rel="stylesheet" href="../css/forms.css">
<link rel="stylesheet" href="../css/styles.css">

<script src="../js/scripts.js"></script>
<script>
	function confirmarExclusao() {
		return $('#codigo').value != "" && confirm('Confirma exclusao?');
	}

	function validate() {
		if ($('#idModelo').value == "") {
			alert('Campo "Modelo" obrigatório!');
			return false;
		}
		if ($('#idCliente').value == "") {
			alert('Campo "Cliente" obrigatório!');
			return false;
		}
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
	
	<h1>Manter Veículo</h1>
	<div>
		<form action="manter.jsp" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			<label for="codigo">Código</label>
			<input class="limpavel" id="codigo" name="codigo" style="width: 200px;" 
			value="<%= veiculo.getIdVeiculo() == null ? "" : veiculo.getIdVeiculo() %>" type="text" readonly>
			<label for="placa">Placa</label>
			<input class="limpavel" id="placa" name="placa" 
			value="<%= veiculo.getPlaca() == null ? "" : veiculo.getPlaca() %>" type="text">
			<label for="cor">Cor</label>
			<input class="limpavel" id="cor" name="cor" 
			value="<%= veiculo.getCor() == null ? "" : veiculo.getCor() %>" type="text">
			<label for="ano">Ano</label>
			<input class="limpavel" id="ano" name="ano" 
			value="<%= veiculo.getAno() == null ? "" : veiculo.getAno() %>" type="text">
			
			<label for="idCliente">Cliente</label><br>
			<select id="idCliente" class="limpavel" name="idCliente">
				<option value="">Selecione Cliente</option>
				<% for(Cliente nCliente : listaCliente) { %>
					<option <%= veiculo.getCliente() == null ? "" : (
							veiculo.getCliente().equals(nCliente) ? "selected" : ""
							) %> value="<%= nCliente.getIdCliente() %>">
						<%= nCliente.getNome() %>
					</option>
				<% } %>
			</select><br>
			
			<label for="idModelo">Modelo</label><br>
			<select id="idModelo" class="limpavel" name="idModelo">
				<option value="">Selecione Modelo</option>
				<% for(Modelo nModelo : listaModelo) { %>
					<option <%= veiculo.getModelo() == null ? "" : (
							veiculo.getModelo().equals(nModelo) ? "selected" : ""
							) %> value="<%= nModelo.getIdModelo() %>">
						<%= nModelo.toString() %>
					</option>
				<% } %>
			</select><br>
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Salvar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a class="btn btn-default" href="manter.jsp?newAction=remove&codigo=<%= veiculo.getIdVeiculo() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="consultar.jsp" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>