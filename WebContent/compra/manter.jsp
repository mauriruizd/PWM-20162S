<%@page import="org.postgresql.util.PSQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Fornecedor" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Compra" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.entity.Produto" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionFornecedor" %>
<%@ page import = "br.edu.udc.sistemas.poo2_20161S.session.SessionCompra" %>
<%
	String errorMsg = null;
	String errorDetail = null;
	String infoMsg = (String) request.getAttribute("msg");
	
	Compra compra = (Compra) request.getAttribute("object");
	if (compra == null) {
		compra = new Compra();
	}
	
	Object listaFornecedor[] = (Object[]) request.getAttribute("listaFornecedor");
	Object listaProduto[] = (Object[]) request.getAttribute("listaProduto");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manter Compra</title>
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
		if ($('#fornecedor').value == "") {
			alert('Campo "Fornecedor" obrigatório!');
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
	
	function validateNumber(e) {
		var unicode = e.keyCode ? e.keyCode : e.charCode;
		return unicode >= 48 && unicode <= 57;
	}
	
</script>
</head>
<body>

	<% if(infoMsg != null) { %>
		<div class="server-msg info-msg">
			<%= infoMsg %>
		</div>
	<% } %>
	
	<h1>Manter Compra</h1>
	<div>
		<form action="dispatcher?newAction=save&entityName=Compra" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="save" type="hidden">
			
			<label for="idcompra">Código</label>
			<input class="limpavel" id="idcompra" name="idcompra" style="width: 200px;" 
			value="<%= compra.getIdCompra() == null ? "" : compra.getIdCompra() %>" type="text" readonly>
			
			<label for="fornecedor">Fornecedor</label><br>
			<select id="fornecedor" class="limpavel" name="fornecedor">
				<option value="">Selecione Fornecedor</option>
				<% 
				for(Object obj : listaFornecedor) {
					Fornecedor nFornecedor = (Fornecedor) obj;
				%>
					<option <%= compra.getFornecedor() == null ? "" : (
							compra.getFornecedor().equals(nFornecedor) ? "selected" : ""
							) %> value="<%= nFornecedor.getIdFornecedor() %>">
						<%= nFornecedor.getRazaoSocial() %>
					</option>
				<% } %>
			</select><br>
			
			<label for="descricao">Descrição</label>
			<input class="limpavel" id="descricao" name="descricao" 
			value="<%= compra.getDescricao() == null ? "" : compra.getDescricao() %>" type="text" required>
			
			<label for="valor">Valor</label>
			<input class="limpavel" id="valor" name="valor" 
			value="<%= compra.getValor() == null ? "0" : compra.getValor() %>" type="text" required <%= compra.getIdCompra() != null ? "readonly" : "" %>>
			
			<label for="data">Data</label>
			<input class="limpavel" id="data" name="data" onclick="<%= compra.getIdCompra() == null ? "setData()" : "void()" %>"
			value="<%= compra.getData() == null ? "" : compra.getData() %>" type="text" required readonly>
			
			<% if (compra.getIdCompra() == null) { %>
			
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
			<input type="text" onkeypress="return validateNumber(event)" id="selectquantidade" class="limpavel">
			
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
				<a class="btn btn-default" href="dispatcher?newAction=remove&entityName=Compra&idCompra=<%= compra.getIdCompra() %>" onclick="return confirmarExclusao();">Excluir</a>
				<a href="dispatcher?newAction=goFind&entityName=Compra" class="btn btn-default">Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>