package server.tasks;

import java.io.Serializable;

import compute.Task;
import server.services.CPF;

public class CpfTask implements Task<String>, Serializable {
    private String cpf;
    private String method;

    public CpfTask(String method, String cpf) {
        this.method = method;
        this.cpf = cpf;
    }

    @Override
    public String execute() {
        CPF cpfService = new CPF();
        if (method.equals("generate")) {
            return cpfService.generateValidCPF();
        } else if (method.equals("validate")) {
            return cpfService.isValid(cpf) ? "Valid" : "Invalid";
        } else {
            throw new IllegalArgumentException("Method not found");
        }
    }
}
