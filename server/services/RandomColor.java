package server.services;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RandomColor {
    private String[] color;

    public RandomColor(int n) {
        this.color = generateRandomColor(n);
    }

    public String[] generateRandomColor(int n) {
        String[] colors = new String[n];
        for (int i = 0; i < n; i++) {
            String letters = "0123456789ABCDEF";
            String color = "#";
            for (int j = 0; j < 6; j++) {
                color += letters.charAt((int) Math.floor(Math.random() * 16));
            }
            colors[i] = color;
        }
        return colors;
    }

    public BufferedImage generateImage() {
        int width = color.length * 100;
        int height = 200;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        for (int i = 0; i < color.length; i++) {
            graphics.setColor(Color.decode(color[i]));
            graphics.fillRect(i * (width / color.length), 0, width / color.length, height);
        }
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
