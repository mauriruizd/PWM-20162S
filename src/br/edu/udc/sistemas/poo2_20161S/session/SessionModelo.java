package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoMarca;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoModelo;
import br.edu.udc.sistemas.poo2_20161S.entity.Marca;
import br.edu.udc.sistemas.poo2_20161S.entity.Modelo;

public class SessionModelo extends Session {

	public SessionModelo() throws Exception {
		super(DaoModelo.class);
	}
	
	public Object[] find(Object object, boolean bCommit) throws Exception {
        Object listModelo[] = super.find(object,false);

        DaoMarca daoMarca = new DaoMarca(this.getDao().getConnection());

        for (int i = 0; i < listModelo.length; i++) {
                Modelo modeloAux = (Modelo) listModelo[i];
                modeloAux.setMarca( (Marca) daoMarca.findByPrimary(modeloAux.getMarca()));
        }
        if (bCommit) {
                this.getDao().commit();
        }
        return listModelo;
	}       

}
