package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.service.OverdueService;

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
    void testGetOverdueRecordsNoException() {
        assertDoesNotThrow(() -> overdueService.getOverdueRecords());
    }
} 