package it.prova.gestioneproprietari.service;


public class MyServiceFactory {

	// rendiamo le istanze restituite SINGLETON
	private static AbitanteService abitanteServiceInstance = null;
	private static MunicipioService municipioServiceInstance = null;

	public static AbitanteService getAbitanteServiceInstance() {
		if (abitanteServiceInstance == null) {
			abitanteServiceInstance = new AbitanteServiceImpl();
			abitanteServiceInstance.setAbitanteDAO(MyDaoFactory.getAbitanteDAOInstance());
		}
		return abitanteServiceInstance;
	}

	public static MunicipioService getMunicipioServiceInstance() {
		if (municipioServiceInstance == null) {
			municipioServiceInstance = new MunicipioServiceImpl();
			municipioServiceInstance.setMunicipioDAO(MyDaoFactory.getMunicipioDAOInstance());
		}
		return municipioServiceInstance;
	}

}
