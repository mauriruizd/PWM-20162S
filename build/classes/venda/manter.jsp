<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Veiculo" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Cliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Venda" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Produto" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionCliente" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionVenda" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Venda venda = (Venda) request.getAttribute("object");
	if (venda == null) {
		venda = new Venda();
	}
	
	Object listaCliente[] = (Object[]) request.getAttribute("listaCliente");
	Object listaProduto[] = (Object[]) request.getAttribute("listaProduto");
	Object listaVeiculo[] = (Object[]) request.getAttribute("listaVeiculo");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Venda</title>
<link rel="stylesheet" href="css/forms.css">
<link rel="stylesheet" href="css/styles.css">

<script src="js/scripts.js"></script>
<script>
	function confirmarExclusao(btn) {
		if (confirm('Confirma exlusao da lista?')) {
			btn.parentNode.parentNode.remove();
		}
	}

	function validate() {
		if ($('#cliente').value == "") {
			alert('Campo "Cliente" obrigatório!');
			return false;
		}
		if ($('#veiculo').value == "") {
			alert('Campo "Veiculo" obrigatório!');
			return false;
		}
		return true;
	}
	
	function generateQtts(max) {
		var sel = $('#selectquantidade');
		var html = '';
		setHTML('');
		
		for(var i = 0; i <= max; i++) {
			html += genOption(i);
		}
		
		setHTML(html);
		
		function setHTML(html) {
			sel.innerHTML = html;
		}
		
		function genOption(option) {
			return '<option value="' + option + '">' + option + '</option>';
		}
	}
	
	function extractQttd(elm) {
		if (elm.selectedIndex == 0) {
			return 0;
		}
		var value = elm.options[elm.selectedIndex].innerHTML;
		var cast = Number(value.substr(value.indexOf('Qtd:') + 5));
		return cast;
	}
	
	function extractProdDescricao(elm) {
		var value = elm.options[elm.selectedIndex].innerHTML;
		return value.substr(0, value.indexOf(' --'));
	}
	
	function updateValor() {
		var elm = $('#valor');
		var subtotais = $('.subtotal');
		var newValor = 0;
		for (var i = 0; i < subtotais.length; i++) {
			newValor += Number(subtotais[i].value);
		}
		elm.value = newValor;
	}
	
	function setData() {
		var dia = prompt("Digite dia em formato dd:");
		var mes = prompt("Digite mes em formato mm:");
		var ano = prompt("Digite ano em formato YYYY:");
		$('#data').value = ano + '-' + mes + '-' + (dia.length == 2 ? dia : '0' + dia);
	}
	
	function addProduto() {
		var qtd = $('#selectquantidade').value;
		if (qtd == 0) {
			return;
		}
		var select = $('#selectproduto');
		var produto = {
				id: select.value,
				descricao: extractProdDescricao($('#selectproduto')),
				valor: Number($('#selectprodutovalor').options[select.selectedIndex].value),
				subtotal: function() { return this.valor * qtd}
		};
		
		var tdHtml = '<td>' + 
		'<input type="hidden" class="itemproduto" name="itens[' + produto.id + ']" value="' + produto.id + '">' + produto.descricao + '</td>' +
		'<td><input type="text" class="shy-input" name="quantidade[' + produto.id + ']" value="' + qtd + '" readonly></td>' +
		'<td><input type="text" class="shy-input" name="valor[' + produto.id + ']" value="' + produto.valor + '" readonly></td>' +
		'<td><input type="text" class="shy-input subtotal" value="' + produto.subtotal() + '" readonly></td>' +
		'<td>' + '<a class="btn btn-delete" onclick="confirmarExclusao(this); updateValor();">Excluir da lista</button>' + '</td>';
		
		var existanceIndex = checkExistance(produto.id);
		if (existanceIndex !== false) {
			if (confirm('Confirma modificar registro da tabela?')) {
				$('tr')[existanceIndex + 1].innerHTML = tdHtml;
			} else {
				return;
			}
		} else {
			$('#tabelaprodutosbody').innerHTML += '<tr>' + tdHtml + '</tr>';
		}
		updateValor();
	}
	
	function checkExistance(id) {
		var listaitens = $('.itemproduto');
		for (var i = 0; i < listaitens.length; i++) {
			if (listaitens[i].value == id) {
				return i;
			}
		}
		return false;
	}
	
</script>
</head>
<body>

	<% if(infoMsg != null) { %>
		<div class="server-msg info-msg">
			<%= infoMsg %>
		</div>
	<% } %>
	
	<h1>Manter Venda</h1>
	<div>
		<form action="dispatcher?newAction=save&entityName=Venda" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			
			<label for="idvenda">Código</label>
			<input class="limpavel" id="idvenda" name="idvenda" style="width: 200px;" 
			value="<%= venda.getIdVenda() == null ? "" : venda.getIdVenda() %>" type="text" readonly>
			
			<label for="cliente">Cliente</label><br>
			<select id="cliente" class="limpavel" name="cliente">
				<option value="">Selecione Cliente</option>
				<% 
				for(Object obj : listaCliente) {
					Cliente nCliente = (Cliente) obj;
				%>
					<option <%= venda.getCliente() == null ? "" : (
							venda.getCliente().equals(nCliente) ? "selected" : ""
							) %> value="<%= nCliente.getIdCliente() %>">
						<%= nCliente.getNome() %>
					</option>
				<% } %>
			</select><br>
			
			<label for="veiculo">Veiculo</label><br>
			<select id="veiculo" class="limpavel" name="veiculo">
				<option value="">Selecione Veiculo</option>
				<% 
				for(Object obj : listaVeiculo) {
					Veiculo nVeiculo = (Veiculo) obj;
				%>
					<option <%= venda.getVeiculo() == null ? "" : (
							venda.getVeiculo().equals(nVeiculo) ? "selected" : ""
							) %> value="<%= nVeiculo.getIdVeiculo() %>">
						<%= nVeiculo.toString() %>
					</option>
				<% } %>
			</select><br>
			
			<label for="descricao">Descrição</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= venda.getDescricao() == null ? "" : venda.getDescricao() %>" type="text" required>
			
			<label for="valor">Valor</label>
			<input class="limpavel" id="valor" name="valor" 
			value="<%= venda.getValor() == null ? "0" : venda.getValor() %>" type="text" required <%= venda.getIdVenda() != null ? "readonly" : "" %>>
			
			<label for="data">Data</label>
			<input class="limpavel" id="data" name="data" onclick="<%= venda.getIdVenda() == null ? "setData()" : "void()" %>"
			value="<%= venda.getData() == null ? "" : venda.getData() %>" type="text" required readonly>
			
			<% if (venda.getIdVenda() == null) { %>
			
			<label for="selectproduto">Produto</label><br>
			<select id="selectproduto" class="limpavel" onchange="generateQtts(extractQttd(this));">
				<option value="">Selecione Produto</option>
				<% 	
				for(Object obj : listaProduto) {
					Produto nProduto = (Produto) obj;
				%>
					<option value="<%= nProduto.getIdProduto() %>"><%= nProduto.getDescricao() %> -- Qtd: <%= nProduto.getQuantidade() %></option>
				<% } %>
			</select><br>
			
			<label for="selectquantidade">Quantidade</label><br>
			<select id="selectquantidade" class="limpavel">
				<option value="">0</option>
			</select><br>
			
			<select id="selectprodutovalor" style="display: none">
				<option value="">0</option>
				<% 	
				for(Object obj : listaProduto) {
					Produto nProduto = (Produto) obj;
				%>
					<option value="<%= nProduto.getValor() %>"></option>
				<% } %>
			</select>
			<button type="button" id="addproduto" onclick="addProduto()">Adicionar produto</button>
			
			<label for="selectprodutoservico">Produto</label><br>
			<select id="selectprodutoservico" class="limpavel" onchange="generateQtts(extractQttd(this));">
				<option value="">Selecione Produto</option>
				<% 	
				for(Object obj : listaProduto) {
					Produto nProduto = (Produto) obj;
				%>
					<option value="<%= nProduto.getIdProduto() %>"><%= nProduto.getDescricao() %> -- Qtd: <%= nProduto.getQuantidade() %></option>
				<% } %>
			</select><br>
			
			<label for="selectquantidadeservico">Quantidade</label><br>
			<select id="selectquantidadeservico" class="limpavel">
				<option value="">0</option>
			</select><br>
			
			<select id="selectprodutovalorservico" style="display: none">
				<option value="">0</option>
				<% 	
				for(Object obj : listaProduto) {
					Produto nProduto = (Produto) obj;
				%>
					<option value="<%= nProduto.getValor() %>"></option>
				<% } %>
			</select>
			<button type="button" id="addservico" onclick="addProduto()">Adicionar serviço</button>
			
			<h2>Produtos</h2>
			<table id="tabelaprodutos">
				<thead>
					<tr>
					<th>Produto</th>
					<th>Quantidade</th>
					<th>Valor Unitário</th>
					<th>Valor</th>
					<th class="medium-cell">Ações</th>
					</tr>
				</thead>
				<tbody id="tabelaprodutosbody">
				</tbody>
			</table>
			<% } %>
			<div id="buttons-container">
				<button type="submit" class="btn btn-default">Salvar</button>
				<button type="button" class="btn btn-default" onclick="clean('.limpavel')">Limpar</button>
				<a class="btn btn-default" href="dispatcher?newAction=remove&entityName=Venda&idVenda=<%= venda.getIdVenda() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=goFind&entityName=Venda" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>