package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {

  public static List<Athlete> readAthletesFromFile(Path inputFile) throws IOException {
    ArrayList<Athlete> athletes = new ArrayList<>();
    BufferedReader reader = Files.newBufferedReader(inputFile);

    String readedLine;

    while ((readedLine = reader.readLine()) != null) {
      athletes.add(buildAthleteFromFile(readedLine));
    }

    Collections.sort(athletes);

    reader.close();
    return athletes;
  }

  public static Athlete buildAthleteFromFile(String line) {

    String[] parts = line.split(",");
    String athleteNumber = parts[0];
    String athleteName = parts[1];
    String countryCode = parts[2];
    String skiTimeResult = parts[3];
    String firstShootingResult = parts[4];
    String secondShootingResult = parts[5];
    String thirdShootingResult = parts[6];

    List<String> shootingResults = new ArrayList<>();
    shootingResults.add(firstShootingResult);
    shootingResults.add(secondShootingResult);
    shootingResults.add(thirdShootingResult);

    Athlete athlete =
        new Athlete(
            athleteNumber,
            athleteName,
            countryCode,
            skiTimeResult,
            firstShootingResult,
            secondShootingResult,
            thirdShootingResult);

    int skiTimeResultInSeconds = convertSkiTimeInSeconds(skiTimeResult);
    int penaltySeconds = calculateTimePenalties(shootingResults);

    skiTimeResultInSeconds += penaltySeconds;

    athlete.setTotalTimeInSeconds(skiTimeResultInSeconds);
    athlete.setPenaltySeconds(penaltySeconds);

    return athlete;
  }

  public static int convertSkiTimeInSeconds(String skiTimeResult) {
    String[] skiTimeResultParts = skiTimeResult.split(":");
    int minutes = Integer.parseInt(skiTimeResultParts[0]);
    int seconds = Integer.parseInt(skiTimeResultParts[1]);

    return (minutes * 60) + seconds;
  }

  public static int calculateTimePenalties(List<String> shootingResults) {
    int penaltySeconds = 0;

    for (String result : shootingResults) {
      for (int i = 0; i < result.length(); i++) {
        if (result.charAt(i) == 'o') {
          penaltySeconds += 10;
        }
      }
    }

    return penaltySeconds;
  }

  public static void writeFinalResultsToFile(List<Athlete> athletesList, Path outputFile)
      throws IOException {
    BufferedWriter writer = Files.newBufferedWriter(outputFile);

    addThePositionDescription(athletesList);

    for (Athlete athlet : athletesList) {
      writer.write(athlet + "\n");
    }

    writer.close();
  }

  public static void addThePositionDescription(List<Athlete> athletes) {
    int position = 0;
    for (Athlete athlete : athletes) {
      position++;
      Position podiumPosition = null;

      switch (position) {
        case 1:
          podiumPosition = Position.WINNER;
          break;
        case 2:
          podiumPosition = Position.RUNNER_UP;
          break;
        case 3:
          podiumPosition = Position.THIRD_PLACE;
          break;
        default:
          System.out.println("Not on the podium!");
      }
      athlete.setPosition(podiumPosition);
    }
  }
}
