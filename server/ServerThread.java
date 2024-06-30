package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import compute.Compute;
import server.tasks.CipherTask;
import server.tasks.CpfTask;
import server.tasks.LoremTask;
import server.tasks.RandomColorTask;

public class ServerThread extends Thread {
    private Socket socket;
    private String[] args;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.args = null;
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            this.args = inputStream.readUTF().split(" ");
            processArgs(args);
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void processArgs(String[] args) throws NotBoundException, IOException {
        if (args.length < 2) {
            System.out.println("Invalid arguments");
            for (String arg : args) {
                System.out.println(arg);
            }
            throw new IllegalArgumentException(
                    "java -cp . client.Client <service_name> <service_method> <service_parameters_1> ... <service_parameters_1>");
        }
        String serviceName = args[0];
        String serviceMethod = args[1];
        String[] serviceParameters = new String[args.length - 2];
        for (int i = 0; i < serviceParameters.length; i++) {
            serviceParameters[i] = args[i + 2];
        }
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        Compute compute = (Compute) registry.lookup("Compute"); 
        System.out.println("Service: " + serviceName);
        System.out.println("Method: " + serviceMethod);
        for (String param : serviceParameters) {
            System.out.println("Parameter: " + param);
        }
        switch (serviceName) {
            case "cpf":
                if (serviceMethod.equals("generate")) {
                    CpfTask task = new CpfTask("generate", "");
                    compute.executeTask(task);
                } else if (serviceMethod.equals("validate")) {
                    CpfTask task = new CpfTask("validate", serviceParameters[0]);
                    String isValid = compute.executeTask(task);
                    outputStream.writeUTF(isValid);
                } else {
                    throw new IllegalArgumentException("Method not found");
                }
                break;
            case "cipher":
                if (serviceMethod.equals("encrypt")) {
                    CipherTask task = new CipherTask("encrypt", serviceParameters[0], serviceParameters[1]);
                    String encrypted = compute.executeTask(task);
                    outputStream.writeUTF(encrypted);
                } else if (serviceMethod.equals("decrypt")) {
                    CipherTask task = new CipherTask("decrypt", serviceParameters[0], serviceParameters[1]);
                    String decrypted = compute.executeTask(task);
                    outputStream.writeUTF(decrypted);
                } else {
                    throw new IllegalArgumentException("Method not found");
                }
                break;
            case "lorem":
                if (serviceMethod.equals("generate")) {
                    if (serviceParameters.length == 0) {
                        LoremTask task = new LoremTask("generate");
                        compute.executeTask(task);
                    } else if (serviceParameters.length == 4) {
                        LoremTask task = new LoremTask("generate", serviceParameters[0], serviceParameters[1], serviceParameters[2], serviceParameters[3]);
                        compute.executeTask(task);
                    } else {
                        throw new IllegalArgumentException("Invalid number of parameters");
                    }
                } else {
                    throw new IllegalArgumentException("Method not found");
                }
                outputStream.writeUTF("Lorem ipsum generated - file saved at '/out/lorem-ipsum.txt'");
                break;
            case "randomColor":
                RandomColorTask task = new RandomColorTask(serviceMethod, Integer.parseInt(serviceParameters[0]));
                String result = compute.executeTask(task);
                outputStream.writeUTF(result);
                break;
            default:
                throw new IllegalArgumentException("Service not found");
        }
    }

}