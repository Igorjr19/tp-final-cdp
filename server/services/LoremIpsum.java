package server.services;

import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoremIpsum {
    private int numberOfWords;
    private int numberOfParagraphs;
    private int numberOfSentencesPerParagraph;
    private boolean withLoremIpsum;
    private String generatedText;

    private final String[] WORDS = {
            "lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
            "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore",
            "magna", "aliqua", "ut", "enim", "ad", "minim", "veniam", "quis", "nostrud",
            "exercitation", "ullamco", "laboris", "nisi", "ut", "aliquip", "ex", "ea",
            "commodo", "consequat", "duis", "aute", "irure", "dolor", "in", "reprehenderit",
            "in", "voluptate", "velit", "esse", "cillum", "dolore", "eu", "fugiat", "nulla",
            "pariatur", "excepteur", "sint", "occaecat", "cupidatat", "non", "proident",
            "sunt", "in", "culpa", "qui", "officia", "deserunt", "mollit", "anim", "id",
            "est", "laborum"
    };

    public LoremIpsum() {
        this.numberOfWords = 100;
        this.numberOfParagraphs = 5;
        this.numberOfSentencesPerParagraph = 5;
        this.withLoremIpsum = true;
    }

    public LoremIpsum(int numberOfWords, int numberOfParagraphs, int numberOfSentencesPerParagraph,
            boolean withLoremIpsum) {
        this.numberOfWords = numberOfWords;
        this.numberOfParagraphs = numberOfParagraphs;
        this.numberOfSentencesPerParagraph = numberOfSentencesPerParagraph;
        this.withLoremIpsum = withLoremIpsum;
    }

    public String generate() {
        StringBuilder loremIpsumText = new StringBuilder();
        Random random = new Random();
        boolean capitalize = false;

        for (int p = 0; p < numberOfParagraphs; p++) {
            if (p > 0) {
                loremIpsumText.append("\n");
                capitalize = true;
            }
            for (int s = 0; s < numberOfSentencesPerParagraph; s++) {
                if (s == 0 && withLoremIpsum && p == 0) {
                    loremIpsumText.append("Lorem ipsum dolor sit amet, ");
                } else if (s > 0) {
                    loremIpsumText.append(" ");
                }
                for (int w = 0; w < numberOfWords; w++) {
                    if (w > 0) {
                        loremIpsumText.append(" ");
                    }
                    if (capitalize) {
                        loremIpsumText.append(WORDS[random.nextInt(WORDS.length)].substring(0, 1).toUpperCase());
                        loremIpsumText.append(WORDS[random.nextInt(WORDS.length)].substring(1));
                        capitalize = false;
                    } else {
                        loremIpsumText.append(WORDS[random.nextInt(WORDS.length)]);
                    }
                }
                loremIpsumText.append(".");

            }
        }
        generatedText = loremIpsumText.toString();
        return generatedText;
    }

    public void writeToFile(String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(generatedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
