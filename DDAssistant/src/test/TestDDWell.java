package test;

import static org.junit.Assert.*;

import org.junit.Test;

import ddassistant.DDWell;

public class TestDDWell {
	@Test
	public void testDDWell() {
		// test kickoff
		DDWell well = new DDWell();
		well.addKickOff(4000, 5000, 165, 85);
		assertTrue(true);
	}
}
