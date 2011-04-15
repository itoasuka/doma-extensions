package jp.osd.doma.extensions;

/**
 * 文字列がドメインで許容される最大長以上である場合にスローされる例外クラスです。
 * 
 * @author asuka
 */
public class MaximumLengthException extends StringBaseDomainException {
	private static final long serialVersionUID = 5916329140388138667L;
	private static final String MAXIMUM = "maximum";

	/**
	 * 許容される文字列の最大長を指定して新たにオブジェクトを構築します。
	 * 
	 * @param value
	 *            ドメインのコンストラクタに指定した基本型の値
	 * @param maximum
	 *            許容される文字列の最大長
	 */
	public MaximumLengthException(String value, int maximum) {
		super(value);
		setVariable(MAXIMUM, maximum);
	}

	/**
	 * 許容される文字列の最大長を取得します。
	 * 
	 * @return 許容される文字列の最大長
	 */
	public int getMaximum() {
		return (Integer) getVariables().get(MAXIMUM);
	}
}
