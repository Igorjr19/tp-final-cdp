package server.tasks;

import java.io.Serializable;

import compute.Task;
import server.services.CPF;
import server.services.VigenereCipher;

public class CipherTask implements Task<String>, Serializable {
    private String message;
    private String key;
    private String method;

    public CipherTask(String message, String key, String method) {
        this.message = message;
        this.key = key;
        this.method = method;
    }

    @Override
    public String execute() {
        if (method.equals("encrypt")) {
            return VigenereCipher.encrypt(message, key);
        } else if (method.equals("decrypt")) {
            return VigenereCipher.decrypt(message, key);
        } else {
            throw new IllegalArgumentException("Method not found");
        }
    }
}
