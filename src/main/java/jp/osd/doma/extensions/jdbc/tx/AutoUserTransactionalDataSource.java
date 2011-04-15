/*
 * 作成日 : 2011/04/15
 */
package jp.osd.doma.extensions.jdbc.tx;

import javax.sql.DataSource;
import javax.transaction.NotSupportedException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * JTA の {@link UserTransaction} を用いた {@link AutoTransactionalDataSource} 実装です。
 *
 * @author asuka
 */
public class AutoUserTransactionalDataSource extends
		AbstractAutoTransactionalDataSource<DataSource> {

	private final UserTransaction userTransaction;

	/**
	 * 新たにインスタンスを構築します。
	 *
	 * @param dataSource ラップするデータソース
	 * @param userTransaction JTA のトランザクションオブジェクト
	 */
	public AutoUserTransactionalDataSource(DataSource dataSource,
			UserTransaction userTransaction) {
		super(dataSource);
		this.userTransaction = userTransaction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void beginTransaction() {
		try {
			if (userTransaction.getStatus() != Status.STATUS_ACTIVE) {
				userTransaction.begin();
			}
		} catch (SystemException e) {
			throw new RuntimeException(e);
		} catch (NotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

}
