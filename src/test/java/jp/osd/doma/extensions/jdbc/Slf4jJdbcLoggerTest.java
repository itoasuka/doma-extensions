package jp.osd.doma.extensions.jdbc;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.seasar.doma.jdbc.Sql;
import org.seasar.doma.jdbc.SqlExecutionSkipCause;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class Slf4jJdbcLoggerTest {
	private Slf4jJdbcLogger logger;
	private Logger logbackLogger;
	private ListAppender<ILoggingEvent> appender;

	@Before
	public void setUp() throws Exception {
		logger = new Slf4jJdbcLogger();
		logbackLogger = (Logger) LoggerFactory.getLogger(getClass().toString());
		appender = new ListAppender<ILoggingEvent>();
		appender.setName("TestHelperAppender");
		appender.list.clear();
		appender.start();
		logbackLogger.addAppender(appender);
		logbackLogger.setLevel(Level.ALL);
	}

	@Test
	public void testLogDaoMethodEntering() {
		logger.logDaoMethodEntering(getClass().toString(), "hoge", "a", 1);
		assertEquals("hoge(a, 1) に入ります。", getLastLog());
		logbackLogger.setLevel(Level.OFF);
		logger.logDaoMethodEntering(getClass().toString(), "hoge", "a", 1);
		assertEquals(1, appender.list.size());
	}

	@Test
	public void testLogDaoMethodExiting() {
		logger.logDaoMethodExiting(getClass().toString(), "hoge", "result");
		assertEquals("hoge から出ます。戻り値 = result", getLastLog());
	}

	@Test
	public void testLogDaoMethodThrowing() {
		logger.logDaoMethodThrowing(getClass().toString(), "hoge",
				new RuntimeException("test"));
		assertEquals("hoge で実行時エラーが発生しました。", getLastLog());
	}

	@Test
	public void testLogSqlExecutionSkipping() {
		logger.logSqlExecutionSkipping(getClass().toString(), "hoge",
				SqlExecutionSkipCause.BATCH_TARGET_NONEXISTENT);
		assertEquals("SQL の実行をスキップしました。理由[BATCH_TARGET_NONEXISTENT]",
				getLastLog());
	}

	@Test
	public void testLogSql() {
		Sql<?> sql = createMock(Sql.class);
		expect(sql.getSqlFilePath()).andReturn("hoge.sql");
		expect(sql.getFormattedSql()).andReturn("SELECT * FROM hoge");
		replay(sql);

		logger.logSql(getClass().toString(), "hoge", sql);
		assertEquals(
				"[DOMA2076] SQLログ : SQLファイル=[hoge.sql],\nSELECT * FROM hoge",
				getLastLog());
	}

	@Test
	public void testLogLocalTransactionBegun() {
		logger.logLocalTransactionBegun(getClass().toString(), "hoge", "abc");
		assertEquals("[DOMA2063] ローカルトランザクション[abc]を開始しました。", getLastLog());
	}

	@Test
	public void testLogLocalTransactionEnded() {
		logger.logLocalTransactionEnded(getClass().toString(), "hoge", "abc");
		assertEquals("[DOMA2064] ローカルトランザクション[abc]を終了しました。", getLastLog());
	}

	@Test
	public void testLogLocalTransactionSavepointCreated() {
		logger.logLocalTransactionSavepointCreated(getClass().toString(),
				"hoge", "abc", "def");
		assertEquals("[DOMA2065] ローカルトランザクション[abc]のセーブポイント[def]を作成しました。",
				getLastLog());
	}

	@Test
	public void testLogLocalTransactionSavepointReleased() {
		logger.logLocalTransactionSavepointReleased(getClass().toString(),
				"hoge", "abc", "def");
		assertEquals("[DOMA2066] ローカルトランザクション[abc]のセーブポイント[def]を開放しました。",
				getLastLog());
	}

	@Test
	public void testLogLocalTransactionCommitted() {
		logger.logLocalTransactionCommitted(getClass().toString(), "hoge",
				"abc");
		assertEquals("[DOMA2067] ローカルトランザクション[abc]をコミットしました。", getLastLog());
	}

	@Test
	public void testLogLocalTransactionRolledback() {
		logger.logLocalTransactionRolledback(getClass().toString(), "hoge",
				"abc");
		assertEquals("[DOMA2068] ローカルトランザクション[abc]をロールバックしました。", getLastLog());
	}

	@Test
	public void testLogLocalTransactionSavepointRolledback() {
		logger.logLocalTransactionSavepointRolledback(getClass().toString(),
				"hoge", "abc", "def");
		assertEquals("[DOMA2069] ローカルトランザクション[abc]のセーブポイント[def]へロールバックしました。",
				getLastLog());
	}

	@Test
	public void testLogLocalTransactionRollbackFailure() {
		logger.logLocalTransactionRollbackFailure(getClass().toString(),
				"hoge", "abc", new SQLException("test"));
		assertEquals("[DOMA2070] ローカルトランザクション[abc]のロールバックに失敗しました。",
				getLastLog());
	}

	@Test
	public void testLogAutoCommitEnablingFailure() {
		logger.logAutoCommitEnablingFailure(getClass().toString(), "hoge",
				new SQLException("test"));
		assertEquals("[DOMA2071] 自動コミットモードの有効化に失敗しました。", getLastLog());
	}

	@Test
	public void testLogTransactionIsolationSettingFailuer() {
		logger.logTransactionIsolationSettingFailuer(getClass().toString(),
				"hoge", 1, new SQLException("test"));
		assertEquals("[DOMA2072] トランザクション分離レベル[1]の設定に失敗しました。", getLastLog());
	}

	@Test
	public void testLogConnectionClosingFailure() {
		logger.logConnectionClosingFailure(getClass().toString(), "hoge",
				new SQLException("test"));
		assertEquals("[DOMA2073] java.sql.Connectionのクローズに失敗しました。",
				getLastLog());
	}

	@Test
	public void testLogStatementClosingFailure() {
		logger.logStatementClosingFailure(getClass().toString(), "hoge",
				new SQLException("test"));
		assertEquals("[DOMA2074] java.sql.Statementのクローズに失敗しました。", getLastLog());
	}

	@Test
	public void testLogResultSetClosingFailure() {
		logger.logResultSetClosingFailure(getClass().toString(), "hoge",
				new SQLException("test"));
		assertEquals("[DOMA2075] java.sql.ResultSetのクローズに失敗しました。", getLastLog());
	}

	private String getLastLog() {
		if (appender.list.size() == 0) {
			return null;
		}

		int index = appender.list.size() - 1;
		ILoggingEvent event = appender.list.get(index);

		return event.getFormattedMessage();
	}
}
