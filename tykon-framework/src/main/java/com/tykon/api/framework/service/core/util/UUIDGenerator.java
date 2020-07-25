package com.tykon.api.framework.service.core.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class UUIDGenerator {

	private final static String salt = "12hfkdhfksdk34";
	private final static int iterations = 1;
	private final static int keyLength = 512;

	public static String hashPassword(final String emailIdAndExpiray) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(emailIdAndExpiray.toCharArray(), salt.getBytes(), iterations, keyLength);
			SecretKey key = skf.generateSecret(spec);
			return Hex.encodeHexString(key.getEncoded());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getOTP() {
		int length = 4;
		// Creating object of Random class
		Random obj = new Random();
		char[] otp = new char[length];
		for (int i = 0; i < length; i++) {
			otp[i] = (char) (obj.nextInt(10) + 48);
		}
		System.out.print("Your OTP is : " + new String(otp));
		return new String(otp);
	}

	public static void main(String[] args) {
		System.out.println(getOTP());
	}
}
