package server.services;

public class VigenereCipher {
    private	String message;
    private String key;
    private String encrypted;

    public VigenereCipher(String message, String key) {
        this.message = message;
        this.key = key;
    }

    public String encrypt() {
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

    public String decrypt() {
        
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        for (int i = 0, j = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
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
