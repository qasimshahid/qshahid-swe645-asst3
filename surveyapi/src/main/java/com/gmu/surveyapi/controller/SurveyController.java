/*
Author: Qasim Shahid
SWE 645 Assignment / HW 3.

This Java class is a REST API controller that handles CRUD operations on a MySQL database using the SurveyService.
It leverages REST annotations to map HTTP requests and extract parameters, enabling database interactions such as retrieval, updates, and deletions.
*/

package com.gmu.surveyapi.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.*;
import com.gmu.surveyapi.model.Survey;
import com.gmu.surveyapi.service.SurveyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "*")
public class SurveyController {

  private final String version = "1.0.0.0"; // Change when you want to show that pipeline successfully rebuilds the application.
  private final SurveyService surveyService;
  private static final List<String> VALID_LIKED_MOST = List.of("students", "location", "campus", "atmosphere", "dorm rooms", "sports");
  private static final List<String> VALID_INTEREST_SOURCE = List.of("friends", "television", "Internet", "other");
  private static final List<String> VALID_RECOMMEND_LIKELIHOOD = List.of("Very Likely", "Likely", "Unlikely");


  public SurveyController(SurveyService surveyService) {
    super();
    this.surveyService = surveyService;
  }

  @PostMapping
  public ResponseEntity<Survey> saveSurvey(@Valid @RequestBody Survey survey) {
    validateSurveyInput(survey);
    return new ResponseEntity<>(surveyService.saveSurvey(survey), HttpStatus.CREATED);
  }

  @GetMapping
  public List<Survey> getAllSurveys() {
    return surveyService.getAllSurveys();
  }

  @GetMapping("{id}")
  public ResponseEntity<Survey> getSurveyById(@PathVariable("id") long surveyId) throws Exception {
    return new ResponseEntity<>(surveyService.getSurveyById(surveyId), HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<Survey> updateSurvey(@Valid @PathVariable("id") long id, @RequestBody Survey survey) throws Exception {
    validateSurveyInput(survey);
    return new ResponseEntity<>(surveyService.updateSurvey(survey, id), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteSurvey(@PathVariable("id") long id) throws Exception {
    surveyService.deleteSurvey(id);
    return new ResponseEntity<>("Survey deleted", HttpStatus.OK);
  }

  // New method to return the version info as HTML
  @GetMapping("/version")
  public ResponseEntity<String> getVersionInfo() {
    String versionHtml = "<html>" +
            "<head><title>Survey API Version</title></head>" +
            "<body>" +
            "<h1>Survey API Version</h1>" +
            "<p>Current Version: " + this.version + "</p>" +  // Hardcoded version
            "<p>By Qasim Shahid for SWE 645</p>" +
            "</body>" +
            "</html>";
    return new ResponseEntity<>(versionHtml, HttpStatus.OK);
  }

  private void validateSurveyInput(Survey survey) {
    if (survey.getLikedMost() != null && !VALID_LIKED_MOST.contains(survey.getLikedMost().toLowerCase())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for likedMost");
    }

    if (survey.getInterestSource() != null && !VALID_INTEREST_SOURCE.contains(survey.getInterestSource().toLowerCase())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for interestSource");
    }

    if (survey.getRecommendLikelihood() != null && !VALID_RECOMMEND_LIKELIHOOD.contains(survey.getRecommendLikelihood())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for recommendLikelihood");
    }
  }

}
