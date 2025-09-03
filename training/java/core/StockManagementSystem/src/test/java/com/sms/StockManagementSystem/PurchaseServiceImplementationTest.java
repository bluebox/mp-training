package com.sms.StockManagementSystem;


import com.sms.Dao.Interfaces.PurchaseDao;
import com.sms.Exceptions.InvalidDataExceptions;

import com.sms.models.PurchaseDetails;
import com.sms.models.PurchaseHeader;
import com.sms.service.Interfaces.PurchaseServices;
import com.sms.service.implementations.PurchaseServiceImplementation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class PurchaseServiceImplementationTest {

    private PurchaseDao purchaseDao;
    private PurchaseServices purchaseService;
    
//    @BeforeEach
//   	void init() {
//   		MockitoAnnotations.openMocks(this);
//   		
//   		doNothing().when(validations).purchasevalidation(any());
//   	}

    @BeforeEach
    void setup() {
        purchaseDao = mock(PurchaseDao.class);
        purchaseService = new PurchaseServiceImplementation();
        ((PurchaseServiceImplementation) purchaseService).psd = purchaseDao;
    }

    @Test
    void testCreatePurchase_Success() {
        PurchaseDetails pd = new PurchaseDetails(
                1L, null, "P001", 5, 100, 18, 118, LocalDate.now().plusDays(10)
        );

        PurchaseHeader ph = new PurchaseHeader(
                null, 1L, 100, 18, 118,
                LocalDateTime.now(), "admin", List.of(pd)
        );

        when(purchaseDao.createPurchase(ph)).thenReturn(ph);

        PurchaseHeader result = purchaseService.createPurchase(ph);
        assertNotNull(result);
        verify(purchaseDao, times(1)).createPurchase(ph);
    }

    @Test
    void testCreatePurchase_NullHeader() {
        Exception ex = assertThrows(InvalidDataExceptions.class, () -> purchaseService.createPurchase(null));
        assertEquals("Invalid purchase header data", ex.getMessage());
    }

    @Test
    void testCreatePurchase_InvalidDetails() {
        PurchaseDetails pd = new PurchaseDetails(
                1L, null, "", 0, 0, 0, 0, LocalDate.now()
        );

        PurchaseHeader ph = new PurchaseHeader(
                null, 1L, 100, 18, 118,
                LocalDateTime.now(), "admin", List.of(pd)
        );

        Exception ex = assertThrows(InvalidDataExceptions.class, () -> purchaseService.createPurchase(ph));
        assertEquals("Invalid purchase detail data", ex.getMessage());
    }

    @Test
    void testViewAllPurchases() {
        List<PurchaseHeader> mockList = List.of(new PurchaseHeader());
        when(purchaseDao.viewAllPurchases()).thenReturn(mockList);

        List<PurchaseHeader> result = purchaseService.viewAllPurchases();
        assertEquals(1, result.size());
        verify(purchaseDao, times(1)).viewAllPurchases();
    }

    @Test
    void testViewPurchaseInDetail() {
        List<PurchaseDetails> mockList = List.of(new PurchaseDetails());
        when(purchaseDao.viewPurchaseInDetail(1L)).thenReturn(mockList);

        List<PurchaseDetails> result = purchaseService.viewPurchaseInDetail(1L);
        assertEquals(1, result.size());
        verify(purchaseDao, times(1)).viewPurchaseInDetail(1L);
    }
}

