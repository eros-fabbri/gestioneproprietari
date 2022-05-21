package it.prova.gestioneproprietari.dao.automobile;

import java.util.List;

import javax.persistence.EntityManager;

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
	
	//implementare altri metodi

}
