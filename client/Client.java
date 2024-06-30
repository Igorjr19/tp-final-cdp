package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 1234;
        try (
            Socket socket = new Socket(serverHost, serverPort);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            String argsString = String.join(" ", args);
            System.out.println("Client Request: " + argsString);
            outputStream.writeUTF(argsString);
            String response = inputStream.readUTF();
            System.out.println("Server Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}