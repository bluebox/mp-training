package com.medplus.marketing.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.medplus.discounts.PromotionException;
import com.medplus.discounts.domain.ComplimentaryProduct;
import com.medplus.discounts.domain.UserMetaData;
import com.medplus.marketing.config.PromotionTestConfig;
import com.medplus.marketing.domain.CampaignSearchCriteria;
import com.medplus.marketing.domain.ComplimentaryPromotion;
import com.medplus.marketing.domain.ComplimentarySlab;
import com.medplus.marketing.service.ComplimentaryPromotionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Disabled
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PromotionTestConfig.class)
class ComplimentaryPromotionTest {

	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	ComplimentaryPromotionService service = context.getBean(ComplimentaryPromotionService.class);

	private ComplimentaryPromotion getComplimentaryPromotion(String name) {
		ComplimentaryPromotion complimentaryPromotion = new ComplimentaryPromotion();
		complimentaryPromotion.setName(name);
		complimentaryPromotion.setAllCustomers(true);
		complimentaryPromotion.setMessageDisplayPercentage(40.0);
		complimentaryPromotion.setFromDate(LocalDateTime.now().plusDays(7));
		complimentaryPromotion.setToDate(LocalDateTime.now().plusDays(8));
		complimentaryPromotion.setStatus("I");
		complimentaryPromotion.setApplicableType(5);
		complimentaryPromotion.setChannels(Arrays.asList(1,4));
		complimentaryPromotion.setLoyalties(Arrays.asList(3,4));
		complimentaryPromotion.setRegions(Arrays.asList("INTG","INAP"));

		ComplimentarySlab complimentarySlab = new ComplimentarySlab();
		complimentarySlab.setName("Test slab 5");
		complimentarySlab.setInvoiceAmount(250.6);
		complimentaryPromotion.setComplimentarySlab(complimentarySlab);
		
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setDateCreated(LocalDateTime.now());	
		userMetaData.setCreatedBy("Otg01057");	
		complimentaryPromotion.setUserMetaData(userMetaData);
		
		ComplimentaryProduct complimentaryProduct1 = new ComplimentaryProduct();
		complimentaryProduct1.setAutoAdd(true);
		complimentaryProduct1.setProductId("DOLO0030");
		complimentaryProduct1.setDiscount(45.50);
		complimentaryProduct1.setQuantity(21);
		
		ComplimentaryProduct complimentaryProduct2 = new ComplimentaryProduct();
		complimentaryProduct2.setAutoAdd(false);
		complimentaryProduct2.setProductId("DOLO0031");
		complimentaryProduct2.setDiscount(45.50);
		complimentaryProduct2.setQuantity(21);
		complimentaryPromotion.setComplimentaryProducts(new HashSet<>(Arrays.asList(complimentaryProduct1, complimentaryProduct2)));
		
		return complimentaryPromotion;
	}
	
	@Test
	void createComplimentaryPromotionTestWithoutStatus() { 	
		ComplimentaryPromotion complimentaryPromotion = new ComplimentaryPromotion();
		complimentaryPromotion.setStatus("I");
		complimentaryPromotion.setName("test comp 45");
		complimentaryPromotion.setAllCustomers(false);
		complimentaryPromotion.setMessageDisplayPercentage(11.0);
		complimentaryPromotion.setFromDate(LocalDateTime.now().plusDays(7));
		complimentaryPromotion.setToDate(LocalDateTime.now().plusDays(8));
		
		complimentaryPromotion.setApplicableType(5);
		complimentaryPromotion.setChannels(Arrays.asList(1,4));
		complimentaryPromotion.setLoyalties(Arrays.asList(3,4));
		complimentaryPromotion.setRegions(Arrays.asList("INTG","INAP"));

		ComplimentarySlab complimentarySlab = new ComplimentarySlab();
		complimentarySlab.setName("Test slab 5");
		complimentarySlab.setInvoiceAmount(150.6);
		complimentaryPromotion.setComplimentarySlab(complimentarySlab);
		
		ComplimentaryProduct complimentaryProduct1 = new ComplimentaryProduct();
		complimentaryProduct1.setAutoAdd(true);
		complimentaryProduct1.setProductId("DOLO0030");
		complimentaryProduct1.setDiscount(45.50);
		complimentaryProduct1.setQuantity(21);
		
		ComplimentaryProduct complimentaryProduct2 = new ComplimentaryProduct();
		complimentaryProduct2.setAutoAdd(false);
		complimentaryProduct2.setProductId("FOMO0031");
		complimentaryProduct2.setDiscount(45.50);
		complimentaryProduct2.setQuantity(21);
		complimentaryPromotion.setComplimentaryProducts(new HashSet<>(Arrays.asList(complimentaryProduct1, complimentaryProduct2)));
		complimentaryPromotion.setReferenceProductIds(new HashSet<>(Arrays.asList("DOLO0031","DOLO0030")));
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setDateCreated(LocalDateTime.now());	
		userMetaData.setCreatedBy("Otg01057");	
		complimentaryPromotion.setUserMetaData(userMetaData);
		complimentaryPromotion.setCompositionIds(new HashSet<>(Arrays.asList(123,1233)));
		complimentaryPromotion.setCustomerIds(new HashSet<>(Arrays.asList(345678l,899022l)));
		
		ComplimentaryPromotion createdComplimemtaryPromotion = service.createComplimentaryPromotion(complimentaryPromotion);
		assertNotNull(createdComplimemtaryPromotion);
		log.info("created complimentary promotion {}",createdComplimemtaryPromotion);
	}
	
	@Test
	void createComplimentaryPromotionTestWithStatus() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		ComplimentaryPromotion createdComplimemtaryPromotion = service.createComplimentaryPromotion(complimentaryPromotion);
		assertNotNull(createdComplimemtaryPromotion);
		log.info("created complimentary promotion {}",createdComplimemtaryPromotion);
	}
	
	@Test
	void testChannelsLoyalitiesRegionsNull() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.setChannels(null);
		complimentaryPromotion.setLoyalties(null);
		complimentaryPromotion.setRegions(null);
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testApplicableType() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.setApplicableType(6);
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testDates() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.setFromDate(LocalDateTime.now().plusDays(-1));
		complimentaryPromotion.setToDate(LocalDateTime.now().plusDays(1));
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testToDateBeforeFromDate() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 6");
		complimentaryPromotion.setFromDate(LocalDateTime.now().plusDays(2));
		complimentaryPromotion.setToDate(LocalDateTime.now().plusDays(2));
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testDateDifference() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.setFromDate(LocalDateTime.now().plusDays(1));
		complimentaryPromotion.setToDate(LocalDateTime.now().plusDays(782));
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	
	@Test
	void testMessageDisplayPercentage() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.setMessageDisplayPercentage(0.0);
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testSlabNameNull() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.getComplimentarySlab().setName(null);
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testSlabName() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.getComplimentarySlab().setName("_testSlabName");
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testSlabNameLength() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.getComplimentarySlab().setName("xys");
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testSlabInvoiceAmount() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.getComplimentarySlab().setInvoiceAmount(-17832278.0);
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testSlabInvoiceAmountLength() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.getComplimentarySlab().setInvoiceAmount(1783224.0);
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testPromotionName() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("-TestCompl");
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testPromotionNameLength() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("Tes");
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testIsCustomersFileEmpty() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.setAllCustomers(false);
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	@Test
	void testIsProductsFileEmpty() {
		ComplimentaryPromotion complimentaryPromotion = getComplimentaryPromotion("test comp 5");
		complimentaryPromotion.setComplimentaryProducts(null);
		Assertions.assertThrows(PromotionException.class, () ->  service.createComplimentaryPromotion(complimentaryPromotion));
	}
	
	
	@Test
	void testGetComplimentaryPromotionById() {
		ComplimentaryPromotion promo = service.getComplimentaryPromotionById(520l);
		assertNotNull(promo);
		log.info("get complimentary by Id {}",promo);
	}
	
	@Test
	void testGetComplimentaryDetailsBySearchCriteria() {
		CampaignSearchCriteria searchCriteria = new CampaignSearchCriteria();
		searchCriteria.setCampaignId(520);
		Map<String, Object> promoObj = service.getComplimentaryPromotionList(searchCriteria, Arrays.asList(5));
		assertNotNull(promoObj);
		log.info("get complimentary by search criteria {}",promoObj);	
	}
	
	@Test
	void testGetComplimentaryDetailsBySearchCriteriaWithOutDates() {
		CampaignSearchCriteria searchCriteria = new CampaignSearchCriteria();
		Map<String, Object> promoObj = service.getComplimentaryPromotionList(searchCriteria, Arrays.asList(5));
		assertNotNull(promoObj);
		log.info("get complimentary by search criteria {}",promoObj);	
	}
	
	@Test
	void testComplimentaryDetailsBySearchCriteria() {
		CampaignSearchCriteria searchCriteria = new CampaignSearchCriteria();
		searchCriteria.setCreatedBy("otg01057");
		searchCriteria.setApplicableTypes(Arrays.asList(7));

		Map<String, Object> promoObj = service.getComplimentaryPromotionList(searchCriteria, Arrays.asList(5));
		assertNotNull(promoObj);
		log.info("get complimentary by search criteria {}",promoObj);	
	}
	
	@Test
	void testGetComplimentaryDetailsBySearchCriteriaWithDates() {
		CampaignSearchCriteria searchCriteria = new CampaignSearchCriteria();
		searchCriteria.setFromDate(LocalDateTime.now().plusDays(-3));
		searchCriteria.setToDate(LocalDateTime.now().plusDays(3));
		Map<String, Object> promoObj = service.getComplimentaryPromotionList(searchCriteria, Arrays.asList(5));
		assertNotNull(promoObj);
		log.info("get complimentary by search criteria {}",promoObj);
	}
	
	@Test
	void testApproveComplimentaryPromotion() {
		Assertions.assertThrows(PromotionException.class,() -> service.approveComplimentaryPromotion(520l, LocalDateTime.now().plusDays(10), Arrays.asList(5), "otg01057"));
	}
	
	@Test
	void testApprovedComplimentaryPromotion() {
		Assertions.assertThrows(PromotionException.class, () -> service.approveComplimentaryPromotion(520l, LocalDateTime.now().plusDays(10), Arrays.asList(5), "otg01057"));
	}
	
	@Test
	void testApproveComplimentaryPromotionToDate() {
		Assertions.assertThrows(PromotionException.class, () -> service.approveComplimentaryPromotion(520l, LocalDateTime.now().plusDays(-1), Arrays.asList(5), "otg01057"));
	}

	@Test
	void testApprove() {
		boolean isApproved = service.approveComplimentaryPromotion(502l, LocalDateTime.now().plusDays(10), Arrays.asList(5), null);
	    assertTrue(!isApproved);
	}
	
	@Test
	void testApproveWithWrongApplicableTypes() {
		Assertions.assertThrows(PromotionException.class, () -> service.approveComplimentaryPromotion(520l, LocalDateTime.now().plusDays(10), Arrays.asList(7), "otg01057"));
	}
	
	@Test
	void testApproveWithApplicableTypesAsNull() {
		Assertions.assertThrows(PromotionException.class, () -> service.approveComplimentaryPromotion(513l, LocalDateTime.now().plusDays(10), null, "otg01057"));
	}
	
	@Test
	void testUpdateActiveComplimentaryToDateForExpiredPromotion() {
		Assertions.assertThrows(PromotionException.class, () -> service.updateComplimentaryToDate(515l,  LocalDateTime.now().plusDays(10), Arrays.asList(5), "otg01057", "A"));
	}
	
	@Test
	void testUpdateActiveComplimentaryToDate() {
		assertTrue(service.updateComplimentaryToDate(520l,  LocalDateTime.now().plusDays(20), Arrays.asList(5), "otg01057", "A"));
	}
	
	@Test
	void testUpdateInActiveComplimentaryToDate() {
		assertTrue(service.updateComplimentaryToDate(531l,  LocalDateTime.now().plusDays(10), Arrays.asList(5), "otg01057", "I"));
	}
	
	@Test
	void testUpdateInActiveComplimentaryToDateWithActiveStatus() {
		Assertions.assertThrows(PromotionException.class, () -> service.updateComplimentaryToDate(531l,  LocalDateTime.now().plusDays(10), Arrays.asList(5), "otg01057", "A"));
	}
	
	@Test
	void testRejectWithStatusActive() {
		Assertions.assertThrows(PromotionException.class, () -> service.rejectComplimentaryPromotion(514l, "otg01057", "test remarks", Arrays.asList(5)));
	}
	
	@Test
	void testRejectWithStatusInActive() {
		assertTrue(service.rejectComplimentaryPromotion(487l, "otg01057", "test remarks", Arrays.asList(5)));
	}
	
	@Test
	void testRejectWithStatusReject() {
		Assertions.assertThrows(PromotionException.class, () -> service.rejectComplimentaryPromotion(487l, "otg01057", "test remarks", Arrays.asList(5)));
	}
	
	@Test
	void testRejectWithNullFields() {
		Assertions.assertThrows(PromotionException.class, () -> service.rejectComplimentaryPromotion(487l, "otg01057", "test remarks", null));
	}
	
	@Test
	void testGetProducts() {
	    Set<ComplimentaryProduct> refProducts = service.getComplimentaryProducts(534L, 35L);
	    ComplimentaryProduct product1 = new ComplimentaryProduct();
	    product1.setProductId("DOLO0030");
	    product1.setQuantity(21l);
	    product1.setDiscount(45.5);
	    product1.setAutoAdd(true);
	    assertTrue(refProducts.contains(product1));
	}
	
	
	@Test
	void testGetProductsWithSlab() {
	    Set<ComplimentaryProduct> refProducts = service.getComplimentaryProducts(534L, 12l);
	    assertTrue(refProducts.size() == 0);
	}
	
	@Test
	void testGetReferenceProducts() {
	    Set<String> refProducts = service.getComplimentaryReferenceProducts(534L);
	    assertTrue(refProducts.contains("DOLO0030"));
	    assertTrue(refProducts.contains("DOLO0031"));
	}
	
	@Test
	void testGetReferenceCompositions() {
		Set<Integer> refCompositions = service.getComplimentaryRefCompositons(534l);
		assertTrue(refCompositions.contains("1233"));
	}
	
	@Test
	void testGetReferenceCompositionsForID() {
		Set<Integer> refCompositions = service.getComplimentaryRefCompositons(524l);
		assertTrue(refCompositions.isEmpty());
	}
	
	@Test
	void testGetCustomers() {
		Set<Long> customers = service.getComplimentaryPromotionCustomers(534l);
		assertTrue(customers.contains(899022l));
		assertTrue(customers.contains(345678l));
	}
	
	@Test
	void testAutoReject() {
	    Assertions.assertDoesNotThrow(() -> service.autoRejectClosedComplimentaryPromotions());    
	}
	

	@Test
	void testUpdateComplimentaryPromotionWithSatusActive() {
		ComplimentaryPromotion promo = new ComplimentaryPromotion();
		promo.setComplimentaryId(539);
		Assertions.assertThrows(PromotionException.class, () -> service.updateComplimentaryPromotion(promo));
	}
	
	@Test
	void testUpdateComplimentaryPromotion() {
		ComplimentaryPromotion complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setModifiedBy("o28401");
		userMetaData.setDateModified(LocalDateTime.now());
		complimentaryPromotion.setUserMetaData(userMetaData);
		complimentaryPromotion.setRemoveComplimentaryProducts(new HashSet<String>(Arrays.asList("DOLO0031","DOLO00011")));
		ComplimentaryProduct complimentaryProduct2 = new ComplimentaryProduct();
		complimentaryProduct2.setAutoAdd(false);
		complimentaryProduct2.setProductId("DOLO0035");
		complimentaryProduct2.setDiscount(45.50);
		complimentaryProduct2.setQuantity(21);
		complimentaryPromotion.setComplimentaryProducts(new HashSet<>(Arrays.asList(complimentaryProduct2)));
		complimentaryPromotion.setReferenceProductIds(new HashSet<>(Arrays.asList("DOLO0037","KOLO0041")));
		complimentaryPromotion.setRemoveReferenceProductIds(new HashSet<>(Arrays.asList("DOLO0035")));
		complimentaryPromotion.setRemoveReferenceProductIds(new HashSet<>(Arrays.asList("DOLO0035")));
		complimentaryPromotion.setCompositionIds(new HashSet<>(Arrays.asList(123,12313)));
		complimentaryPromotion.setRemoveCompositionIds(new HashSet<>(Arrays.asList(1233)));
		
		service.updateComplimentaryPromotion(complimentaryPromotion);
		Set<ComplimentaryProduct> dbProducts=service.getComplimentaryProducts(533l, complimentaryPromotion.getComplimentarySlab().getComplimentarySlabId());
		log.info("db Pron {}",dbProducts);
		Set<String> dbProductIds = dbProducts.stream().map(product -> product.getProductId()).collect(Collectors.toSet());
		assertTrue(dbProductIds.contains("DOLO0035"));
		assertTrue(!dbProducts.contains("DOLO0031"));
		assertTrue(!dbProducts.contains("DOLO00011"));
	}
	
	@Test
	void testUpdateReferenceProducts() {
		ComplimentaryPromotion complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setModifiedBy("o28401");
		userMetaData.setDateModified(LocalDateTime.now());
		complimentaryPromotion.setUserMetaData(userMetaData);
		complimentaryPromotion.setReferenceProductIds(new HashSet<>(Arrays.asList("DOLO0032","DOLO0030")));
		complimentaryPromotion.setRemoveReferenceProductIds(new HashSet<>(Arrays.asList("DOLO0031")));
		service.updateComplimentaryPromotion(complimentaryPromotion);
		Set<String> refProductIds=service.getComplimentaryReferenceProducts(533l);
		assertTrue(refProductIds.contains("DOLO0032"));
		assertTrue(refProductIds.contains("DOLO0030"));
		assertTrue(!refProductIds.contains("DOLO0031"));
	}
	
	@Test
	void testUpdateCompositionIds() {
		ComplimentaryPromotion complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setModifiedBy("o28401");
		userMetaData.setDateModified(LocalDateTime.now());
		complimentaryPromotion.setUserMetaData(userMetaData);
		complimentaryPromotion.setCompositionIds(new HashSet<>(Arrays.asList(99354)));
		complimentaryPromotion.setRemoveCompositionIds(new HashSet<>(Arrays.asList(12313)));
		service.updateComplimentaryPromotion(complimentaryPromotion);
		Set<Integer> compositionIds=service.getComplimentaryRefCompositons(533l);
		assertTrue(compositionIds.contains(99354));
		assertTrue(!compositionIds.contains(12313));
	}
	
	@Test
	void testRegionsUpdate() {
		ComplimentaryPromotion complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setModifiedBy("o28401");
		userMetaData.setDateModified(LocalDateTime.now());
		complimentaryPromotion.setUserMetaData(userMetaData);
		complimentaryPromotion.setRegions(Arrays.asList("INTN","INKN"));
		service.updateComplimentaryPromotion(complimentaryPromotion);
		complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		assertTrue(complimentaryPromotion.getRegions().contains("INTN"));
		assertTrue(complimentaryPromotion.getRegions().contains("INKN"));
	}
	
	@Test
	void testLoyaltiesUpdate() {
		ComplimentaryPromotion complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setModifiedBy("o28401");
		userMetaData.setDateModified(LocalDateTime.now());
		complimentaryPromotion.setUserMetaData(userMetaData);
		complimentaryPromotion.setLoyalties(Arrays.asList(3,6));
		service.updateComplimentaryPromotion(complimentaryPromotion);
		complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		assertTrue(complimentaryPromotion.getLoyalties().contains(3));
		assertTrue(complimentaryPromotion.getLoyalties().contains(6));
	}
	
	@Test
	void testChannelsUpdate() {
		ComplimentaryPromotion complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setModifiedBy("o28401");
		userMetaData.setDateModified(LocalDateTime.now());
		complimentaryPromotion.setUserMetaData(userMetaData);
		complimentaryPromotion.setChannels(Arrays.asList(3,6));
		service.updateComplimentaryPromotion(complimentaryPromotion);
		complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		assertTrue(complimentaryPromotion.getChannels().contains(3));
		assertTrue(complimentaryPromotion.getChannels().contains(6));
	}
	
	@Test
	void testPromotionHeaderUpdate() {
		ComplimentaryPromotion complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setModifiedBy("o28402");
		LocalDateTime dateModified = LocalDateTime.now();
		userMetaData.setDateModified(dateModified);
		complimentaryPromotion.setUserMetaData(userMetaData);
		complimentaryPromotion.setMessageDisplayPercentage(65.0);
		complimentaryPromotion.setToDate(LocalDateTime.now().plusDays(11));
		service.updateComplimentaryPromotion(complimentaryPromotion);
		complimentaryPromotion = service.getComplimentaryPromotionById(533l);
		assertTrue("o28402".equalsIgnoreCase(complimentaryPromotion.getUserMetaData().getModifiedBy()));		
		assertTrue(dateModified.getMonth().equals(complimentaryPromotion.getUserMetaData().getDateModified().getMonth()));
		assertTrue(dateModified.getYear() == complimentaryPromotion.getUserMetaData().getDateModified().getYear());
		assertTrue(dateModified.getDayOfMonth() == complimentaryPromotion.getUserMetaData().getDateModified().getDayOfMonth());
		assertTrue(65.0 == complimentaryPromotion.getMessageDisplayPercentage());
	}
	
	@Test
	void testCustomersUpdate() {
		ComplimentaryPromotion complimentaryPromotion = service.getComplimentaryPromotionById(538l);
		UserMetaData userMetaData = new UserMetaData();
		userMetaData.setModifiedBy("o28401");
		userMetaData.setDateModified(LocalDateTime.now());
		complimentaryPromotion.setUserMetaData(userMetaData);
		complimentaryPromotion.setCustomerIds(new HashSet<>(Arrays.asList(897678l)));
		complimentaryPromotion.setRemoveCustomerIds(new HashSet<>(Arrays.asList(377678l,997678l)));
		
		service.updateComplimentaryPromotion(complimentaryPromotion);
		Set<Long> customers = service.getComplimentaryPromotionCustomers(538l);
		log.info("customers {}",customers);
		assertTrue(customers.contains(897678l));
		assertTrue(!customers.contains(377678l));
	}
}

