package jp.osd.doma.extensions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ドメイン生成に関する例外クラスです。
 * 
 * @author asuka
 */
public class DomainException extends RuntimeException {
	private static final long serialVersionUID = -7795548270681700595L;
	private final Map<String, Object> variables = new HashMap<String, Object>();
	private String resourceKey = getClass().getSimpleName();

	/**
	 * 新規例外を構築します。
	 */
	public DomainException() {
		super();
	}

	/**
	 * 指定された詳細メッセージを使用して新規例外を構築します。
	 * 
	 * @param message
	 *            詳細メッセージ
	 */
	public DomainException(String message) {
		super(message);
	}

	/**
	 * この例外にひもづけられた変数値を取得します。
	 * 
	 * @return この例外にひもづけられた変数値
	 * @see #setVariable(String, Object)
	 */
	public Map<String, Object> getVariables() {
		return Collections.unmodifiableMap(variables);
	}

	/**
	 * この例外にひもづく変数値を設定します。このメソッドで設定した変数値は {@link #getVariables()}
	 * によって取り出すことができ、例外をキャッチした部分でメッセージを組み立てるときなどに使用することができます。
	 * 
	 * @param name
	 *            変数値の名前
	 * @param value
	 *            変数値
	 */
	public void setVariable(String name, Object value) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException(
					"Argument [[name]] cannot be null or an empty string");
		}
		if (value == null) {
			throw new IllegalArgumentException(
					"Argument [[value]] cannot be null or an empty string");
		}
		variables.put(name, value);
	}

	/**
	 * この例外にひもづくメッセージリソースのキーを取得します。この例外をキャッチした部分で例外に対応するメッセージをメッセージリソースから取得したい場合などに利用することができます。
	 * <P>
	 * デフォルトではこのメソッドは <code>getClass().getSimpleName()</code> の値を返しますが {@link #setResourceKey(String)} でこのメソッドが返すリソースキーを設定することもできます。
	 * 
	 * @return この例外にひもづくメッセージリソースのキー
	 */
	public String getResourceKey() {
		return resourceKey;
	}

	/**
	 * この例外にひもづくメッセージリソースのキーを設定します。
	 *
	 * @param resourceKey この例外にひもづくメッセージリソースのキー
	 * @see #getResourceKey()
	 */
	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

}
