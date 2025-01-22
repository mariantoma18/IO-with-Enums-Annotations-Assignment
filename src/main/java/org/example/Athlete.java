package org.example;

/*
This class represent an athlete from biathlon competition;
In this class are stored infos about the athlete which will be processed later
 */
public class Athlete implements Comparable<Athlete> {

  private String athleteNumber;
  private String athleteName;
  private String countryCode;
  private String skiTimeResult;
  private String finalTimeResult;
  private String firstShootingResult;
  private String secondShootingResult;
  private String thirdShootingResult;
  private int totalTimeInSeconds;
  private int penaltySeconds;
  private Position position;

  public Athlete(
      String athleteNumber,
      String athleteName,
      String countryCode,
      String skiTimeResult,
      String firstShootingResult,
      String secondShootingResult,
      String thirdShootingResult) {
    this.athleteNumber = athleteNumber;
    this.athleteName = athleteName;
    this.countryCode = countryCode;
    this.skiTimeResult = skiTimeResult;
    this.firstShootingResult = firstShootingResult;
    this.secondShootingResult = secondShootingResult;
    this.thirdShootingResult = thirdShootingResult;
  }

  public String getAthleteNumber() {
    return athleteNumber;
  }

  public void setAthleteNumber(String athleteNumber) {
    this.athleteNumber = athleteNumber;
  }

  public String getAthleteName() {
    return athleteName;
  }

  public void setAthleteName(String athleteName) {
    this.athleteName = athleteName;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getSkiTimeResult() {
    return skiTimeResult;
  }

  public void setSkiTimeResult(String skiTimeResult) {
    this.skiTimeResult = skiTimeResult;
  }

  public String getFinalTimeResult() {
    return finalTimeResult;
  }

  public void setFinalTimeResult(String finalTimeResult) {
    this.finalTimeResult = finalTimeResult;
  }

  public String getFirstShootingResult() {
    return firstShootingResult;
  }

  public void setFirstShootingResult(String firstShootingResult) {
    this.firstShootingResult = firstShootingResult;
  }

  public String getSecondShootingResult() {
    return secondShootingResult;
  }

  public void setSecondShootingResult(String secondShootingResult) {
    this.secondShootingResult = secondShootingResult;
  }

  public String getThirdShootingResult() {
    return thirdShootingResult;
  }

  public void setThirdShootingResult(String thirdShootingResult) {
    this.thirdShootingResult = thirdShootingResult;
  }

  public int getTotalTimeInSeconds() {
    return totalTimeInSeconds;
  }

  public int getPenaltySeconds() {
    return penaltySeconds;
  }

  public Position getPosition() {
    return position;
  }

  public void setTotalTimeInSeconds(int totalTimeInSeconds) {
    this.totalTimeInSeconds = totalTimeInSeconds;
  }

  public void setPenaltySeconds(int penaltySeconds) {
    this.penaltySeconds = penaltySeconds;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  @Override
  public String toString() {
    int minutes = totalTimeInSeconds / 60;
    int seconds = totalTimeInSeconds % 60;
    return position.getDisplayPosition()
        + athleteName
        + " "
        + String.format("%d:%02d", minutes, seconds)
        + "("
        + skiTimeResult
        + " + "
        + penaltySeconds
        + ")";
  }

  @Override
  public int compareTo(Athlete o) {
    return Integer.compare(this.totalTimeInSeconds, o.totalTimeInSeconds);
  }
}
