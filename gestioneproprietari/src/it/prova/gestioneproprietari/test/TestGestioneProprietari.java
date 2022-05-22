package it.prova.gestioneproprietari.test;

import java.util.Date;
import java.util.List;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.model.Proprietario;
import it.prova.gestioneproprietari.service.MyServiceFactory;
import it.prova.gestioneproprietari.service.automobile.AutomobileService;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioService;

public class TestGestioneProprietari {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testInserisciProprietario(proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testInserisciAutomobile(proprietarioService, automobileService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testRimozioneAutomobile(proprietarioService, automobileService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");
			
			testCountQuantiHannoAutomobileImmatricolataDal(proprietarioService);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciProprietario inizio.............");
		@SuppressWarnings("deprecation")
		Proprietario nuovoProprietario = new Proprietario("mario", "rossi", "RSSMRO00000000",
				new Date(2002 - 1900, 06, 13));
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		proprietarioService.inserisciNuovo(nuovoProprietario);
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciProprietario fallito ");

		System.out.println(".......testInserisciProprietario fine: PASSED.............");
	}

	private static void testInserisciAutomobile(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testInserisciAutomobile inizio.............");

		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovoAutomobile = new Automobile("Audi", "A3", "AB000CD", 2017);
		nuovoAutomobile.setProprietario(listaProprietariPresenti.get(0));

		automobileService.inserisciNuova(nuovoAutomobile);

		if (nuovoAutomobile.getId() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");

		if (nuovoAutomobile.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il proprietario ");

		System.out.println(".......testInserisciAutomobile fine: PASSED.............");
	}

	private static void testRimozioneAutomobile(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testRimozioneAutomobile inizio.............");

		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testRimozioneAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovoAutomobile = new Automobile("Audi", "A3", "AB000CD", 2017);
		nuovoAutomobile.setProprietario(listaProprietariPresenti.get(0));

		automobileService.inserisciNuova(nuovoAutomobile);

		Long idAutomobileInserito = nuovoAutomobile.getId();
		automobileService.rimuovi(nuovoAutomobile);
		if (automobileService.caricaSingolaAutomobile(idAutomobileInserito) != null)
			throw new RuntimeException("testRimozioneAutomobile fallito: record non cancellato ");
		System.out.println(".......testRimozioneAutomobile fine: PASSED.............");
	}

	private static void testCountQuantiHannoAutomobileImmatricolataDal(ProprietarioService proprietarioService)
			throws Exception {
		System.out.println(".......testCountQuantiHannoAutomobileImmatricolataDal inizio.............");

		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException(
					"testCountQuantiHannoAutomobileImmatricolataDal fallito: non ci sono proprietari a cui collegarci ");
		long result = proprietarioService.countQuantiHannoAutomobileImmatricolataDal(2000);
		System.out.println("TROVATI:" + result);
		System.out.println(".......testRimozioneAutomobile fine: PASSED.............");
	}
}
