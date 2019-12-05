package gamelogic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {
    transient ScoreCalculator scoreCalculator;

    @BeforeEach
    void setUp() {
        scoreCalculator = new ScoreCalculator();
    }

    @Test
    void getScoreTest() {
        assertEquals(0, scoreCalculator.getScore());
    }

    @Test
    void setScoreTest() {
        scoreCalculator.setScore(50);
        assertEquals(50, scoreCalculator.getScore());
    }

    @Test
    void addTest() {
        scoreCalculator.add(50);
        assertEquals(50, scoreCalculator.getScore());
    }

    @Test
    void subtractTest() {
        scoreCalculator.setScore(80);
        scoreCalculator.subtract(20);
        assertEquals(60, scoreCalculator.getScore());
    }

    @Test
    void subtractTest2() {
        scoreCalculator.setScore(80);
        scoreCalculator.subtract(100);
        assertEquals(80, scoreCalculator.getScore());
    }

    @Test
    void resetScoreTest() {
        scoreCalculator.setScore(80);
        scoreCalculator.subtract(20);
        scoreCalculator.resetScore();
        assertEquals(0, scoreCalculator.getScore());
    }
}