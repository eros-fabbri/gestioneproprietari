package it.prova.gestioneproprietari.dao.automobile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	@Override
	public Automobile get(Long id) throws Exception {
		return entityManager.find(Automobile.class, id);
	}

	@Override
	public void update(Automobile automobile) throws Exception {
		if (automobile == null) {
			throw new Exception("Problema valore in input");
		}

		automobile = entityManager.merge(automobile);
	}

	@Override
	public void insert(Automobile automobile) throws Exception {
		if (automobile == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(automobile);
	}

	@Override
	public void delete(Automobile automobile) throws Exception {
		if (automobile == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.remove(entityManager.merge(automobile));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Automobile> getListaAutomobiliDaProprietariConCodiceFiscaleIniziaCon(String inizioCF) throws Exception {
		if (inizioCF == null) {
			throw new Exception("Problema valore in input");
		}

		Query query = entityManager
				.createQuery("from Automobile a join a.proprietario p where p.codiceFiscale  like ?1");
		query.setParameter(1, inizioCF + "%");
		System.out.println(query);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Automobile> getAutomobiliErroreProprietarioMinorenne() throws Exception {

		Query query = entityManager.createQuery(
				"select a from Automobile a join a.proprietario p where year(curdate())-year(p.dataNascita)<18 ",
				Automobile.class);
		return query.getResultList();

	}

}
