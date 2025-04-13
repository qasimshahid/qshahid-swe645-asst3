/*
Author: Qasim Shahid
SWE 645 Assignment / HW 3

Repository interface for Survey entities. Extends JpaRepository to provide built-in CRUD operations using the Survey ID (Long) as the primary key.
No additional implementation is needed to interact with the database.
*/

package com.gmu.surveyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gmu.surveyapi.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
