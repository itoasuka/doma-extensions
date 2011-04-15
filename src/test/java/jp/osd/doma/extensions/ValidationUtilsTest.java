package jp.osd.doma.extensions;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class ValidationUtilsTest {

	@Test
	public void testValidateNull() {
		ValidationUtils.validateNull("abc");

		try {
			ValidationUtils.validateNull(null);
			fail();
		} catch (NullException e) {
			// OK
			assertEquals("NullException", e.getResourceKey());
		}
	}

	@Test
	public void testValidateMinLength() {
		ValidationUtils.validateMinLength("abc", 1);

		try {
			ValidationUtils.validateMinLength("abc", 4);
			fail();
		} catch (MinimumLengthException e) {
			// OK
			assertEquals("MinimumLengthException", e.getResourceKey());
			assertEquals("abc", e.getValue());
			assertEquals(4, e.getMinimum());
		}
	}

	@Test
	public void testValidateMaxLength() {
		ValidationUtils.validateMaxLength("abc", 5);

		try {
			ValidationUtils.validateMaxLength("abc", 2);
			fail();
		} catch (MaximumLengthException e) {
			// OK
			assertEquals("MaximumLengthException", e.getResourceKey());
			assertEquals("abc", e.getValue());
			assertEquals(2, e.getMaximum());
		}
	}

	@Test
	public void testValidatePattern() {
		ValidationUtils.validatePattern("123", "\\d+");

		try {
			ValidationUtils.validatePattern("abc", "\\d+");
			fail();
		} catch (PatternException e) {
			assertEquals("PatternException", e.getResourceKey());
			assertEquals("abc", e.getValue());
			assertEquals("\\d+", e.getPattern());
		}
	}

	@Test
	public void testValidateByteArrayLength() {
		ValidationUtils.validateByteArrayLength(new byte[3], 3);

		try {
			ValidationUtils.validateByteArrayLength(new byte[4], 3);
			fail();
		} catch (ByteArrayLengthException e) {
			assertEquals("ByteArrayLengthException", e.getResourceKey());
			assertEquals(3, e.getLength());
		}
	}

	@Test
	public void testValidationUtils() throws Exception {
		// カバレッジを埋めるためだけの不毛なテスト
		Constructor<ValidationUtils> c = ValidationUtils.class
				.getDeclaredConstructor();
		assertFalse(c.isAccessible());
		c.setAccessible(true);
		c.newInstance();
	}
}
