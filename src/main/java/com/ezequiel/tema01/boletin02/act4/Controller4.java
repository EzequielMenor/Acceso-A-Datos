package com.ezequiel.tema01.boletin02.act4;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Controller4 {
    //Ruta al arhcivo JSON
    private static final String RUTA = "/Users/ezequielmenor/2DAM/AccesoDatos/tema01/ficheros/usuarios.json";
    private final File f = new File(RUTA);
    //Gson, PrettyPrinting mas visual
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<User> users;
    private User usuarioActual = null;  //Usuario acutalmetne logueado

    public Controller4() throws Exception {
        loadUsers();
    }

    /**
     * Carga la lista de usuarios desde el archivo JSON
     * Si el archivo no existe, lo crea con un usuario "admin" por defecto
     */
    private void loadUsers() throws Exception {
        if (!f.exists()) {
            //Crea directorios y usuario por defecto
            f.getParentFile().mkdirs();
            users = new ArrayList<>();
            users.add(new User("admin", hashSHA1("S3cret@")));
            saveUsers();
            System.out.println("Archivo JSON creado. Usuario por defecto admin/S3cret@");
        } else {
            //Coger usuarios del archivo JSON
            try(FileReader reader = new FileReader(f)){
                //Dice el tipo de lista que tiene el JSON
                Type listType = new TypeToken<ArrayList<User>>() {}.getType();
                users = gson.fromJson(reader, listType);
                //Lista vacia para que no falle
                if (users == null) {
                    users = new ArrayList<>();
                }
            }
        }
    }


    private void saveUsers() throws Exception {
        try (FileWriter writer = new FileWriter(f)){
            gson.toJson(users, writer);
        }
    }

    private Optional<User> findUser (String nombre) {
        return users.stream() //stream pasa la lista a flujo de datos
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public boolean validarAcceso(String nombre, String passwd) throws Exception {
        //La busqueda puede no encontrar un resultado
        Optional<User> userOpt = findUser(nombre);

        //Si optional contiene user
        if (userOpt.isPresent()){
            User user = userOpt.get();
            //Comprueba contraseña hasheada coincide
            if (user.getHashedPasswd().equals(hashSHA1(passwd))){
                this.usuarioActual = user; //Cambiar el usuario logeado
                return true;
            }
        }
        return false;
    }


    public boolean resgistrarUsuario(String nomre, String passwd) throws Exception{
        if (findUser(nomre).isPresent()){
            System.out.println("El usuario ya existe");
            return false;
        }

        if(!validarContrasena(passwd)){
            System.out.println("La contraseña no es valida");
            return false;
        }

        String hashedPasswd = hashSHA1(passwd);
        User newUser = new User(nomre, hashedPasswd);

        this.users.add(newUser);
        saveUsers();
        return true;
    }


    public boolean cambiarPasswd(String actual, String nueva) throws Exception {
        if (usuarioActual == null){
            throw new IllegalStateException("No hay ningun usuario logeado");
        }

        //verifica que la contraseña actual sea correcta
        if (!usuarioActual.getHashedPasswd().equals(hashSHA1(actual))){
            return false;
        }

        if (!validarContrasena(nueva)){
            return false;
        }

        // Actualiza la contraseña del objeto. Guarda la lista completa en JSON
        usuarioActual.setHashedPasswd(hashSHA1(nueva));
        saveUsers();
        return true;
    }


    /**
     * Convierte una contraseña de texto plano a un hash SHA-1 irreversible de 40 caracteres.
     *
     */
    public static String hashSHA1(String input) throws NoSuchAlgorithmException {
        //Usa el algoritmo SHA-1 para crear la contraseña y obtener su versión cifrada en binario
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = md.digest(input.getBytes());
        //Toma los resultados binarios y los convierte, uno por uno, a una cadena de texto hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        //Devuelve la cadena final(hash) de 40 carct, para guardarlo en el JSON
        return sb.toString();
    }

    /**
     * Valida si la contraseña cumple con los requisitos de seguridad
     */

    public static boolean validarContrasena(String pass) {
        return pass.length() >= 8 &&
                pass.matches(".*[A-Z].*") &&
                pass.matches(".*[a-z].*") &&
                pass.matches(".*[0-9].*") &&
                pass.matches(".*[^a-zA-Z0-9].*");
    }

    /**
     * Devuelve usuario actual
     */
    public User getUsuarioActual() {
        return usuarioActual;
    }
}
