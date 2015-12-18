package com.example.kaeuc.finalproject;

/**
 * Created by kaeuc on 12/17/2015.
 */
public class WordClass {

    private String word;
    private String definition;
    private String sentence;

    public WordClass(String word, String definition, String sentence) {
        this.word = word;
        this.definition = definition;
        this.sentence = sentence;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }


}
