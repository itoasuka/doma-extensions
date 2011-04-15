package jp.osd.doma.extensions;

/**
 * byte 配列の長さが不正な場合にスローされるドメイン構築例外です。
 * 
 * @author asuka
 */
public class ByteArrayLengthException extends DomainException {
	private static final long serialVersionUID = 5508814789509592132L;
	private static final String LENGTH = "length";

	/**
	 * 検証した値と正しい byte 配列の長さを指定して新しいドメイン構築例外を構築します。
	 * 
	 * @param value
	 *            検証した値
	 * @param length
	 *            正しい byte 配列の長さ
	 */
	public ByteArrayLengthException(byte[] value, int length) {
		super("Array length " + value.length + " is invalid. (Valid length is "
				+ length + ".)");
		setVariable(LENGTH, length);
	}

	/**
	 * 正しい byte 配列の長さを取得します。
	 *
	 * @return 正しい byte 配列の長さ 
	 */
	public int getLength() {
		return (Integer) getVariables().get(LENGTH);
	}
}
