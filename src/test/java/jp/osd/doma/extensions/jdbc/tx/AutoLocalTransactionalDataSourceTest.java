/*
 * 作成日 : 2011/04/15
 */
package jp.osd.doma.extensions.jdbc.tx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.UtilLoggingJdbcLogger;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionalDataSource;
import org.seasar.doma.jdbc.tx.TransactionIsolationLevel;

/**
 * @author asuka
 */
public class AutoLocalTransactionalDataSourceTest {
	private AutoLocalTransactionalDataSource ds;

	@Before
	public void setUp() throws Exception {
		SimpleDataSource sds = new SimpleDataSource();
		sds.setUrl("jdbc:h2:mem:mydb;DB_CLOSE_DELAY=-1");
		sds.setUser("sa");
		LocalTransactionalDataSource ltds = new LocalTransactionalDataSource(sds);
		ds = new AutoLocalTransactionalDataSource(ltds);
	}

	/**
	 * {@link jp.osd.doma.extensions.jdbc.tx.AutoLocalTransactionalDataSource#setTransactionIsolationLevel(org.seasar.doma.jdbc.tx.TransactionIsolationLevel)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testSetTransactionIsolationLevel() {
		ds.setTransactionIsolationLevel(TransactionIsolationLevel.READ_COMMITTED);
		assertEquals(TransactionIsolationLevel.READ_COMMITTED,
				ds.getTransactionIsolationLevel());
	}

	/**
	 * {@link jp.osd.doma.extensions.jdbc.tx.AutoLocalTransactionalDataSource#setDefaultTransactionIsolationLevel(org.seasar.doma.jdbc.tx.TransactionIsolationLevel)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testSetDefaultTransactionIsolationLevel() {
		ds.setDefaultTransactionIsolationLevel(TransactionIsolationLevel.READ_COMMITTED);
		assertEquals(TransactionIsolationLevel.READ_COMMITTED,
				ds.getTransactionIsolationLevel());
	}

	/**
	 * {@link jp.osd.doma.extensions.jdbc.tx.AbstractAutoTransactionalDataSource#getConnection()}
	 * のためのテスト・メソッド。
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetConnection() throws Exception {
		Connection conn = ds.getConnection();
		LocalTransaction tx = ds.getDataSource().getLocalTransaction(
				new UtilLoggingJdbcLogger());
		assertTrue(tx.isActive());
		conn.createStatement().execute("CREATE TABLE hoge (fuga VARCHAR(255))");
		conn.close();

		conn = ds.getConnection();
		assertTrue(tx.isActive());
		conn.createStatement().executeUpdate("INSERT INTO hoge VALUES('abc')");
		conn.close();

		tx.commit();
	}

	/**
	 * {@link jp.osd.doma.extensions.jdbc.tx.AbstractAutoTransactionalDataSource#getConnection()}
	 * のためのテスト・メソッド。
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetConnection_2() throws Exception {
		ds.setTransactionIsolationLevel(TransactionIsolationLevel.READ_COMMITTED);
		Connection conn = ds.getConnection();
		LocalTransaction tx = ds.getDataSource().getLocalTransaction(
				new UtilLoggingJdbcLogger());
		assertTrue(tx.isActive());
		conn.close();
	}
}
