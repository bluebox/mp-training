package com.casestudy.junit;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.casestudy.domain.Gender;

public class GenderTest {

    @Test
    public void testGetCode() {
        assertEquals("M", Gender.MALE.getCode());
        assertEquals("F", Gender.FEMALE.getCode());
    }

    @Test
    public void testFromCodeValid() {
        assertEquals(Gender.MALE, Gender.fromCode("M"));
        assertEquals(Gender.MALE, Gender.fromCode("m")); // case-insensitive

        assertEquals(Gender.FEMALE, Gender.fromCode("F"));
        assertEquals(Gender.FEMALE, Gender.fromCode("f")); // case-insensitive
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromCodeInvalid() {
        Gender.fromCode("X");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromCodeNull() {
        Gender.fromCode(null);
    }
}

