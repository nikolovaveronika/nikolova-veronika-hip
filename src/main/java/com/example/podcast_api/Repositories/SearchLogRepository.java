package com.example.podcast_api.Repositories;

import com.example.podcast_api.Entities.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {

    // Returns [phrase, count] pairs sorted by most searched first
    @Query("""
        SELECT s.phrase, COUNT(s) as cnt
        FROM SearchLog s
        GROUP BY s.phrase
        ORDER BY cnt DESC
    """)
    List<Object[]> findTopPhrases();
}