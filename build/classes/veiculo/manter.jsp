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
	String infoMsg = (String) request.getAttribute("msg");
	
	Veiculo veiculo = (Veiculo) request.getAttribute("object");
	if (veiculo == null) {
		veiculo = new Veiculo();
	}
	
	Object listCliente[] = (Object[]) request.getAttribute("listaCliente");
	Object listModelo[] = (Object[]) request.getAttribute("listaModelo");
	
	/*Modelo listaModelo[] = SessionModelo.find(modelo);
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
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Veículo</title>
<link rel="stylesheet" href="css/forms.css">
<link rel="stylesheet" href="css/styles.css">

<script src="js/scripts.js"></script>
<script>
	function confirmarExclusao() {
		return $('#codigo').value != "" && confirm('Confirma exclusao?');
	}

	function validate() {
		if ($('#cliente').value == "") {
			alert('Campo "Cliente" obrigatório!');
			return false;
		}
		if ($('#modelo').value == "") {
			alert('Campo "Modelo" obrigatório!');
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
		<form action="dispatcher" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			<input name="entityName" id="entityName" value="Veiculo" type="hidden">
			<label for="idveiculo">Código</label>
			<input class="limpavel" id="idveiculo" name="idveiculo" style="width: 200px;" 
			value="<%= veiculo.getIdVeiculo() == null ? "" : veiculo.getIdVeiculo() %>" type="text" readonly>
			<label for="placa">Placa</label>
			<input class="limpavel" id="placa" name="placa" 
			value="<%= veiculo.getPlaca() == null ? "" : veiculo.getPlaca() %>" type="text" required>
			<label for="cor">Cor</label>
			<input class="limpavel" id="cor" name="cor" 
			value="<%= veiculo.getCor() == null ? "" : veiculo.getCor() %>" type="text" required>
			<label for="ano">Ano</label>
			<input class="limpavel" id="ano" name="ano" 
			value="<%= veiculo.getAno() == null ? "" : veiculo.getAno() %>" type="text" required>
			
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
				<button type="submit" class="btn btn-default">Salvar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a class="btn btn-default" href="dispatcher?newAction=remove&entityName=Veiculo&id=<%= veiculo.getIdVeiculo() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=goFind&entityName=Veiculo" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>