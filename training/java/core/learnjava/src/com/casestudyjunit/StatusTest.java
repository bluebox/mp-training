package com.casestudyjunit;

import org.junit.Test;

import com.casestudy.Status;

import static org.junit.Assert.*;

public class StatusTest {

    @Test
    public void testGetCode() {
        assertEquals("A", Status.ACTIVE.getCode());
        assertEquals("I", Status.INACTIVE.getCode());
    }

    @Test
    public void testFromCode_ValidCodes() {
        assertEquals(Status.ACTIVE, Status.fromCode("A"));
        assertEquals(Status.INACTIVE, Status.fromCode("I"));
        assertEquals(Status.ACTIVE, Status.fromCode("a")); // case-insensitive
        assertEquals(Status.INACTIVE, Status.fromCode("i")); // case-insensitive
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromCode_InvalidCode() {
        Status.fromCode("X");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromCode_NullCode() {
        Status.fromCode(null);
    }
}
