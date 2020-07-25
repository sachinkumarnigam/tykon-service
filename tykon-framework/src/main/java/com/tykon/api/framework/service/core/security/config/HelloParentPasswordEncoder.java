package com.tykon.api.framework.service.core.security.config;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.bson.internal.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HelloParentPasswordEncoder implements PasswordEncoder{

	Logger logger = LoggerFactory.getLogger(HelloParentPasswordEncoder.class);
	@Override
	public String encode(CharSequence rawPassword) {
		String passwordHash = null;;
		try {
			byte[] passwordSalt = this.getSalt();
			passwordHash = this.generateStorngPasswordHash(rawPassword.toString(), passwordSalt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			logger.error("error in encrypting password",e);
		}
		return passwordHash;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			return this.verifyPassword(rawPassword.toString(), encodedPassword);
		} catch (Exception e) {
			logger.error("error in decrypting password",e);
		}
		return false;
	}
	
    private boolean verifyPassword(String password, String passwordHash) throws Exception, InvalidKeySpecException {
    	byte[] salt = new byte[16];
        byte[] decodedString = Base64.decode(passwordHash);
        for (int i = 1; i < decodedString.length; i++) {
        	salt[i-1] = decodedString[i];
			if(i==16) {
				break;
			}
		}
        String newHash = generateStorngPasswordHash(password, salt);
        return passwordHash.equals(newHash);
    }
     

	
    private String generateStorngPasswordHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 32 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        byte []pwd = new byte[1+16+32];
        pwd[0]=0x00;
        for (int i = 0; i < salt.length; i++) {
        	pwd[i+1] = salt[i];
		}
        for (int i = 0; i < hash.length; i++) {
        	pwd[i+salt.length+1] = hash[i];
		}
        return Base64.encode(pwd);//iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
     
    private byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

}
