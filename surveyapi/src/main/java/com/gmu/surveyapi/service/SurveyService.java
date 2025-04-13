/*
Author: Qasim Shahid
SWE 645 Assignment / HW 3

Service interface for managing Survey data.
Defines the contract for operations that interact with the underlying MySQL database.
This interface is intended to be implemented to provide the actual logic for data manipulation.
*/

package com.gmu.surveyapi.service;

import java.util.List;
import com.gmu.surveyapi.model.Survey;

public interface SurveyService {

  Survey saveSurvey(Survey survey);
  List<Survey> getAllSurveys();
  Survey getSurveyById(long id) throws Exception;
  Survey updateSurvey(Survey updatedSurvey, long id) throws Exception;
  void deleteSurvey(long id) throws Exception;

}
