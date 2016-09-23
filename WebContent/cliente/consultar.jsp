<%@page import="java.util.Calendar"%>
<%@page import="org.postgresql.util.PSQLException"%>
<%@page import="java.sql.Date"%>
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
	Cliente listaCliente[] = new Cliente[0];
	
	if(action != null && !action.equals("")) {
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
		
		switch(action) {
		case "find":
			listaCliente = SessionCliente.find(cliente);
			break;
		case "remove":
			try {
				SessionCliente.remove(cliente);
				infoMsg = "Remoção efeituada com sucesso";
			} catch(PSQLException e) {
				switch(e.getSQLState()) {
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
			cliente = new Cliente();
			break;
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Cliente</title>
<link rel="stylesheet" href="../css/forms.css">
<link rel="stylesheet" href="../css/styles.css">

<script src="../js/scripts.js"></script>
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
	
	<h1>Consultar Cliente</h1>
	<div>
		<form action="consultar.jsp" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
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
				<button type="submit" class="btn btn-default">Consultar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a href="manter.jsp" class="btn btn-default">Novo</a>
			</div>
		</form>
	</div>
	<div id="results-container">
	<table>
		<thead>
			<tr>
				<th class="short-cell">Código</th>
				<th>Nome</th>
				<th>RG</th>
				<th>CPF</th>
				<th>Data de nascimento</th>
				<th>Logradouro</th>
				<th>Número</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>CEP</th>
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		<% for(Cliente nCliente : listaCliente) { %>
		<tr>
			<td><%= nCliente.getIdCliente() %></td>
			<td><%= nCliente.getNome() %></td>
			<td><%= nCliente.getRg() %></td>
			<td><%= nCliente.getCpf() %></td>
			<td><%= nCliente.getDataNascimento().getDate() + "/" + 
				nCliente.getDataNascimento().getMonth() + "/" + 
				nCliente.getDataNascimento().getYear() %></td>
			<td><%= nCliente.getLogradouro() %></td>
			<td><%= nCliente.getNumero() %></td>
			<td><%= nCliente.getBairro() %></td>
			<td><%= nCliente.getCidade() %></td>
			<td><%= nCliente.getEstado() %></td>
			<td><%= nCliente.getCep() %></td>
			<td>
				<a class="btn btn-delete" href="consultar.jsp?newAction=remove&codigo=<%= nCliente.getIdCliente() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="manter.jsp?newAction=detail&codigo=<%= nCliente.getIdCliente() %>" class="btn btn-edit">Editar</a>
			</td>
		</tr>
		<% } %>		
		</tbody>
	</table>
	</div>
</body>
</html>