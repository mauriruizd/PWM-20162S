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
		//TODO: completar
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>