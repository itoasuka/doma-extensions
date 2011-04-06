package jp.osd.doma.extensions.internal;

import java.sql.SQLException;

import org.seasar.doma.jdbc.SqlExecutionSkipCause;

import jp.osd.doma.extensions.jdbc.Slf4jJdbcLogger;

/**
 * リソースバンドルのキーとして使用するメッセージコードを定義した列挙型です。
 * 
 * @author asuka
 */
public enum MessageCodes {
	
	/** 
	 * {@link Slf4jJdbcLogger#logLocalTransactionBegun(String, String, String)}
	 * で使用するメッセージコードです。
	 */
	DOMA2063,
	/** 
	 * {@link Slf4jJdbcLogger#logLocalTransactionEnded(String, String, String)}
	 * で使用するメッセージコードです。
	 */
	DOMA2064,
	/** 
	 * {@link Slf4jJdbcLogger#logLocalTransactionSavepointCreated(String, String, String, String)}
	 * で使用するメッセージコードです。
	 */
	DOMA2065,
	/** 
	 * {@link Slf4jJdbcLogger#logLocalTransactionSavepointReleased(String, String, String, String)}
	 * で使用するメッセージコードです。
	 */
	DOMA2066,
	/** 
	 * {@link Slf4jJdbcLogger#logLocalTransactionCommitted(String, String, String)}
	 * で使用するメッセージコードです。
	 */
	DOMA2067,
	/** 
	 * {@link Slf4jJdbcLogger#logLocalTransactionRolledback(String, String, String)}
	 * で使用するメッセージコードです。
	 */
	DOMA2068,
	/** 
	 * {@link Slf4jJdbcLogger#logLocalTransactionSavepointRolledback(String, String, String, String)}
	 * で使用するメッセージコードです。
	 */
	DOMA2069,
	/** 
	 * {@link Slf4jJdbcLogger#logLocalTransactionRollbackFailure(String, String, String, SQLException)}
	 * で使用するメッセージコードです。
	 */
	DOMA2070,
	/** 
	 * {@link Slf4jJdbcLogger#logAutoCommitEnablingFailure(String, String, SQLException)}
	 * で使用するメッセージコードです。
	 */
	DOMA2071,
	/** 
	 * {@link Slf4jJdbcLogger#logTransactionIsolationSettingFailuer(String, String, int, SQLException)}
	 * で使用するメッセージコードです。
	 */
	DOMA2072,
	/** 
	 * {@link Slf4jJdbcLogger#logConnectionClosingFailure(String, String, SQLException)}
	 * で使用するメッセージコードです。
	 */
	DOMA2073,
	/** 
	 * {@link Slf4jJdbcLogger#logStatementClosingFailure(String, String, SQLException)}
	 * で使用するメッセージコードです。
	 */
	DOMA2074,
	/** 
	 * {@link Slf4jJdbcLogger#logResultSetClosingFailure(String, String, SQLException)}
	 * で使用するメッセージコードです。
	 */
	DOMA2075,
	/** 
	 * {@link Slf4jJdbcLogger#logSql(String, String, org.seasar.doma.jdbc.Sql)}
	 * で使用するメッセージコードです。
	 */
	DOMA2076,
	/** 
	 * {@link Slf4jJdbcLogger#logDaoMethodEntering(String, String, Object...)}
	 * で使用するメッセージコードです。
	 */
	EXT001,
	/** 
	 * {@link Slf4jJdbcLogger#logDaoMethodExiting(String, String, Object)}
	 * で使用するメッセージコードです。
	 */
	EXT002,
	/** 
	 * {@link Slf4jJdbcLogger#logDaoMethodThrowing(String, String, RuntimeException)}
	 * で使用するメッセージコードです。
	 */
	EXT003,
	/** 
	 * {@link Slf4jJdbcLogger#logSqlExecutionSkipping(String, String, SqlExecutionSkipCause)}
	 * で使用するメッセージコードです。
	 */
	EXT004;
}
