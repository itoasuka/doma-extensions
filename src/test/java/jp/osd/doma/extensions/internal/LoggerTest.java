package jp.osd.doma.extensions.internal;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class LoggerTest {
	private Logger logger;
	private ch.qos.logback.classic.Logger logbackLogger;
	private ListAppender<ILoggingEvent> appender;

	@Before
	public void setUp() throws Exception {
		logbackLogger = (ch.qos.logback.classic.Logger) LoggerFactory
				.getLogger(getClass().toString());
		appender = new ListAppender<ILoggingEvent>();
		appender.setName("TestHelperAppender");
		appender.list.clear();
		appender.start();
		logbackLogger.addAppender(appender);
		logbackLogger.setLevel(Level.ALL);
		logger = Logger.getLogger(getClass().toString(), ResourceBundle
				.getBundle("jp/osd/doma/extensions/jdbc/messages"));
	}

	@Test
	public void testDebug() {
		logger.debug(MessageCodes.EXT002, "hoge", "result");
		assertEquals("hoge から出ます。戻り値 = result", getLastLog());
		logbackLogger.setLevel(Level.INFO);
		logger.debug(MessageCodes.EXT002, "hoge", "result");
		assertEquals(1, appender.list.size());
	}

	@Test
	public void testError() {
		logger.error(new SQLException("test"), MessageCodes.DOMA2075);
		assertEquals("[DOMA2075] java.sql.ResultSetのクローズに失敗しました。", getLastLog());
		logbackLogger.setLevel(Level.OFF);
		logger.error(new SQLException("test"), MessageCodes.DOMA2075);
		assertEquals(1, appender.list.size());
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
