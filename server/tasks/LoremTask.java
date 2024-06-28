package server.tasks;

import java.io.Serializable;

import compute.Task;
import server.services.CPF;
import server.services.LoremIpsum;

public class LoremTask implements Task<String>, Serializable {
    private int numberOfWords;
    private int numberOfParagraphs;
    private int numberOfSentencesPerParagraph;
    private boolean withLoremIpsum;
    private String method;

    public LoremTask(String method, String numberOfWords, String numberOfParagraphs, String numberOfSentencesPerParagraph, String withLoremIpsum) {
        this.method = method;
        this.numberOfWords = Integer.parseInt(numberOfWords);
        this.numberOfParagraphs = Integer.parseInt(numberOfParagraphs);
        this.numberOfSentencesPerParagraph = Integer.parseInt(numberOfSentencesPerParagraph);
        this.withLoremIpsum = Boolean.parseBoolean(withLoremIpsum);
    }

    public LoremTask(String method) {
        this.method = method;
        this.numberOfWords = 0;
        this.numberOfParagraphs = 0;
        this.numberOfSentencesPerParagraph = 0;
        this.withLoremIpsum = false;
    }

    @Override
    public String execute() {
        LoremIpsum loremIpsumService = null;
        if (this.numberOfWords == 0 && this.numberOfParagraphs == 0 && this.numberOfSentencesPerParagraph == 0) {
            loremIpsumService = new LoremIpsum();
        } else {
            loremIpsumService = new LoremIpsum(this.numberOfWords, this.numberOfParagraphs, this.numberOfSentencesPerParagraph, this.withLoremIpsum);
        }
        if (method.equals("generate")) {
            return loremIpsumService.generate();
        } else {
            throw new IllegalArgumentException("Method not found");
        }
    }
}
