package com.example.escriturarapidajuego.model;

import com.example.escriturarapidajuego.controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Manages the game timer for each level with decreasing time based on difficulty.
 * Handles timer initialization, countdown, and expiration notifications.
 * Uses JavaFX Timeline for accurate time management.
 *
 * @author David
 * @version 1.0
 */
public class GameTimer {
    private Timeline timeline;
    private int timeLeft;
    private Label timeLabel;
    private GameController gameController;

    /**
     * Constructs a GameTimer with a time display label.
     *
     * @param timeLabel the label to display remaining time
     */
    public GameTimer(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    /**
     * Constructs a GameTimer with a time display label and game controller reference.
     *
     * @param timeLabel the label to display remaining time
     * @param gameController the game controller to notify when time expires
     */
    public GameTimer(Label timeLabel, GameController gameController) {
        this.timeLabel = timeLabel;
        this.gameController = gameController;
    }

    /**
     * Calculates the time allowance for a specific level.
     * Time decreases as levels increase (2 seconds less every 5 levels).
     * Minimum time is 2 seconds.
     *
     * @param level the current game level
     * @return the time in seconds for the level
     */
    private int calculateTimeForLevel(int level) {
        int reduction = (level / 5) * 2;   // Reduce 2 seconds every 5 levels
        int newTime = 20 - reduction;
        return Math.max(newTime, 2);       // Never less than 2 seconds
    }

    /**
     * Starts or restarts the timer for a specific level.
     * Initializes the countdown and updates the time display.
     *
     * @param level the current game level
     */
    public void startTimer(int level) {
        // Stop any previous timer
        if (timeline != null) {
            timeline.stop();
        }

        timeLeft = calculateTimeForLevel(level); // Calculate time for current level
        timeLabel.setText("Tiempo: " + timeLeft);

        // Create timeline for countdown
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    timeLeft--;
                    timeLabel.setText("Tiempo: " + timeLeft);

                    // Check if time has expired
                    if (timeLeft <= 0) {
                        timeline.stop();
                        timeLabel.setText("Tiempo!");

                        // Notify game controller if available
                        if (gameController != null) {
                            gameController.timeIsUp();
                        }
                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Stops the timer immediately.
     */
    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Checks if time has expired for the current level.
     *
     * @return true if time has expired, false otherwise
     */
    public boolean isTimeOver() {
        return timeLeft <= 0;
    }

    /**
     * Gets the remaining time for the current level.
     *
     * @return the remaining time in seconds
     */
    public int getTimeLeft() {
        return timeLeft;
    }
}