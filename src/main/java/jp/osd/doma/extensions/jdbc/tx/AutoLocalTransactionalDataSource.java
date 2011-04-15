/*
 * 作成日 : 2011/04/15
 */
package jp.osd.doma.extensions.jdbc.tx;

import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.UtilLoggingJdbcLogger;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionalDataSource;
import org.seasar.doma.jdbc.tx.TransactionIsolationLevel;

/**
 * {@link LocalTransactionalDataSource} を用いた {@link AutoTransactionalDataSource}
 * 実装です。
 *
 * @author asuka
 */
public class AutoLocalTransactionalDataSource extends
		AbstractAutoTransactionalDataSource<LocalTransactionalDataSource> {
	private TransactionIsolationLevel defaultTransactionIsolationLevel = null;

	private ThreadLocal<TransactionIsolationLevel> threadLocal = new ThreadLocal<TransactionIsolationLevel>() {
		@Override
		protected TransactionIsolationLevel initialValue() {
			return defaultTransactionIsolationLevel;
		}
	};
	private final JdbcLogger jdbcLogger;

	/**
	 * 新たにオブジェクトを構築します。
	 *
	 * @param dataSource
	 *            ラップするデータソース
	 * @param jdbcLogger
	 *            ロガー
	 */
	public AutoLocalTransactionalDataSource(
			LocalTransactionalDataSource dataSource, JdbcLogger jdbcLogger) {
		super(dataSource);
		this.jdbcLogger = jdbcLogger;
	}

	/**
	 * 新たにオブジェクトを構築します。ロガーには {@link UtilLoggingJdbcLogger} を使用します。
	 *
	 * @param dataSource
	 *            ラップするデータソース
	 */
	public AutoLocalTransactionalDataSource(
			LocalTransactionalDataSource dataSource) {
		this(dataSource, new UtilLoggingJdbcLogger());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void beginTransaction() {
		LocalTransaction tx = getDataSource().getLocalTransaction(jdbcLogger);
		if (!tx.isActive()) {
			TransactionIsolationLevel level = getTransactionIsolationLevel();
			threadLocal.remove();
			if (level == null) {
				tx.begin();
			} else {
				tx.begin(level);
			}
		}
	}

	/**
	 * 次に開始するトランザクションのトランザクション分離レベルを設定します。
	 *
	 * @param transactionIsolationLevel トランザクション分離レベル
	 */
	public void setTransactionIsolationLevel(
			TransactionIsolationLevel transactionIsolationLevel) {
		threadLocal.set(transactionIsolationLevel);
	}

	/**
	 * デフォルトのトランザクション分離レベルを設定します。
	 *
	 * @param transactionIsolationLevel トランザクション分離レベル
	 */
	public void setDefaultTransactionIsolationLevel(
			TransactionIsolationLevel transactionIsolationLevel) {
		defaultTransactionIsolationLevel = transactionIsolationLevel;
	}

	/**
	 * 次に開始するトランザクションのトランザクション分離レベルを取得します。
	 *
	 * @return トランザクション分離レベル
	 */
	public TransactionIsolationLevel getTransactionIsolationLevel() {
		TransactionIsolationLevel level = threadLocal.get();
		return level;
	}
}
