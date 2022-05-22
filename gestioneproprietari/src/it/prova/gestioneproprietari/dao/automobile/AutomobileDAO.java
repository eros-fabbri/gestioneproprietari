package it.prova.gestioneproprietari.dao.automobile;

import java.util.List;

import it.prova.gestioneproprietari.dao.IBaseDAO;
import it.prova.gestioneproprietari.model.Automobile;

public interface AutomobileDAO extends IBaseDAO<Automobile> {

	public List<Automobile> getListaAutomobiliDaProprietariConCodiceFiscaleIniziaCon(String inizioCF) throws Exception;

	public List<Automobile> getAutomobiliErroreProprietarioMinorenne() throws Exception;
}
