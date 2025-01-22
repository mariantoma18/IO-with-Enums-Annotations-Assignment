package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class FileManagerTest {

  @Test
  void readAthleteFromFile() throws IOException {
    String testLine = "12,Thomas Walter,USA,30:30,xxxox,xoxxo,xxxxo";

    Path path = Files.createTempFile("athletes", ".csv");

    Files.write(path, testLine.getBytes());

    List<Athlete> athletes = FileManager.readAthletesFromFile(path);

    assertEquals(1, athletes.size());
  }

  @Test
  void readAthletesFromFile() throws IOException {
    String testLine =
        "11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo\n"
            + "1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo";

    Path path = Files.createTempFile("athletes", ".csv");

    Files.write(path, testLine.getBytes());

    List<Athlete> athletes = FileManager.readAthletesFromFile(path);

    assertEquals(2, athletes.size());
  }

  @Test
  void buildAthleteFromFile() {
    String line = "11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo";

    Athlete athlete = FileManager.buildAthleteFromFile(line);

    assertEquals("11", athlete.getAthleteNumber());
    assertEquals("Umar Jorgson", athlete.getAthleteName());
    assertEquals("SK", athlete.getCountryCode());
    assertEquals("30:27", athlete.getSkiTimeResult());
    assertEquals("xxxox", athlete.getFirstShootingResult());
    assertEquals("xxxxx", athlete.getSecondShootingResult());
    assertEquals("xxoxo", athlete.getThirdShootingResult());
  }

  @Test
  void convertSkiTimeInSeconds() {
    String skiTime = "25:50";

    int result = FileManager.convertSkiTimeInSeconds(skiTime);

    assertEquals(1550, result);
  }

  @Test
  void calculateTimePenalties() {
    String firstAttempt = "xxxxx";
    String secondAttempt = "oxxxx";
    String thirdAttempt = "xxxxo";

    List<String> shootingRangeResults = new ArrayList<>();
    shootingRangeResults.add(firstAttempt);
    shootingRangeResults.add(secondAttempt);
    shootingRangeResults.add(thirdAttempt);

    int result = FileManager.calculateTimePenalties(shootingRangeResults);

    assertEquals(20, result);
  }

  @Test
  void writeFinalResultsToFile() throws IOException {
    String contentAthlete1 = "11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo";
    String contentAthlete2 = "1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo";
    String contentAthlete3 = "27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx";

    String expectedResult =
        "Winner - Piotr Smitzer 30:10(30:10 + 0)\n"
            + "Runner-up - Jimmy Smiles 30:15(29:15 + 60)\n"
            + "Third Place - Umar Jorgson 30:57(30:27 + 30)";

    Athlete athlete1 = FileManager.buildAthleteFromFile(contentAthlete1);
    Athlete athlete2 = FileManager.buildAthleteFromFile(contentAthlete2);
    Athlete athlete3 = FileManager.buildAthleteFromFile(contentAthlete3);

    List<Athlete> athletes = new ArrayList<>();
    athletes.add(athlete1);
    athletes.add(athlete2);
    athletes.add(athlete3);

    Collections.sort(athletes);

    Path path = Files.createTempFile("outputFile", ".txt");

    FileManager.writeFinalResultsToFile(athletes, path);

    String readedContent = String.join("\n", Files.readAllLines(path));
    assertEquals(expectedResult, readedContent);
  }

  @Test
  void addThePositionDescription() {
    String contentAthlete1 = "11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo";
    String contentAthlete2 = "1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo";
    String contentAthlete3 = "27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx";

    Athlete athlete1 = FileManager.buildAthleteFromFile(contentAthlete1);
    Athlete athlete2 = FileManager.buildAthleteFromFile(contentAthlete2);
    Athlete athlete3 = FileManager.buildAthleteFromFile(contentAthlete3);

    List<Athlete> athletes = new ArrayList<>();
    athletes.add(athlete1);
    athletes.add(athlete2);
    athletes.add(athlete3);

    Collections.sort(athletes);

    FileManager.addThePositionDescription(athletes);

    assertEquals("Winner - ", athlete3.getPosition().getDisplayPosition());
  }
}
