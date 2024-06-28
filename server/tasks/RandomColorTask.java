package server.tasks;

import java.io.Serializable;

import compute.Task;
import server.services.CPF;
import server.services.RandomColor;

public class RandomColorTask implements Task<String>, Serializable {
    private String color;
    private String method;

    public RandomColorTask(String method, String color) {
        this.method = method;
        this.color = color;
    }

    @Override
    public String execute() {
        RandomColor randomColorService = new RandomColor();
        if (method.equals("generate")) {
            randomColorService.generateRandomColorImage();
            return "Random color image generated - file saved at: './out/random-color.png'";
        } else {
            throw new IllegalArgumentException("Method not found");
        }
    }
}
