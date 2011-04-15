/*
 * 作成日 : 2011/04/15
 */
package jp.osd.doma.extensions.jdbc.tx;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * {@link AutoTransactionalDataSource} 実装のための基底クラスです。
 *
 * @param <E> ラップするデータソースの型
 * @author asuka
 */
public abstract class AbstractAutoTransactionalDataSource<E extends DataSource>
		implements AutoTransactionalDataSource {


	private final E dataSource;

	/**
	 * 新たにオブジェクトを構築します。
	 *
	 * @param dataSource
	 *            ラップするデータソース
	 */
	public AbstractAutoTransactionalDataSource(E dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Connection getConnection() throws SQLException {
		beginTransaction();
		return dataSource.getConnection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		beginTransaction();
		return dataSource.getConnection(username, password);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return dataSource.getLogWriter();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		return dataSource.getLoginTimeout();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		dataSource.setLogWriter(out);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		dataSource.setLoginTimeout(seconds);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return dataSource.isWrapperFor(iface);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return dataSource.unwrap(iface);
	}


	/**
	 * ラップしているデータソースを取得します。
	 *
	 * @return データソース
	 */
	protected E getDataSource() {
		return dataSource;
	}

	/**
	 * トランザクションを開始します。
	 */
	protected abstract void beginTransaction();
}
