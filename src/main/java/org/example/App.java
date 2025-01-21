package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
  public static void main(String[] args) {
    Path inputFile = Paths.get("Biathlon-results.csv");
    Path outputFile = Paths.get("Biathlon-final-results.txt");

    String athleteNumber;
    String athleteName;
    String countryCode;
    String skiTimeResult;
    String firstShootingResult;
    String secondShootingResult;
    String thirdShootingResult;

    List<String> lines = new ArrayList<>();
    List<Athlete> athletes = new ArrayList<>();

    try {

      BufferedReader reader = Files.newBufferedReader(inputFile);
      BufferedWriter writer = Files.newBufferedWriter(outputFile);
      String readedLine;

      while ((readedLine = reader.readLine()) != null) {
        lines.add(readedLine);
      }

      for (String line : lines) {
        String[] parts = line.split(",");
        athleteNumber = parts[0];
        athleteName = parts[1];
        countryCode = parts[2];
        skiTimeResult = parts[3];
        firstShootingResult = parts[4];
        secondShootingResult = parts[5];
        thirdShootingResult = parts[6];

        Athlete athlete =
            new Athlete(
                athleteNumber,
                athleteName,
                countryCode,
                skiTimeResult,
                firstShootingResult,
                secondShootingResult,
                thirdShootingResult);

        String[] skiTimeResultParts = skiTimeResult.split(":");
        int minutes = Integer.parseInt(skiTimeResultParts[0]);
        int seconds = Integer.parseInt(skiTimeResultParts[1]);

        int skiTimeResultInSeconds = (minutes * 60) + seconds;

        List<String> shootingResults = new ArrayList<>();
        shootingResults.add(firstShootingResult);
        shootingResults.add(secondShootingResult);
        shootingResults.add(thirdShootingResult);

        int penaltySeconds = 0;

        for (String result : shootingResults) {
          for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == 'x') {
              continue;
            } else if (result.charAt(i) == 'o') {
              penaltySeconds += 10;
            }
          }
        }

        skiTimeResultInSeconds += penaltySeconds;
        athlete.setPenaltySeconds(penaltySeconds);

        int finalMinutes = skiTimeResultInSeconds / 60;
        int finalSeconds = skiTimeResultInSeconds % 60;

        skiTimeResultParts[0] = String.valueOf(finalMinutes);
        skiTimeResultParts[1] = String.valueOf(finalSeconds);

        String finalSkiTimeResult = skiTimeResultParts[0] + ":" + skiTimeResultParts[1];

        athlete.setTotalTimeInSeconds(skiTimeResultInSeconds);
        athletes.add(athlete);
      }

      Collections.sort(athletes);

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
        System.out.println(athlete);
        writer.write(athlete + "\n");
      }

      writer.close();
      reader.close();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
