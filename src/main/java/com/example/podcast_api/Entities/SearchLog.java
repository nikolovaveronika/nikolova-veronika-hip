package com.example.podcast_api.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SearchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long episodeId;

    private String phrase;

    private LocalDateTime searchedAt = LocalDateTime.now();

    public SearchLog() {}

    public SearchLog(Long episodeId, String phrase) {
        this.episodeId = episodeId;
        this.phrase = phrase;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEpisodeId() { return episodeId; }
    public void setEpisodeId(Long episodeId) { this.episodeId = episodeId; }

    public String getPhrase() { return phrase; }
    public void setPhrase(String phrase) { this.phrase = phrase; }

    public LocalDateTime getSearchedAt() { return searchedAt; }
    public void setSearchedAt(LocalDateTime searchedAt) { this.searchedAt = searchedAt; }
}