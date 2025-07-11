package com.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MethodsTest {
	static Methods m;
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void adding() {
		assertEquals(5,m.mul(2,3));
	}
	
	
	
	@BeforeEach
	static void init() {
		m = new Methods();
	}

}
