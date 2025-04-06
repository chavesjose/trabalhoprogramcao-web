package com.chaves.webap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtilService {

    // Método para gerar o hash MD5 da senha
    public static String toMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            // Converte o hash para uma string hexadecimal em minúsculas
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));  // %02x para letras minúsculas
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar o hash MD5", e);
        }
    }

    // Método para verificar se a senha fornecida corresponde ao hash armazenado
    public static boolean checkPassword(String password, String storedPassword) {
        return storedPassword.equals(toMD5(password));  // Verifica se o hash MD5 corresponde
    }
}
