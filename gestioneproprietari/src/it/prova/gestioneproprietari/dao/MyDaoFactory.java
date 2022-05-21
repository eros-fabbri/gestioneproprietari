package it.prova.gestioneproprietari.dao;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.dao.automobile.AutomobileDAOImpl;
import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneproprietari.model.Automobile;

public class MyDaoFactory {

	// rendiamo questo factory SINGLETON
	private static AutomobileDAO automobileDAOInstance = null;
	private static ProprietarioDAO proprietarioDAOInstance = null;

	public static AutomobileDAO getAbitanteDAOInstance() {
		if (automobileDAOInstance == null)
			automobileDAOInstance = new AutomobileDAOImpl();
		return automobileDAOInstance;
	}

	public static ProprietarioDAO getMunicipioDAOInstance(){
		if(proprietarioDAOInstance == null)
			proprietarioDAOInstance= new ProprietarioDAOImpl();
		return proprietarioDAOInstance;
	}

}
