package com.example.podcast_api.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    private String author;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL)
    @OrderBy("startSec ASC")
    private List<com.example.podcast_api.Entities.TranscriptSegment> segments = new ArrayList<>();

    public Episode() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<com.example.podcast_api.Entities.TranscriptSegment> getSegments() { return segments; }
    public void setSegments(List<com.example.podcast_api.Entities.TranscriptSegment> segments) { this.segments = segments; }
}