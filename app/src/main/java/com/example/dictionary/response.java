package com.example.dictionary;


import java.util.List;

public class response{

    String word;

    List<phonetics> phonetics = null;

    List<meanings> meanings = null;

    public List<com.example.dictionary.meanings> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<com.example.dictionary.meanings> meanings) {
        this.meanings = meanings;
    }

    public List<com.example.dictionary.phonetics> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<com.example.dictionary.phonetics> phonetics) {
        this.phonetics = phonetics;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
