package com.example.podcast_api.Controllers;

import com.example.podcast_api.DTOs.PhraseCount;
import com.example.podcast_api.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@SuppressWarnings("unused")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // GET /reports/most-searched-phrases — ranked list of searched phrases
    @GetMapping("/most-searched-phrases")
    public ResponseEntity<List<PhraseCount>> getMostSearchedPhrases() {
        return ResponseEntity.ok(reportService.getMostSearchedPhrases());
    }
}