<%@page import="java.sql.Date"%>
<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Cliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionCliente" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = null;
	
	String action = request.getParameter("newAction");
	
	Cliente cliente = new Cliente();
	
	if( action != null && !action.equals("") ) {	
		try {
			cliente.setIdCliente(Integer.parseInt(request.getParameter("codigo")));
		} catch(Exception e) {}
		cliente.setNome(request.getParameter("nome"));
		cliente.setRg(request.getParameter("rg"));
		cliente.setCpf(request.getParameter("cpf"));
		try {
			cliente.setDataNascimento(Date.valueOf(request.getParameter("data_nascimento")));
		} catch(IllegalArgumentException e) {}
		cliente.setLogradouro(request.getParameter("logradouro"));
		cliente.setNumero(request.getParameter("numero"));
		cliente.setBairro(request.getParameter("bairro"));
		cliente.setCidade(request.getParameter("cidade"));
		cliente.setEstado(request.getParameter("estado"));
		cliente.setCep(request.getParameter("cep"));
		
		switch (action) {
		case "save":
			try {
				SessionCliente.save(cliente);
				infoMsg = "Cliente salvo com sucesso";
			} catch(PSQLException e) {
				errorMsg = "Houve um erro no momento de concretar a operação";
				errorDetail = e.getMessage();
			}
			break;
		case "remove":
			try {
				SessionCliente.remove(cliente);
				infoMsg = "Remoção efeituada com sucesso";
			} catch (PSQLException e) {
				switch (e.getSQLState()) {
					case "23503":
						errorMsg = "Existe um registro associado a este cliente, portanto não foi possível efetuar a remoção";
						break;
					default:
						errorMsg = "Houve um erro no momento de concretar a operação";
				}
				errorDetail = e.getMessage();
			}
			cliente = new Cliente();
			break;
		case "detail":
			Cliente clienteAux = new Cliente();
			try {
				clienteAux.setIdCliente(Integer.parseInt(request.getParameter("codigo")));
				clienteAux = SessionCliente.findByPrimary(clienteAux);
				if (clienteAux != null) {
					cliente = clienteAux;
				}
			} catch(Exception e) {
				errorMsg = "O cliente especificado não foi encontrado.";
			}
			break;
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Cliente</title>
<link rel="stylesheet" href="../css/forms.css">
<link rel="stylesheet" href="../css/styles.css">

<script src="../js/scripts.js"></script>
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
	
	<h1>Manter Cliente</h1>
	<div>
		<form action="manter.jsp" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			<label for="codigo">Código</label>
			<input class="limpavel" id="codigo" name="codigo" style="width: 200px;" 
			value="<%= cliente.getIdCliente() == null ? "" : cliente.getIdCliente() %>" type="text">
			<label for="descricao">Nome</label>
			<input class="limpavel" id="nome" name="nome" 
			value="<%= cliente.getNome() == null ? "" : cliente.getNome() %>" type="text">
			<label for="rg">RG</label>
			<input class="limpavel" id="rg" name="rg" 
			value="<%= cliente.getRg() == null ? "" : cliente.getRg() %>" type="text">
			<label for="cpf">CPF</label>
			<input class="limpavel" id="cpf" name="cpf" 
			value="<%= cliente.getCpf() == null ? "" : cliente.getCpf() %>" type="text">
			<label for="data_nascimento">Data de Nascimento</label>
			<input class="limpavel" id="data_nascimento" name="data_nascimento" 
			value="<%= cliente.getDataNascimento() == null ? "" : cliente.getDataNascimento().toString() %>" type="text">
			<label for="logradouro">Logradouro</label>
			<input class="limpavel" id="logradouro" name="logradouro" 
			value="<%= cliente.getLogradouro() == null ? "" : cliente.getLogradouro() %>" type="text">
			<label for="numero">Número</label>
			<input class="limpavel" id="numero" name="numero" 
			value="<%= cliente.getNumero() == null ? "" : cliente.getNumero() %>" type="text">
			<label for="bairro">Bairro</label>
			<input class="limpavel" id="bairro" name="bairro" 
			value="<%= cliente.getBairro() == null ? "" : cliente.getBairro() %>" type="text">
			
			<label for="cidade">Cidade</label>
			<input class="limpavel" id="cidade" name="cidade" 
			value="<%= cliente.getCidade() == null ? "" : cliente.getCidade() %>" type="text">
			
			<label for="estado">Estado</label>
			<input class="limpavel" id="estado" name="estado" 
			value="<%= cliente.getEstado() == null ? "" : cliente.getEstado() %>" type="text">
			
			<label for="cep">CEP</label>
			<input class="limpavel" id="cep" name="cep" 
			value="<%= cliente.getCep() == null ? "" : cliente.getCep() %>" type="text">
			
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Salvar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a class="btn btn-default" href="manter.jsp?newAction=remove&codigo=<%= cliente.getIdCliente() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="consultar.jsp" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>