package com.ezequiel.tema01.boletin02.act3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class Controller3 {
    private static final String RUTA = "/Users/ezequielmenor/2DAM/AccesoDatos/tema01/ficheros/usuario.properties";
    private static final String CLAVE = "password";
    private final Properties prop = new Properties();
    private final File f = new File(RUTA);


    public Controller3() throws Exception{
        if(!f.exists()){
            f.getParentFile().mkdirs();
            prop.setProperty(CLAVE, hashSHA1("S3cret@"));
            prop.store(new FileOutputStream(f), "Archivo de usuario");
        }else {
            prop.load(new FileInputStream(f));
        }
    }

    public boolean validarAcceso (String pass) throws NoSuchAlgorithmException {
        return prop.getProperty(CLAVE).equals(hashSHA1(pass));
    }

    public boolean cambiarContrasena(String actual, String nueva) throws Exception {
        if(!prop.getProperty(CLAVE).equals(hashSHA1(actual))) return false;
        if(!validarContrasena(nueva)) return false;

        prop.setProperty(CLAVE, hashSHA1(nueva));
        prop.store(new FileOutputStream(f), "Archivo de usuario");
        return true;
    }

    public static String hashSHA1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean validarContrasena(String pass) {
        return pass.length() >= 8 &&
                pass.matches(".*[A-Z].*") &&
                pass.matches(".*[a-z].*") &&
                pass.matches(".*[0-9].*") &&
                pass.matches(".*[^a-zA-Z0-9].*");
    }
}
