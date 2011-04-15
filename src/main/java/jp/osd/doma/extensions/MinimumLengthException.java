package jp.osd.doma.extensions;

/**
 * 文字列がドメインで許容される最小長以上である場合にスローされる例外クラスです。
 * 
 * @author asuka
 */
public class MinimumLengthException extends StringBaseDomainException {
	private static final long serialVersionUID = -3840496810421483923L;
	private static final String MINIMUM = "minimum";

	/**
	 * 許容される文字列の最小長を指定して新たにオブジェクトを構築します。
	 * 
	 * @param value
	 *            ドメインのコンストラクタに指定した基本型の値
	 * @param minimum
	 *            許容される文字列の最小長
	 */
	public MinimumLengthException(String value, int minimum) {
		super(value);
		setVariable(MINIMUM, minimum);
	}

	/**
	 * 許容される文字列の最小長を取得します。
	 * 
	 * @return 許容される文字列の最小長
	 */
	public int getMinimum() {
		return (Integer) getVariables().get(MINIMUM);
	}
}
