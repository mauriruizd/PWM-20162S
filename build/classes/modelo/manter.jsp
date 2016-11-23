<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Marca" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Modelo" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionMarca" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionModelo" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Modelo modelo = (Modelo) request.getAttribute("object");
	if (modelo == null) {
		modelo = new Modelo();
	}
	
	Object listaMarca[] = (Object[]) request.getAttribute("listaMarca");
	
	/*String action = request.getParameter("newAction");
	
	SessionMarca sessionMarca = new SessionMarca();
	SessionModelo sessionModelo = new SessionModelo();
	Modelo modelo = new Modelo();
	Marca marca = new Marca();
	Object listaMarca[] = sessionMarca.find(marca);
	
	if( action != null && !action.equals("") ) {	
		try {
			marca.setIdMarca(Integer.parseInt(request.getParameter("idMarca")));
		} catch(Exception e) {}
		try {
			modelo.setIdModelo(Integer.parseInt(request.getParameter("codigo")));
		} catch(Exception e) {}
		
		modelo.setMarca(marca);
		modelo.setDescricao(request.getParameter("descricao"));
		
		switch (action) {
		case "save":
			try {
				sessionModelo.save(modelo);
				infoMsg = "Modelo salvo com sucesso";
			} catch(PSQLException e) {
				errorMsg = "Houve um erro no momento de concretar a operação";
				errorDetail = e.getMessage();
			}
			break;
		case "remove":
			try {
				sessionMarca.remove(marca);
				infoMsg = "Remoção efeituada com sucesso";
			} catch (PSQLException e) {
				switch (e.getSQLState()) {
					case "23503":
						errorMsg = "Existe um registro associado a este modelo, portanto não foi possível efetuar a remoção";
						break;
					case "23505":
						errorMsg = "Já existe um modelo com essa descrição";
						break;
					default:
						errorMsg = "Houve um erro no momento de concretar a operação";
				}
				errorDetail = e.getMessage();
			}
			marca = new Marca();
			break;
		case "detail":
			Modelo modeloAux = new Modelo();
			try {
				modeloAux.setIdModelo(Integer.parseInt(request.getParameter("codigo")));
				modeloAux = (Modelo) sessionModelo.findByPrimary(modeloAux);
				if (modeloAux != null) {
					modelo = modeloAux;
				}
			} catch(Exception e) {
				errorMsg = "O modelo especificado não foi encontrado.";
			}
			break;
		}
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Modelo</title>
<link rel="stylesheet" href="css/forms.css">
<link rel="stylesheet" href="css/styles.css">

<script src="js/scripts.js"></script>
<script>
	function confirmarExclusao() {
		return $('#codigo').value != "" && confirm('Confirma exclusao?');
	}

	function validate() {
		if ($('#idMarca').value == "") {
			alert('Campo "Marca" obrigatório!');
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
	
	<h1>Manter Modelo</h1>
	<div>
		<form action="dispatcher?newAction=save&entityName=Modelo" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			<label for="idmodelo">Código</label>
			<input class="limpavel" id="idmodelo" name="idmodelo" style="width: 200px;" 
			value="<%= modelo.getIdModelo() == null ? "" : modelo.getIdModelo() %>" type="text" readonly>
			<label for="descricao">Descrição</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= modelo.getDescricao() == null ? "" : modelo.getDescricao() %>" type="text" required>
			<label for="marca">Marca</label><br>
			<select id="marca" class="limpavel" name="marca">
				<option value="">Selecione Marca</option>
				<% 
				for(Object obj : listaMarca) {
					Marca nMarca = (Marca) obj;
				%>
					<option <%= modelo.getMarca() == null ? "" : (
							modelo.getMarca().equals(nMarca) ? "selected" : ""
							) %> value="<%= nMarca.getIdMarca() %>">
						<%= nMarca.getDescricao() %>
					</option>
				<% } %>
			</select>
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Salvar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a class="btn btn-default" href="dispatcher?newAction=remove&entityName=Modelo&idModelo=<%= modelo.getIdModelo() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=goFind&entityName=Modelo" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>