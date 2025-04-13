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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImplementation implements SurveyService {

  public SurveyRepository surveyRepository;

  public SurveyServiceImplementation(SurveyRepository surveyRepository) {
    super();
    this.surveyRepository = surveyRepository;
  }

  @Override
  public Survey saveSurvey(Survey survey) {
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

}
