package jp.osd.doma.extensions.jdbc.tx;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;

import javax.sql.DataSource;
import javax.transaction.NotSupportedException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.junit.Before;
import org.junit.Test;

public class AutoUserTransactionalDataSourceTest {
	private DataSource ds;
	private UserTransaction tx;
	private AutoUserTransactionalDataSource autds;

	@Before
	public void setUp() throws Exception {
		ds = createMock(DataSource.class);
		tx = createMock(UserTransaction.class);
		autds = new AutoUserTransactionalDataSource(ds, tx);
	}

	@Test
	public void testGetConnectionStringString() throws Exception {
		Connection conn = createMock(Connection.class);
		expect(ds.getConnection("abc", "def")).andReturn(conn);
		expect(tx.getStatus()).andReturn(Status.STATUS_NO_TRANSACTION);
		tx.begin();
		replay(conn, ds, tx);
		assertSame(conn, autds.getConnection("abc", "def"));
	}

	@Test
	public void testGetConnectionStringString_2() throws Exception {
		Connection conn = createMock(Connection.class);
		expect(ds.getConnection("abc", "def")).andReturn(conn);
		expect(tx.getStatus()).andReturn(Status.STATUS_ACTIVE);
		replay(conn, ds, tx);
		assertSame(conn, autds.getConnection("abc", "def"));
	}

	@Test
	public void testGetConnectionStringString_3() throws Exception {
		Connection conn = createMock(Connection.class);
		expect(ds.getConnection("abc", "def")).andReturn(conn);
		expect(tx.getStatus()).andReturn(Status.STATUS_NO_TRANSACTION);
		tx.begin();
		expectLastCall().andThrow(new SystemException("test"));
		replay(conn, ds, tx);
		try {
			autds.getConnection("abc", "def");
		} catch (RuntimeException e) {
			assertEquals("test", e.getCause().getMessage());
		}
	}

	@Test
	public void testGetConnectionStringString_4() throws Exception {
		Connection conn = createMock(Connection.class);
		expect(ds.getConnection("abc", "def")).andReturn(conn);
		expect(tx.getStatus()).andReturn(Status.STATUS_NO_TRANSACTION);
		tx.begin();
		expectLastCall().andThrow(new NotSupportedException("test"));
		replay(conn, ds, tx);
		try {
			autds.getConnection("abc", "def");
		} catch (RuntimeException e) {
			assertEquals("test", e.getCause().getMessage());
		}
	}

	@Test
	public void testGetLogWriter() throws Exception {
		PrintWriter pw = new PrintWriter("abc");
		expect(ds.getLogWriter()).andReturn(pw);
		replay(ds, tx);
		assertSame(pw, autds.getLogWriter());
	}

	@Test
	public void testGetLoginTimeout() throws Exception {
		expect(ds.getLoginTimeout()).andReturn(123);
		replay(ds, tx);
		assertEquals(123, autds.getLoginTimeout());
	}

	@Test
	public void testSetLogWriter() throws Exception {
		PrintWriter pw = new PrintWriter("abc");
		ds.setLogWriter(pw);
		replay(ds, tx);

		autds.setLogWriter(pw);
		verify(ds);
	}

	@Test
	public void testSetLoginTimeout() throws Exception {
		ds.setLoginTimeout(123);
		replay(ds, tx);

		autds.setLoginTimeout(123);
		verify(ds);
	}

	@Test
	public void testIsWrapperFor() throws Exception {
		expect(ds.isWrapperFor(Serializable.class)).andReturn(true);
		replay(ds);

		assertTrue(autds.isWrapperFor(Serializable.class));
	}

	@Test
	public void testUnwrap() throws Exception {
		expect(ds.unwrap(Serializable.class)).andReturn("abc");
		replay(ds);

		assertEquals("abc", autds.unwrap(Serializable.class));
	}

}
