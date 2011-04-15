package jp.osd.doma.extensions;

/**
 * 基本型が文字列型であるドメインの生成時にスローされる実行時例外です。
 * 
 * @author asuka
 */
public class StringBaseDomainException extends DomainException {
	private static final long serialVersionUID = 4312354629769216888L;
	private final String value;

	/**
	 * ドメインのコンストラクタに指定した基本型の値を指定して新たに例外を生成します。
	 * 
	 * @param value
	 *            ドメインのコンストラクタに指定した基本型の値
	 */
	public StringBaseDomainException(String value) {
		super();
		this.value = value;
	}
	
	/**
	 * ドメインのコンストラクタに指定した基本型の値および詳細メッセージを指定して新たに例外を生成します。
	 *
	 * @param value ドメインのコンストラクタに指定した基本型の値
	 * @param message 詳細メッセージ
	 */
	public StringBaseDomainException(String value, String message) {
		super(message);
		this.value = value;
	}

	/**
	 * ドメインのコンストラクタに指定した基本型の値を取得します。
	 * 
	 * @return ドメインのコンストラクタに指定した基本型の値
	 */
	public String getValue() {
		return value;
	}
}
