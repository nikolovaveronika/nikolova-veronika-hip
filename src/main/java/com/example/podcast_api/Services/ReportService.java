package com.example.podcast_api.Services;

import com.example.podcast_api.DTOs.PhraseCount;
import com.example.podcast_api.Repositories.SearchLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class ReportService {

    @Autowired
    private SearchLogRepository searchLogRepository;

    public List<PhraseCount> getMostSearchedPhrases() {
        List<Object[]> rows = searchLogRepository.findTopPhrases();
        List<PhraseCount> result = new ArrayList<>();
        for (Object[] row : rows) {
            result.add(new PhraseCount((String) row[0], (Long) row[1]));
        }
        return result;
    }
}