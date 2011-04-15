/*
 * 作成日 : 2011/04/15
 */
package jp.osd.doma.extensions.jdbc.tx;

import javax.sql.DataSource;

/**
 * コネクションの取得と同時に（まだ開始していなければ）トランザクションを開始するデータソースのインタフェースです。
 *
 * @author asuka
 */
public interface AutoTransactionalDataSource extends DataSource {

}
