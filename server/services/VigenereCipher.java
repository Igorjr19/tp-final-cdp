package server.services;

public class VigenereCipher {

    public static String encrypt(String message, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                int shift = key.charAt(j % key.length()) - 'a';
                ch = (char) ((ch - base + shift) % 26 + base);
                j++;
            }
            result.append(ch);
        }
        return result.toString();
    }

    public static String decrypt(String message, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                int shift = key.charAt(j % key.length()) - 'a';
                ch = (char) ((ch - base - shift + 26) % 26 + base);
                j++;
            }
            result.append(ch);
        }
        return result.toString();
    }
}
