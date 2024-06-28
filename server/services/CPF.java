package server.services;

import java.util.Random;

public class CPF {
    public CPF() {
    }

    public boolean isValid(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum1 += digit * (10 - i);
            sum2 += digit * (11 - i);
        }

        int checkDigit1 = 11 - (sum1 % 11);
        checkDigit1 = (checkDigit1 > 9) ? 0 : checkDigit1;
        sum2 += checkDigit1 * 2;

        int checkDigit2 = 11 - (sum2 % 11);
        checkDigit2 = (checkDigit2 > 9) ? 0 : checkDigit2;

        return cpf.equals(cpf.substring(0, 9) + checkDigit1 + checkDigit2);
    }

    public String generateValidCPF() {
        Random random = new Random();
        int[] cpf = new int[11];

        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += cpf[i] * (10 - i);
        }
        int checkDigit1 = 11 - (sum1 % 11);
        checkDigit1 = (checkDigit1 > 9) ? 0 : checkDigit1;
        cpf[9] = checkDigit1;

        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            sum2 += cpf[i] * (11 - i);
        }
        int checkDigit2 = 11 - (sum2 % 11);
        checkDigit2 = (checkDigit2 > 9) ? 0 : checkDigit2;
        cpf[10] = checkDigit2;

        StringBuilder formattedCPF = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            formattedCPF.append(cpf[i]);
            if (i == 2 || i == 5) {
                formattedCPF.append('.');
            } else if (i == 8) {
                formattedCPF.append('-');
            }
        }
        return formattedCPF.toString();
    }
}