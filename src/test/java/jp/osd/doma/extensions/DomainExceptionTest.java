/*
 * 作成日 : 2011/04/15
 */
package jp.osd.doma.extensions;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author asuka
 */
public class DomainExceptionTest {

	/**
	 * {@link jp.osd.doma.extensions.DomainException#setVariable(java.lang.String, java.lang.Object)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testSetVariable() {
		DomainException de = new DomainException();
		try {
			de.setVariable(null, "abc");
		} catch (IllegalArgumentException e) {
			// OK;
			assertEquals("Argument [[name]] cannot be null or an empty string",
					e.getMessage());
		}
		try {
			de.setVariable("", "abc");
		} catch (IllegalArgumentException e) {
			// OK;
			assertEquals("Argument [[name]] cannot be null or an empty string",
					e.getMessage());
		}
		try {
			de.setVariable("abc", null);
		} catch (IllegalArgumentException e) {
			// OK;
			assertEquals("Argument [[value]] cannot be null or an empty string",
					e.getMessage());
		}
		try {
			de.setVariable("abc", "");
		} catch (IllegalArgumentException e) {
			// OK;
			assertEquals("Argument [[value]] cannot be null or an empty string",
					e.getMessage());
		}
	}

	/**
	 * {@link jp.osd.doma.extensions.DomainException#setResourceKey(java.lang.String)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testSetResourceKey() {
		DomainException e = new DomainException();
		e.setResourceKey("abc");
		assertEquals("abc", e.getResourceKey());
	}

}
