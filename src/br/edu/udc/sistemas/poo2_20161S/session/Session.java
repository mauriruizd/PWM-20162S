package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.Dao;

public class Session {
	
	private Dao dao;
	protected Class <Dao> daoClass;
		
	public Session(Class <?> daoClass) {
		this.daoClass = (Class<Dao>) daoClass;
		this.dao = null;
	}
	
	public Dao getDao() throws Exception {
		if (this.dao == null) {
			this.dao = this.daoClass.newInstance();
		}
		return dao;
	}

	public void save(Object object) throws Exception {
		this.save(object,true);	
	}
	
	public void save(Object object, boolean bCommit) throws Exception {
		this.getDao().save(object);
		if (bCommit) {
			this.getDao().commit();
		}
	}
	
	public void remove(Object object) throws Exception {
		this.remove(object,true);	
	}

	public void remove(Object object, boolean bCommit) throws Exception {
		this.getDao().remove(object);
		if (bCommit) {
			this.getDao().commit();
		}
	}
	
	public Object[] find(Object object) throws Exception {
		return this.find(object,true);
	}
	
	public Object[] find(Object object, boolean bCommit) throws Exception {
		Object list[] = this.getDao().find(object);
		if (bCommit) {
			this.getDao().commit();
		}
		return list;
	}
	
	public Object detail(Object object) throws Exception {
		return this.detail(object,true);
	}
	
	public Object detail(Object object, boolean bCommit) throws Exception {
		Object objectRetorno = this.getDao().findByPrimary(object);
		if (bCommit) {
			this.getDao().commit();
		}
		return objectRetorno;
	}
}
