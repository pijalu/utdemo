/**
 * 
 */
package be.ordina.utdemo.factoids.provider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.CleanInsertLoadStrategy;

/**
 * @author Turbots
 *
 */
@DataSet(value = "/facts.xml", loadStrategy = CleanInsertLoadStrategy.class)
public class DatabaseFactProviderUnitilsTest extends UnitilsJUnit4 {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	private DatabaseFactProvider databaseFactProvider;

	@BeforeClass
	public static void initEntityManager() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("UTDEMO");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Before
	public void before() {
		databaseFactProvider = new DatabaseFactProvider(entityManager);
	}

	@Test
	public void test() {
		Assert.assertEquals(10, databaseFactProvider.size());
	}
}
