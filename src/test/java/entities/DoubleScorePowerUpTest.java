package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import models.DoubleScore;
import models.Score;
import org.junit.jupiter.api.Test;
import world.GameMap;


public class DoubleScorePowerUpTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new DoubleScorePowerUp();
    }

    @Test
    void actionTest() {

        GameMap fakeMap = mock(GameMap.class, CALLS_REAL_METHODS);

        DoubleScorePowerUp powerUp = new DoubleScorePowerUp();

        Score score = new Score();
        int initial = 30;
        score.setValue(initial);

        DoubleScore ds = new DoubleScore();
        ds.setValue(initial);

        when(fakeMap.getScore()).thenReturn(score);

        powerUp.action(fakeMap);

        when(fakeMap.getScore()).thenReturn(ds);

        verify(fakeMap).setScore(any(DoubleScore.class));
        assertEquals(fakeMap.getScore().getClass(), ds.getClass());
        assertEquals(fakeMap.getScore().getValue(), initial);
    }

}
