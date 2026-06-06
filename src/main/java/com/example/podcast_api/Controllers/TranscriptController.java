package com.example.podcast_api.Controllers;

import com.example.podcast_api.DTOs.SegmentRequest;
import com.example.podcast_api.Entities.TranscriptSegment;
import com.example.podcast_api.Services.TranscriptSegmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SuppressWarnings("unused")
public class TranscriptController {

    @Autowired
    private TranscriptSegmentService segmentService;

    // POST /episodes/{id}/segments — add a segment (enforces no-overlap rule)
    @PostMapping("/episodes/{id}/segments")
    public ResponseEntity<TranscriptSegment> addSegment(
            @PathVariable Long id,
            @Valid @RequestBody SegmentRequest request) {
        return new ResponseEntity<>(segmentService.addSegment(id, request), HttpStatus.CREATED);
    }

    // GET /episodes/{id}/transcript — full ordered transcript
    @GetMapping("/episodes/{id}/transcript")
    public ResponseEntity<List<TranscriptSegment>> getTranscript(@PathVariable Long id) {
        return ResponseEntity.ok(segmentService.getTranscript(id));
    }

    // GET /episodes/{id}/search?phrase=... — search segments by phrase
    @GetMapping("/episodes/{id}/search")
    public ResponseEntity<List<TranscriptSegment>> search(
            @PathVariable Long id,
            @RequestParam String phrase) {
        return ResponseEntity.ok(segmentService.searchByPhrase(id, phrase));
    }

    // GET /segments/{id} — get one segment
    @GetMapping("/segments/{id}")
    public ResponseEntity<TranscriptSegment> getSegmentById(@PathVariable Long id) {
        return ResponseEntity.ok(segmentService.getSegmentById(id));
    }

    // PUT /segments/{id} — edit a segment (re-checks overlap, excludes self)
    @PutMapping("/segments/{id}")
    public ResponseEntity<TranscriptSegment> updateSegment(
            @PathVariable Long id,
            @Valid @RequestBody SegmentRequest request) {
        return ResponseEntity.ok(segmentService.updateSegment(id, request));
    }

    // DELETE /segments/{id} — soft delete (leaves a time gap in transcript)
    @DeleteMapping("/segments/{id}")
    public ResponseEntity<Void> deleteSegment(@PathVariable Long id) {
        segmentService.deleteSegment(id);
        return ResponseEntity.noContent().build();
    }
}