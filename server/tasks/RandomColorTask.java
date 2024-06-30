package server.tasks;

import java.io.Serializable;

import compute.Task;
import server.services.CPF;
import server.services.RandomColor;

public class RandomColorTask implements Task<String>, Serializable {
    private String method;
    private int numberOfColors;

    public RandomColorTask(String method, int numberOfColors) {
        this.method = method;
        this.numberOfColors = numberOfColors;
    }

    @Override
    public String execute() {
        RandomColor randomColorService = new RandomColor(numberOfColors);
        if (method.equals("generate")) {
            randomColorService.generateRandomColorImage();
            return "Random color image generated - file saved at: './out/random-color.png'";
        } else {
            throw new IllegalArgumentException("Method not found");
        }
    }
}
