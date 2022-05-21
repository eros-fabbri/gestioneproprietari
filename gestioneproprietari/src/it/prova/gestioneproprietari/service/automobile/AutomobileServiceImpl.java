package it.prova.gestioneproprietari.service.automobile;

import java.util.List;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileServiceImpl implements AutomobileService {

	@Override
	public List<Automobile> listAllAutomobili() throws Exception {
		
		return null;
	}

	@Override
	public Automobile caricaSingolaAutomobile(Long id) throws Exception {
		
		return null;
	}

	@Override
	public void aggiorna(Automobile automobileInstance) throws Exception {
	}

	@Override
	public void inserisciNuova(Automobile automobileInstance) throws Exception {
	}

	@Override
	public void rimuovi(Long idAutomobileInstance) throws Exception {
	}

	@Override
	public void setAutomobileDAO(AutomobileDAO automobileDAO) {
	}

}
