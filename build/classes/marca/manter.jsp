<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Marca" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionMarca" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Marca marca = (Marca) request.getAttribute("object");
	if (marca == null) {
		marca = new Marca();
	}
	
	/*String action = request.getParameter("newAction");
	
	Marca marca = new Marca();
	
	if( action != null && !action.equals("") ) {
		SessionMarca sessionMarca = new SessionMarca();
		try {
			marca.setIdMarca(Integer.parseInt(request.getParameter("codigo")));
		} catch(Exception e) {}
		marca.setDescricao(request.getParameter("descricao"));
		
		switch (action) {
		case "save":
			try {
				sessionMarca.save(marca);
				infoMsg = "Marca salva com sucesso";
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
						errorMsg = "Existe um registro associado a esta marca, portanto não foi possível efetuar a remoção";
						break;
					case "23505":
						errorMsg = "Já existe uma marca com essa descrição";
						break;
					default:
						errorMsg = "Houve um erro no momento de concretar a operação";
				}
				errorDetail = e.getMessage();
			}
			marca = new Marca();
		case "detail":
			Marca marcaAux = new Marca();
			try {
				marcaAux.setIdMarca(Integer.parseInt(request.getParameter("codigo")));
				marcaAux = (Marca) sessionMarca.findByPrimary(marcaAux);
				if (marcaAux != null) {
					marca = marcaAux;
				}
			} catch(Exception e) {
				errorMsg = "A marca especificada não foi encontrada.";
			}
			break;
		}
	}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Marca</title>
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
	
	<h1>Manter Marca</h1>
	<div>
		<form action="dispatcher?newAction=save&entityName=Marca" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			<label for="codigo">Código</label>
			<input class="limpavel" id="idMarca" name="idMarca" style="width: 200px;" 
			value="<%= marca.getIdMarca() == null ? "" : marca.getIdMarca() %>" type="text" readonly>
			<label for="descricao">Descrição</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= marca.getDescricao() == null ? "" : marca.getDescricao() %>" type="text" required>
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Salvar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a class="btn btn-default" href="dispatcher?newAction=remove&entityName=Marca&idMarca=<%= marca.getIdMarca() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=goFind&entityName=Marca" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>