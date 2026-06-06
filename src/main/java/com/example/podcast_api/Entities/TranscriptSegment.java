package com.example.podcast_api.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class TranscriptSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = false)
    private Episode episode;

    @NotNull
    private Integer startSec;

    @NotNull
    private Integer endSec;

    @NotBlank
    private String speaker;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String text;

    // Soft delete — leaves a time gap in the transcript
    private boolean deleted = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public TranscriptSegment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Episode getEpisode() { return episode; }
    public void setEpisode(Episode episode) { this.episode = episode; }

    public Integer getStartSec() { return startSec; }
    public void setStartSec(Integer startSec) { this.startSec = startSec; }

    public Integer getEndSec() { return endSec; }
    public void setEndSec(Integer endSec) { this.endSec = endSec; }

    public String getSpeaker() { return speaker; }
    public void setSpeaker(String speaker) { this.speaker = speaker; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}