package server.services;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RandomColor {
    private String color;
    public RandomColor() {
        this.color = generateRandomColor();
    }

    public String generateRandomColor() {
        String letters = "0123456789ABCDEF";
        String color = "#";
        for (int i = 0; i < 6; i++) {
            color += letters.charAt((int) Math.floor(Math.random() * 16));
        }
        return color;
    }

    public BufferedImage generateImage() {
        int width = 500;
        int height = 500;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Color randomColor = Color.decode(color);
        graphics.setColor(randomColor);
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
        return image;
    }

    public void saveImage(BufferedImage image, String path) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateRandomColorImage() {
        BufferedImage image = generateImage();
        saveImage(image, "./out/random-color.png");
    }
}
