package test;

import static org.junit.Assert.*;

import org.junit.Test;

import ddassistant.DDSurvey;

public class TestDDSurvey {

	@Test
	public void testDDSurvey() {
		DDSurvey s1 = new DDSurvey();
		s1.depth = 123.0;
		s1.inclination = 32;
		s1.azimuth = 123.32;
		DDSurvey s2 = new DDSurvey(123.0, 123.32, 32);
		assertTrue(s1.equals(s2));
		DDSurvey s3 = new DDSurvey(0, 123.32, 32);
		assertTrue(s1.compareTo(s3) > 0);
		assertFalse(s1.equals(s3));
	}
}
