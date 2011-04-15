package jp.osd.doma.extensions;

/**
 * 文字列のパターンが指定されたパターンにマッチしない場合にスローされるドメイン構築例外です。
 * 
 * @author asuka
 */
public class PatternException extends StringBaseDomainException {
	private static final long serialVersionUID = 3134991895289430621L;
	private static final String PATTERN = "pattern";

	/**
	 * 検証した値と許容される正規表現パターンを指定してドメイン構築例外を生成します。
	 * 
	 * @param value
	 *            検証した値
	 * @param pattern
	 *            許容される正規表現パターン
	 */
	public PatternException(String value, String pattern) {
		super(value, "\"" + value + "\" does not match a pattern of \""
				+ pattern + "\".");
		setVariable(PATTERN, pattern);
	}

	/**
	 * 許容される正規表現パターンを取得します。
	 * 
	 * @return 許容される正規表現パターン
	 */
	public String getPattern() {
		return (String) getVariables().get(PATTERN);
	}
}
