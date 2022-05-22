package it.prova.gestioneproprietari.dao.proprietario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.model.Proprietario;

public class ProprietarioDAOImpl implements ProprietarioDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Proprietario> list() throws Exception {

		return entityManager.createQuery("from Proprietario", Proprietario.class).getResultList();
	}

	@Override
	public Proprietario get(Long id) throws Exception {

		return entityManager.find(Proprietario.class, id);
	}

	@Override
	public Proprietario getEagerAutomobili(Long id) throws Exception {
		TypedQuery<Proprietario> query = entityManager
				.createQuery("from Proprietario p left join fetch p.automobili where m.id = ?1", Proprietario.class);
		query.setParameter(1, id);
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public void update(Proprietario proprietario) throws Exception {
		if (proprietario == null) {
			throw new Exception("Problema valore in input");
		}
		proprietario = entityManager.merge(proprietario);
	}

	@Override
	public void insert(Proprietario proprietario) throws Exception {
		if (proprietario == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(proprietario);
	}

	@Override
	public void delete(Proprietario proprietario) throws Exception {
		if (proprietario == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(proprietario));
	}

	@Override
	public long countQuantiHannoAutomobileImmatricolataDal(int annoImmatricolazione) throws Exception {

		if (annoImmatricolazione == 0) {
			throw new Exception("Problema valore in input");
		}

		Query query = entityManager.createQuery(
				"select count(*) from Proprietario p join p.automobili a where a.annoImmatricolazione >= ?1");
		query.setParameter(1, annoImmatricolazione);
		return (long) query.getSingleResult();
	}

}
