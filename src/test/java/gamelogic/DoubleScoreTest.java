package gamelogic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class DoubleScoreTest {

    @Test
    void addTest() {
        DoubleScore doubleScore = new DoubleScore();
        doubleScore.setValue(10);
        doubleScore.add(20);

        assertEquals(50, doubleScore.getValue());
    }

}