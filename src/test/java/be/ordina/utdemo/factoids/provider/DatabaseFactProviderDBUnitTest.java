/**
 * 
 */
package be.ordina.utdemo.factoids.provider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.internal.SessionImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.ordina.utdemo.factoids.model.Fact;

/**
 * @author Turbots
 *
 */
public class DatabaseFactProviderDBUnitTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static IDatabaseConnection connection;
	private static IDataSet dataset;

	private DatabaseFactProvider databaseFactProvider;

	@BeforeClass
	public static void initEntityManager() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("UTDEMO");
		entityManager = entityManagerFactory.createEntityManager();
		connection = new DatabaseConnection(((SessionImpl) (entityManager.getDelegate())).connection());
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());

		FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		flatXmlDataSetBuilder.setColumnSensing(true);
		dataset = flatXmlDataSetBuilder.build(DatabaseFactProviderDBUnitTest.class.getResourceAsStream("/facts.xml"));
	}

	@AfterClass
	public static void closeEntityManager() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Before
	public void cleanDB() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
		databaseFactProvider = new DatabaseFactProvider(entityManager);
	}

	@Test
	public void testSize() {
		int size = databaseFactProvider.size();

		Assert.assertEquals(10, size);
	}

	@Test
	public void testGetFact() {
		DatabaseFactProvider databaseFactProvider = new DatabaseFactProvider();

		Fact fact = databaseFactProvider.getFact(5);

		Assert.assertEquals(new Fact("What killed the dinosaurs: the Ice Age!"), fact);
	}
}
