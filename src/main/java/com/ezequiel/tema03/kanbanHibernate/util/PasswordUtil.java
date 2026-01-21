package com.ezequiel.tema03.kanbanHibernate.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordUtil {

  private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
  private static final int ITERATIONS = 100000;
  private static final int SALT_LENGTH = 16;
  private static final int KEY_LENGTH = 256;

  /**
   * Genera un hash seguro para la contraseña.
   * Formato: algoritmo:iteraciones:salt:hash
   */
  public static String hashPassword(String password) {
    char[] chars = password.toCharArray();
    byte[] salt = getSalt();

    PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
      byte[] hash = skf.generateSecret(spec).getEncoded();

      return ALGORITHM + ":" + ITERATIONS + ":" +
          Base64.getEncoder().encodeToString(salt) + ":" +
          Base64.getEncoder().encodeToString(hash);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException("Error al hashear la contraseña", e);
    }
  }

  /**
   * Verifica si la contraseña coincide con el hash almacenado.
   */
  public static boolean verifyPassword(String originalPassword, String storedHash) {
    String[] parts = storedHash.split(":");
    int iterations = Integer.parseInt(parts[1]);
    byte[] salt = Base64.getDecoder().decode(parts[2]);
    byte[] hash = Base64.getDecoder().decode(parts[3]);

    PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
      byte[] testHash = skf.generateSecret(spec).getEncoded();

      int diff = hash.length ^ testHash.length;
      for (int i = 0; i < hash.length && i < testHash.length; i++) {
        diff |= hash[i] ^ testHash[i];
      }
      return diff == 0;
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException("Error al verificar la contraseña", e);
    }
  }

  private static byte[] getSalt() {
    SecureRandom sr;
    try {
      sr = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      sr = new SecureRandom();
    }
    byte[] salt = new byte[SALT_LENGTH];
    sr.nextBytes(salt);
    return salt;
  }
}
