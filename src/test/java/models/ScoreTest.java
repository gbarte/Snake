package models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreTest {
    transient Score score;

    @BeforeEach
    void setUp() {
        score = new Score();
    }

    @Test
    void getScoreTest() {
        assertEquals(0, score.getValue());
    }

    @Test
    void setScoreTest() {
        score.setValue(50);
        assertEquals(50, score.getValue());
    }

    @Test
    void addTest() {
        score.add(50);
        assertEquals(50, score.getValue());
    }

    @Test
    void subtractTest() {
        score.setValue(80);
        score.subtract(20);
        assertEquals(60, score.getValue());
    }

    @Test
    void subtractTest2() {
        score.setValue(80);
        score.subtract(100);
        assertEquals(80, score.getValue());
    }

    @Test
    void resetScoreTest() {
        score.setValue(80);
        score.subtract(20);
        score.resetScore();
        assertEquals(0, score.getValue());
    }
}