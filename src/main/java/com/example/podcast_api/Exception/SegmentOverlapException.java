package com.example.podcast_api.Exception;

public class SegmentOverlapException extends RuntimeException {
    public SegmentOverlapException(String message) {
        super(message);
    }
}