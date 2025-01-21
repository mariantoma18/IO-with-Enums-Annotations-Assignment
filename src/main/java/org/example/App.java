package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
  public static void main(String[] args) {
    Path inputFile = Paths.get("Biathlon-results.csv");
    Path outputFile = Paths.get("Biathlon-final-results.txt");

    try {

      List<Athlete> athletes = FileManager.readAthletesFromFile(inputFile);

      FileManager.writeFinalResultsToFile(athletes,outputFile);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
