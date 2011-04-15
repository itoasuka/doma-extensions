package jp.osd.doma.extensions;

import java.util.regex.Pattern;

/**
 * ドメイン構築時のバリデーションを行うユーティリティクラスです。
 * 
 * @author asuka
 */
public class ValidationUtils {
	/**
	 * ドメインオブジェクトを生成する際に与えた基本型の値が <code>null</code> でないかを判定します。
	 * 
	 * @param arg 判定対象の値
	 * @throws NullException 判定対象が <code>null</code> の場合
	 */
	public static void validateNull(Object arg) {
		if (arg == null) {
			throw new NullException();
		}
	}

	/**
	 * 文字列ドメインオブジェクトを生成される際に与えられた基本型の値の長さが許容される最小値以上かを判定します。
	 * 
	 * @param arg 基本型の値
	 * @param minLength 許容される最小長
	 * @throws MinimumLengthException 許容される最小長を下回っている場合
	 */
	public static void validateMinLength(String arg, int minLength) {
		if (arg.length() < minLength) {
			throw new MinimumLengthException(arg, minLength);
		}
	}
	
	/**
	 * 文字列ドメインオブジェクトを生成される際に与えられた基本型の値の長さが許容範囲内かを判定します。
	 * 
	 * @param arg 基本型の値
	 * @param maxLength 許容される最大長
	 * @throws MaximumLengthException 許容される最大長を超えている場合
	 */
	public static void validateMaxLength(String arg, int maxLength) {
		if (maxLength < arg.length()) {
			throw new MaximumLengthException(arg, maxLength);
		}
	}
	
	/**
	 * 指定した文字列が指定した正規表現パターンにマッチするかを判定します。
	 * 
	 * @param src
	 *            判定する文字列
	 * @param pattern
	 *            許容される正規表現パターン
	 * @throws PatternException
	 *             指定した文字列のパターンが指定した正規表現パターンにマッチしない場合
	 */
	public static void validatePattern(String src, String pattern) {
		if (!Pattern.matches(pattern, src)) {
			throw new PatternException(src, pattern);
		}
	}
	
	/**
	 * 指定した配列の長さが指定した長さと同じかを判定します。
	 * 
	 * @param byteArray 判定する配列
	 * @param length 許容される配列の長さ
	 * @throws ByteArrayLengthException 指定した配列の長さが指定した長さと異なる場合
	 */
	public static void validateByteArrayLength(byte[] byteArray, int length) {
		if (byteArray.length != length) {
			throw new ByteArrayLengthException(byteArray, length);
		}
	}

	private ValidationUtils() {
		// 何もしない
	}
}
