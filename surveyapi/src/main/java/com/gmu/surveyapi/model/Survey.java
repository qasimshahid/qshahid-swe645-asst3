/*
Author: Qasim Shahid
SWE 645 Assignment / HW 3

This Java class defines the structure of a Survey and corresponds to the frontend form.
It is a POJO (Plain Old Java Object) with getters and setters for all properties.
Hibernate/JPA annotations are used to map fields to MySQL database columns, including the primary key.
*/

package com.gmu.surveyapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
public class Survey {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "First name is required")
  @Column(nullable = false)
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Column(nullable = false)
  private String lastName;

  @NotBlank(message = "Street address is required")
  @Column(nullable = false)
  private String streetAddress;

  @NotBlank(message = "City is required")
  @Column(nullable = false)
  private String city;

  @NotBlank(message = "State is required")
  @Column(nullable = false)
  private String state;

  @NotBlank(message = "ZIP code is required")
  @Column(nullable = false)
  private String zip;

  @NotBlank(message = "Telephone number is required")
  @Column(nullable = false)
  private String telephone;

  @NotBlank(message = "Email is required")
  @Email(message = "Email must be valid")
  @Column(nullable = false)
  private String email;

  @NotNull(message = "Date of survey is required")
  @Column(nullable = false)
  private Date dateOfSurvey;

  @Column
  private String likedMost;

  @Column
  private String interestSource;

  @Column
  private String recommendLikelihood;

  @Column(length=1500)
  private String additionalComments;

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDateOfSurvey() {
    return dateOfSurvey;
  }

  public void setDateOfSurvey(Date dateOfSurvey) {
    this.dateOfSurvey = dateOfSurvey;
  }

  public String getLikedMost() {
    return likedMost;
  }

  public void setLikedMost(String likedMost) {
    this.likedMost = likedMost;
  }

  public String getInterestSource() {
    return interestSource;
  }

  public void setInterestSource(String interestSource) {
    this.interestSource = interestSource;
  }

  public String getRecommendLikelihood() {
    return recommendLikelihood;
  }

  public void setRecommendLikelihood(String recommendLikelihood) {
    this.recommendLikelihood = recommendLikelihood;
  }

  public String getAdditionalComments() {
    return additionalComments;
  }

  public void setAdditionalComments(String additionalComments) {
    this.additionalComments = additionalComments;
  }

}
