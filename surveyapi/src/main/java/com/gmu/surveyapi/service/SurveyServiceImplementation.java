/*
Author: Qasim Shahid
SWE 645 Assignment / HW 3

Implementation of the SurveyService interface.
This class contains the business logic used by the REST controller to perform
CRUD operations on the database. It handles tasks such as constructing Survey objects, persisting them using the SurveyRepository, and checking for their existence.
*/

package com.gmu.surveyapi.service;

import com.gmu.surveyapi.model.Survey;
import com.gmu.surveyapi.repository.SurveyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImplementation implements SurveyService {

  public SurveyRepository surveyRepository;
  private static final List<String> VALID_LIKED_MOST = List.of("students", "location", "campus", "atmosphere", "dorm rooms", "sports");
  private static final List<String> VALID_INTEREST_SOURCE = List.of("friends", "television", "internet", "other");
  private static final List<String> VALID_RECOMMEND_LIKELIHOOD = List.of("very likely", "likely", "unlikely");


  public SurveyServiceImplementation(SurveyRepository surveyRepository) {
    super();
    this.surveyRepository = surveyRepository;
  }

  @Override
  public Survey saveSurvey(Survey survey) {
    validateSurveyInput(survey);
    return this.surveyRepository.save(survey);
  }

  @Override
  public List<Survey> getAllSurveys() {
    return this.surveyRepository.findAll();
  }

  @Override
  public Survey getSurveyById(long id) throws Exception {
    Optional<Survey> survey = this.surveyRepository.findById(id);
    if (survey.isPresent()) {
      return survey.get();
    }
    else {
      throw new Exception("Survey not found by ID");
    }
  }

  @Override
  public Survey updateSurvey(Survey updatedSurvey, long id) throws Exception {
    validateSurveyInput(updatedSurvey);
    Optional<Survey> existingSurveyGet = surveyRepository.findById(id);
    if (existingSurveyGet.isPresent()) {
      Survey existingSurvey = existingSurveyGet.get();

      existingSurvey.setFirstName(updatedSurvey.getFirstName());
      existingSurvey.setLastName(updatedSurvey.getLastName());
      existingSurvey.setStreetAddress(updatedSurvey.getStreetAddress());
      existingSurvey.setCity(updatedSurvey.getCity());
      existingSurvey.setState(updatedSurvey.getState());
      existingSurvey.setZip(updatedSurvey.getZip());
      existingSurvey.setTelephone(updatedSurvey.getTelephone());
      existingSurvey.setEmail(updatedSurvey.getEmail());
      existingSurvey.setDateOfSurvey(updatedSurvey.getDateOfSurvey());
      existingSurvey.setLikedMost(updatedSurvey.getLikedMost());
      existingSurvey.setInterestSource(updatedSurvey.getInterestSource());
      existingSurvey.setRecommendLikelihood(updatedSurvey.getRecommendLikelihood());
      existingSurvey.setAdditionalComments(updatedSurvey.getAdditionalComments());

      this.surveyRepository.save(existingSurvey);
      return existingSurvey;
    }
    else {
      throw new Exception("Survey not found by ID");
    }
  }

  @Override
  public void deleteSurvey(long id) throws Exception {
    Optional<Survey> existingSurveyGet = surveyRepository.findById(id);
    if (existingSurveyGet.isPresent()) {
      this.surveyRepository.deleteById(id);
    }
    else {
      throw new Exception("Survey not found by ID");
    }
  }

  private void validateSurveyInput(Survey survey) {
    survey.setLikedMost(survey.getLikedMost().toLowerCase());
    survey.setInterestSource(survey.getInterestSource().toLowerCase());
    survey.setRecommendLikelihood(survey.getRecommendLikelihood().toLowerCase());
    if (survey.getLikedMost() != null && !VALID_LIKED_MOST.contains(survey.getLikedMost().toLowerCase().trim())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for likedMost. Options are " + VALID_LIKED_MOST.toString() + ". You passed " + survey.getLikedMost());
    }

    if (survey.getInterestSource() != null && !VALID_INTEREST_SOURCE.contains(survey.getInterestSource().toLowerCase().trim())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for interestSource. Option are " + VALID_INTEREST_SOURCE.toString() + ". You passed " + survey.getInterestSource());
    }

    if (survey.getRecommendLikelihood() != null && !VALID_RECOMMEND_LIKELIHOOD.contains(survey.getRecommendLikelihood().trim())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for recommendLikelihood. Options are " + VALID_RECOMMEND_LIKELIHOOD.toString() + ". You passed " + survey.getRecommendLikelihood());
    }
  }

}
