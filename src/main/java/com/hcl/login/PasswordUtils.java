package com.hcl.login;

import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
//import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Utility class for password management
 * 
 * @author AjeetY
 * 
 */
public class PasswordUtils {
	// private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;

	/**
	 * method for generating salt for used for encryption/decryption
	 * 
	 * @param length strength of salt.
	 * @return salt key.
	 */
	public static String getSalt(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(2));
		} 
		return new String(returnValue);
	}

	/**
	 * method user internally for generating salt.
	 * 
	 * @param password character array of user input password.
	 * @param salt     byte array of stored salt.
	 * @return hash code used for encryption algorithm.
	 */

	public static byte[] hash(char[] password, byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		} finally {
			spec.clearPassword(); 
		}
	}

	/**
	 * method for generating secure password.
	 * 
	 * @param password password entered by user.
	 * @param salt     used for decryption, must be stored in db.
	 * @return encrypted password string.
	 */
	public static String generateSecurePassword(String password, String salt) {
		String returnValue = null;
		byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

		returnValue = Base64.getEncoder().encodeToString(securePassword);

		return returnValue;
	}

	/**
	 * method to verify user password from db.
	 * 
	 * @param providedPassword user provided password.
	 * @param securedPassword  encrypted password stored in db.
	 * @param salt             same salt which used to encrypt password, in real
	 *                         world scenario it should get from db.
	 * @return if password is verified or not.
	 */
	public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
		boolean returnValue = false;
		// Generate New secure password with the same salt
		String newSecurePassword = generateSecurePassword(providedPassword, salt);

		// Check if two passwords are equal
		returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
		return returnValue;
	}

}

//RANDOM.nextInt(ALPHABET.length())