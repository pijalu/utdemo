/**
 * 
 */
package be.ordina.utdemo.factoids.provider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import be.ordina.utdemo.factoids.entity.FactEntity;
import be.ordina.utdemo.factoids.model.Fact;

/**
 * @author Turbots
 *
 */
public class DatabaseFactProvider implements FactProvider {

	private EntityManager entityManager;

	public DatabaseFactProvider() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UTDEMO");
		entityManager = emf.createEntityManager();

	}

	@Override
	public int size() {
		Number size = (Number) entityManager.createQuery("SELECT COUNT(factEntity) FROM FactEntity factEntity").getSingleResult();
		return size.intValue();
	}

	@Override
	public Fact getFact(int index) {
		TypedQuery<FactEntity> query = entityManager.createQuery("SELECT factEntity FROM FactEntity factEntity WHERE factEntity.id = :id", FactEntity.class);
		query.setParameter("id", index);

		FactEntity factEntity = query.getSingleResult();

		return new Fact(factEntity.getContent());
	}

	@Override
	@Transactional
	public void addFact(Fact fact) {
		try {
			entityManager.getTransaction().begin();

			FactEntity factEntity = new FactEntity();
			factEntity.setContent(fact.getContent());

			entityManager.persist(factEntity);
		} finally {
			entityManager.getTransaction().commit();
		}
	}
}
