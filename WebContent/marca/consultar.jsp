<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consultar Marca</title>
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
	
	
	
	
	<h1>Consultar Marca</h1>
	<div>
		<form action="consultar.jsp" method="POST" onsubmit="return validate();">
			<input name="newAction" id="newAction" value="find" type="hidden">
			<label for="codigo">Código</label>
			<input class="limpavel" id="codigo" name="codigo" style="width: 200px;" value="" type="text">
			<label for="descricao">Descrição</label>
			<input class="limpavel" id="descricao" name="descricao" value="" type="text">
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
				<th>Descrição</th>
				<th class="medium-cell">Ações</th>
			</tr>
		</thead>
		<tbody>
		
		<tr>
			<td>6</td>
			<td>FIAT</td>
			<td>
				<a class="btn btn-delete" href="consultar.jsp?newAction=remove&amp;codigo=6" onclick="return confirmarExclusao();">Excluir</a>
				<a href="manter.jsp?newAction=detail&amp;codigo=6" class="btn btn-edit">Editar</a>
				
			</td>
		</tr>
		
		<tr>
			<td>9</td>
			<td>MASSERATI</td>
			<td>
				<a class="btn btn-delete" href="consultar.jsp?newAction=remove&amp;codigo=9" onclick="return confirmarExclusao();">Excluir</a>
				<a href="manter.jsp?newAction=detail&amp;codigo=9" class="btn btn-edit">Editar</a>
				
			</td>
		</tr>
		
		<tr>
			<td>11</td>
			<td>RENAULT</td>
			<td>
				<a class="btn btn-delete" href="consultar.jsp?newAction=remove&amp;codigo=11" onclick="return confirmarExclusao();">Excluir</a>
				<a href="manter.jsp?newAction=detail&amp;codigo=11" class="btn btn-edit">Editar</a>
				
			</td>
		</tr>
		
		</tbody>
	</table>
	</div>

</body></html>