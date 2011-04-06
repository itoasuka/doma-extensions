package jp.osd.doma.extensions.internal;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.slf4j.LoggerFactory;

/**
 * ログメッセージにリソースバンドルを用いる SLF4J ラッパーロガーです。
 * 
 * @author asuka
 */
public class Logger {
	private final ResourceBundle bundle;
	private final org.slf4j.Logger logger;

	/**
	 * ロガーを取得します。
	 * 
	 * @param className
	 *            ロガー名に使用するクラス名
	 * @param bundle
	 *            ログメッセージのリソースバンドル
	 * @return ロガー
	 */
	public static Logger getLogger(String className, ResourceBundle bundle) {
		org.slf4j.Logger l = LoggerFactory.getLogger(className);
		return new Logger(l, bundle);
	}

	private Logger(org.slf4j.Logger logger, ResourceBundle bundle) {
		this.logger = logger;
		this.bundle = bundle;
	}

	/**
	 * デバッグレベルのロギングが有効になっているかを取得します。
	 * 
	 * @return デバッグレベルのロギングが有効になっている場合 <code>true</code>
	 */
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	/**
	 * デバッグレベルでログを取ります。
	 * 
	 * @param codes
	 *            ログメッセージのメッセージコード
	 * @param arguments
	 *            ログメッセージに埋め込む引数
	 */
	public void debug(MessageCodes codes, Object... arguments) {
		if (logger.isDebugEnabled()) {
			logger.debug(getString(codes, arguments));
		}
	}

	/**
	 * エラーレベルでログを取ります。
	 *
	 * @param throwable エラーの原因となった例外
	 * @param codes
	 *            ログメッセージのメッセージコード
	 * @param arguments
	 *            ログメッセージに埋め込む引数
	 */
	public void error(Throwable throwable, MessageCodes codes,
			Object... arguments) {
		if (logger.isErrorEnabled()) {
			logger.error(getString(codes, arguments), throwable);
		}
	}

	private String getString(MessageCodes codes, Object... arguments) {
		String pattern = bundle.getString(codes.toString());
		if (arguments.length == 0) {
			return pattern;
		}
		return MessageFormat.format(pattern, arguments);
	}
}
