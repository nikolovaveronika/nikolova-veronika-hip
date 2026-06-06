package com.example.podcast_api.Repositories;


import com.example.podcast_api.Entities.TranscriptSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranscriptSegmentRepository extends JpaRepository<TranscriptSegment, Long> {

    // All non-deleted segments for an episode, ordered by start time
    List<TranscriptSegment> findByEpisodeIdAndDeletedFalseOrderByStartSecAsc(Long episodeId);

    // THE OVERLAP CHECK
    // Two intervals overlap when: NOT (newEnd <= existStart OR newStart >= existEnd)
    // Which means: existStart < newEnd AND existEnd > newStart
    // excludeId is used when editing a segment so it doesn't conflict with itself
    @Query("""
        SELECT s FROM TranscriptSegment s
        WHERE s.episode.id = :episodeId
          AND s.deleted = false
          AND s.startSec < :endSec
          AND s.endSec > :startSec
          AND (:excludeId IS NULL OR s.id <> :excludeId)
    """)
    List<TranscriptSegment> findOverlapping(
            @Param("episodeId") Long episodeId,
            @Param("startSec")  int startSec,
            @Param("endSec")    int endSec,
            @Param("excludeId") Long excludeId
    );

    // Case-insensitive phrase search
    @Query("""
        SELECT s FROM TranscriptSegment s
        WHERE s.episode.id = :episodeId
          AND s.deleted = false
          AND LOWER(s.text) LIKE LOWER(CONCAT('%', :phrase, '%'))
        ORDER BY s.startSec ASC
    """)
    List<TranscriptSegment> searchByPhrase(
            @Param("episodeId") Long episodeId,
            @Param("phrase")    String phrase
    );

    // Get one segment that hasn't been soft-deleted
    Optional<TranscriptSegment> findByIdAndDeletedFalse(Long id);
}
