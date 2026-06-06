package com.example.podcast_api.DTOs;

public class PhraseCount {

    private String phrase;
    private Long count;

    public PhraseCount() {}

    public PhraseCount(String phrase, Long count) {
        this.phrase = phrase;
        this.count = count;
    }

    public String getPhrase() { return phrase; }
    public void setPhrase(String phrase) { this.phrase = phrase; }

    public Long getCount() { return count; }
    public void setCount(Long count) { this.count = count; }
}