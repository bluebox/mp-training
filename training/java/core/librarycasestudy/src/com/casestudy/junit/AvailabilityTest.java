package com.casestudy.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.casestudy.domain.Availability;

public class AvailabilityTest {

	@Test
	public void testGetCode() {
		assertEquals("A", Availability.AVAILABLE.getCode());
		assertEquals("I", Availability.ISSUED.getCode());
	}

	@Test
	public void testFromCodeValid() {
		assertEquals(Availability.AVAILABLE, Availability.fromCode("A"));
		assertEquals(Availability.ISSUED, Availability.fromCode("I"));

		// Test lowercase input
		assertEquals(Availability.AVAILABLE, Availability.fromCode("a"));
		assertEquals(Availability.ISSUED, Availability.fromCode("i"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromCodeNull() {
		Availability.fromCode(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromCodeInvalid() {
		Availability.fromCode("X"); // Invalid code
	}
}
