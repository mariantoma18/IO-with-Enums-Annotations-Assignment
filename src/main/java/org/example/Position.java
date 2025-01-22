package org.example;

/*
An enum where are stored the positions specific descriptions
 */
public enum Position {
  WINNER("Winner - "),
  RUNNER_UP("Runner-up - "),
  THIRD_PLACE("Third Place - ");

  private final String displayPosition;

  Position(String displayPosition) {
    this.displayPosition = displayPosition;
  }

  public String getDisplayPosition() {
    return displayPosition;
  }
}
