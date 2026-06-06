package com.example.podcast_api.Services;


import com.example.podcast_api.DTOs.SegmentRequest;
import com.example.podcast_api.Entities.Episode;
import com.example.podcast_api.Entities.SearchLog;
import com.example.podcast_api.Entities.TranscriptSegment;
import com.example.podcast_api.Exception.ResourceNotFoundException;
import com.example.podcast_api.Exception.SegmentOverlapException;
import com.example.podcast_api.Repositories.SearchLogRepository;
import com.example.podcast_api.Repositories.TranscriptSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class TranscriptSegmentService {

    @Autowired
    private TranscriptSegmentRepository segmentRepository;

    @Autowired
    private SearchLogRepository searchLogRepository;

    @Autowired
    private EpisodeService episodeService;

    public TranscriptSegment addSegment(Long episodeId, SegmentRequest request) {
        Episode episode = episodeService.getEpisodeById(episodeId);

        if (request.getEndSec() <= request.getStartSec()) {
            throw new IllegalArgumentException("endSec must be greater than startSec");
        }

        // BUSINESS INVARIANT: no overlapping segments
        checkOverlap(episodeId, request.getStartSec(), request.getEndSec(), null);

        TranscriptSegment segment = new TranscriptSegment();
        segment.setEpisode(episode);
        segment.setStartSec(request.getStartSec());
        segment.setEndSec(request.getEndSec());
        segment.setSpeaker(request.getSpeaker());
        segment.setText(request.getText());

        return segmentRepository.save(segment);
    }

    public List<TranscriptSegment> getTranscript(Long episodeId) {
        episodeService.getEpisodeById(episodeId); // 404 if episode missing
        return segmentRepository.findByEpisodeIdAndDeletedFalseOrderByStartSecAsc(episodeId);
    }

    public List<TranscriptSegment> searchByPhrase(Long episodeId, String phrase) {
        episodeService.getEpisodeById(episodeId);
        // Log every search so we can report on it later
        searchLogRepository.save(new SearchLog(episodeId, phrase.toLowerCase().trim()));
        return segmentRepository.searchByPhrase(episodeId, phrase.trim());
    }

    public TranscriptSegment getSegmentById(Long id) {
        return segmentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Segment not found with id: " + id));
    }

    public TranscriptSegment updateSegment(Long id, SegmentRequest request) {
        TranscriptSegment segment = getSegmentById(id);

        if (request.getEndSec() <= request.getStartSec()) {
            throw new IllegalArgumentException("endSec must be greater than startSec");
        }

        // Overlap check — exclude self so a segment can be edited in place
        checkOverlap(segment.getEpisode().getId(), request.getStartSec(), request.getEndSec(), id);

        segment.setStartSec(request.getStartSec());
        segment.setEndSec(request.getEndSec());
        segment.setSpeaker(request.getSpeaker());
        segment.setText(request.getText());
        segment.setUpdatedAt(LocalDateTime.now());

        return segmentRepository.save(segment);
    }

    public void deleteSegment(Long id) {
        TranscriptSegment segment = getSegmentById(id);
        segment.setDeleted(true); // soft delete — leaves a gap in the transcript
        segmentRepository.save(segment);
    }

    // ── Overlap helper ────────────────────────────────────────────────────────
    // Classic interval overlap: NOT (newEnd <= existStart OR newStart >= existEnd)
    private void checkOverlap(Long episodeId, int startSec, int endSec, Long excludeId) {
        List<TranscriptSegment> conflicts =
                segmentRepository.findOverlapping(episodeId, startSec, endSec, excludeId);

        if (!conflicts.isEmpty()) {
            TranscriptSegment c = conflicts.get(0);
            throw new SegmentOverlapException(
                    String.format("Segment [%d, %d) overlaps with existing segment [%d, %d) (id=%d)",
                            startSec, endSec, c.getStartSec(), c.getEndSec(), c.getId())
            );
        }
    }
}