package com.hcl.login;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class PasswordUtilsTest extends PasswordUtils {
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
 
	}
	
	@Test
	void testGetSalt() {
		String salt = PasswordUtils.getSalt(30);
		System.out.println(salt);
		//check if the value is correct
		assertEquals(salt, "222222222222222222222222222222");
	}
 
	@Test
	void testGenerateSecurePassword() {
		String encryptedPassword = PasswordUtils.generateSecurePassword("12345", "222222222222222222222222222222");
		System.out.println(encryptedPassword);
		assertEquals("mkDxC+GWBulIe9XXl7Bg/7THNwWNEGwzN1EOm1GV3a0=", encryptedPassword);
	}

	@Test
	void testVerifyUserPassword() {
		boolean isVerified = PasswordUtils.
				verifyUserPassword("12345", "mkDxC+GWBulIe9XXl7Bg/7THNwWNEGwzN1EOm1GV3a0=", "222222222222222222222222222222");
		
		assertEquals(true, isVerified);
	}
	 
	
	
}
