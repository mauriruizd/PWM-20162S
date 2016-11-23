package br.edu.udc.sistemas.poo2_20161S.controller;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import br.edu.udc.sistemas.poo2_20161S.infra.Factory;
import br.edu.udc.sistemas.poo2_20161S.session.Session;

public abstract class Controller {

	protected String entityName;
	protected String action;
	protected Class <?> entityClass;
	protected HttpServletRequest request;
	protected Object obj;
	protected Boolean saveFilter;
	
	Controller(String entityName) throws Exception {
		this.entityName = entityName;
		this.entityClass = Class.forName("br.edu.udc.sistemas.poo2_20161S.entity." + entityName);
	}
	
	private HashMap <String, String> getPostData() {		
		Enumeration <String> parameters = this.request.getParameterNames();
		HashMap <String, String> postData = new HashMap<String, String>();
		while(parameters.hasMoreElements()) {
			String paramName = (String) parameters.nextElement();
			if ((!paramName.equals("newAction")) && 
				(!paramName.equals("entityName")) &&
				(!paramName.equals("id"))) {
				postData.put(paramName, this.request.getParameter(paramName));
			}
		}
		return postData;
	}

	private Object getDeleteObject() throws Exception {
		Integer id = null;
		try {
			id = new Integer(Integer.parseInt(request.getParameter("id")));
		} catch (Exception e) {
			return null;
		}
		return Factory.createById(id,this. entityClass);
	}
	
	private Object getFilterObject() {
		if (this.action.equals("find")) {
			this.request.getSession().setAttribute("filter" + this.entityName, this.obj);
		} else if ((this.action.equals("goNew")) || (this.action.equals("clean"))) {
			this.request.getSession().removeAttribute("filter" + this.entityName);
		} else if ((this.action.equals("goFind")) || (this.action.equals("remove"))) {
			return (Object) this.request.getSession().getAttribute("filter" + this.entityName);
 		}
		return null;
	}
	
	public void execute(HttpServletRequest request, String action) throws Exception {
		this.request = request;
		this.action = action;
		this.request.setAttribute("action", this.action);
		this.obj = Factory.createByPost(this.getPostData(), this.entityClass);
		Object objFilter = this.getFilterObject();
		Object objId = this.getDeleteObject();
		
		Class <?> sessionClass = Class.forName("br.edu.udc.sistemas.poo2_20161S.session.Session" + this.entityName);
		Session session = (Session) sessionClass.newInstance();
		Object objReturn = null;
		try {
			Method sessionMethod = sessionClass.getMethod(this.action, Object.class);
			if (objId != null) {
				objReturn = sessionMethod.invoke(session, objId);
			} else {
				objReturn = sessionMethod.invoke(session, this.obj);
			}
			
			if (objFilter != null) {
				this.action = "find";
				sessionMethod = sessionClass.getMethod(this.action, Object.class);
				objReturn = sessionMethod.invoke(session, objFilter);
			}
			
			Method controllerMethod = this.getClass().getMethod(this.action, Object.class);
 			controllerMethod.invoke(this,objReturn);
			
		} catch (NoSuchMethodException e) {
			if (objFilter != null) {
				this.action = "find";
				Method sessionMethod = sessionClass.getMethod(this.action, Object.class);
				objReturn = sessionMethod.invoke(session, objFilter);
				
				Method controllerMethod = this.getClass().getMethod(this.action, Object.class);
	 			controllerMethod.invoke(this,objReturn);
			} else {
				Method controllerMethod = this.getClass().getMethod(this.action);
				controllerMethod.invoke(this);
			}
		}
	}

	public void lastFind(Object obj) {
		this.find(obj);
	}
	
	public void clean() {
		this.goFind();
	}
	
	public abstract void goNew();
	public abstract void goFind();
	public abstract void save(Object obj);
	public abstract void remove(Object obj);
	public abstract void detail(Object obj);
	public abstract void find(Object obj);
}