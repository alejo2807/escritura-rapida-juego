package com.example.escriturarapidajuego.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class GameTimer {
    private Timeline timeline;
    private int timeLeft;
    private Label timeLabel;

    public GameTimer(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    // Calcula el tiempo según el nivel
    private int calculateTimeForLevel(int level) {
        int reduction = (level / 5) * 2;   // cada 5 niveles resta 2s
        int newTime = 20 - reduction;
        return Math.max(newTime, 2);       // nunca menos de 2
    }

    // Inicia o reinicia el temporizador para un nivel
    public void startTimer(int level) {
        if (timeline != null) {
            timeline.stop(); // Detener cualquier temporizador previo
        }

        timeLeft = calculateTimeForLevel(level); // tiempo inicial según nivel
        timeLabel.setText("Tiempo: " + timeLeft);

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    timeLeft--;
                    timeLabel.setText("Tiempo: " + timeLeft);

                    if (timeLeft <= 0) {
                        timeline.stop();
                        timeLabel.setText("¡Se acabó el tiempo!");
                        // Aquí podrías avisar al controlador del juego que se acabó el tiempo
                    }
                })
        );

        timeline.setCycleCount(timeLeft); // Repetir tantas veces como segundos iniciales
        timeline.play();
    }

    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }
    public boolean isTimeOver() {
        return timeLeft <= 0;
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}

