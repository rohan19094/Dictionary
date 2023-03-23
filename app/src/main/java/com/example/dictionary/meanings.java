package com.example.dictionary;

import java.util.List;

public class meanings {

    String partOfSpeech;
    List<definitions> definitions = null;

    public List<com.example.dictionary.definitions> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<com.example.dictionary.definitions> definitions) {
        this.definitions = definitions;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
}
