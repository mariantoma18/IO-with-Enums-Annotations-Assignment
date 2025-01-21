package org.example;

public enum Position {
        WINNER("Winner - "), RUNNER_UP("Runner-up - "), THIRD_PLACE("Third Place - ");

        private final String displayPosition;

    Position(String displayPosition) {
        this.displayPosition = displayPosition;
    }

    public String getDisplayPosition() {
        return displayPosition;
    }
}


