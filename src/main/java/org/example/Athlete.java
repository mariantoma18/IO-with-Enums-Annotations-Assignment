package org.example;

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
