package com.casestudyjunit;

import org.junit.Test;

import com.casestudy.RecordStatus;

import static org.junit.Assert.*;

public class RecordStatusTest {

    @Test
    public void testGetCode() {
        assertEquals("I", RecordStatus.ISSUED.getCode());
        assertEquals("R", RecordStatus.RETURNED.getCode());
    }

    @Test
    public void testFromCodeValid() {
        assertEquals(RecordStatus.ISSUED, RecordStatus.fromCode("I"));
        assertEquals(RecordStatus.RETURNED, RecordStatus.fromCode("R"));

        // Case-insensitive check
        assertEquals(RecordStatus.ISSUED, RecordStatus.fromCode("i"));
        assertEquals(RecordStatus.RETURNED, RecordStatus.fromCode("r"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromCodeInvalid() {
        RecordStatus.fromCode("X");  // invalid code should throw exception
    }
}
