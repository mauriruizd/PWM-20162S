<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistema de Controle de Mecânica - PWM20162S</title>
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div id="container">
	<div id="header">Sistema de Controle de Mecânica - PWM20162S</div><!--
	--><div id="aside">
		<h1><u>MENU</u></h1>
		<ul>
			<li><a href="dispatcher?newAction=goFind&entityName=Marca" target="main-content-iframe">Marca</a></li>
			<li><a href="dispatcher?newAction=goFind&entityName=Modelo" target="main-content-iframe">Modelo</a></li>
			<li><a href="dispatcher?newAction=goFind&entityName=Veiculo" target="main-content-iframe">Veículo</a></li>
			<li><a href="dispatcher?newAction=goFind&entityName=Cliente" target="main-content-iframe">Cliente</a></li>
			<li><a href="dispatcher?newAction=goFind&entityName=Fornecedor" target="main-content-iframe">Fornecedor</a></li>
			<li><a href="dispatcher?newAction=goFind&entityName=Produto" target="main-content-iframe">Produto</a></li>
			<li><a href="dispatcher?newAction=goFind&entityName=Servico" target="main-content-iframe">Servico</a></li>
			<li><a href="dispatcher?newAction=goFind&entityName=Compra" target="main-content-iframe">Compra</a></li>
			<!--<li><a href="dispatcher?newAction=goFind&entityName=Venda" target="main-content-iframe">Venda</a></li>-->
		</ul>
	</div><!--
	--><div id="main-content">
		<iframe src="dispatcher?newAction=goFind&entityName=Marca" name="main-content-iframe" id="main-content-iframe"></iframe>
	</div>
</div>

</body>
</html>