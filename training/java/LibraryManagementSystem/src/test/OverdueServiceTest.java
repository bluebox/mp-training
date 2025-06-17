package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.OverdueService;

class OverdueServiceTest {
    OverdueService overdueService;

    @BeforeEach
    void setUp() {
        overdueService = new OverdueService();
    }

    @Test
    void testNotYetImplemented() {
        fail("Not yet implemented");
    }

    @Test
    void testGetOverdueRecords_NoException() {
        assertDoesNotThrow(() -> overdueService.getOverdueRecords());
    }
} 