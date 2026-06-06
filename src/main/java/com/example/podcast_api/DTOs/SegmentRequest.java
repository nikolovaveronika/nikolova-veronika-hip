package com.example.podcast_api.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SegmentRequest {

    @NotNull
    private Integer startSec;

    @NotNull
    private Integer endSec;

    @NotBlank
    private String speaker;

    @NotBlank
    private String text;

    public SegmentRequest() {}

    public Integer getStartSec() { return startSec; }
    public void setStartSec(Integer startSec) { this.startSec = startSec; }

    public Integer getEndSec() { return endSec; }
    public void setEndSec(Integer endSec) { this.endSec = endSec; }

    public String getSpeaker() { return speaker; }
    public void setSpeaker(String speaker) { this.speaker = speaker; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}