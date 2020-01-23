package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import entities.snake.SnakeBody;
import models.Score;
import org.junit.jupiter.api.Test;
import utils.Sizes;
import world.GameMap;


public class GoldenAppleTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new GoldenApple();
    }

    @Test
    void appleActionTest() {
        GameMap fakeMap = mock(GameMap.class);
        SnakeBody snakeBody = mock(SnakeBody.class);
        GoldenApple goldenApple = new GoldenApple();
        Score score = new Score();
        int initial = 30;
        score.setValue(initial);
        when(fakeMap.getScore()).thenReturn(score);
        when(fakeMap.getSnake()).thenReturn(snakeBody);

        goldenApple.action(fakeMap);
        assertEquals(score.getValue(), initial + GoldenApple.DEFAULT_SCORE);
        verify(fakeMap.getSnake()).growSnake(2);
    }

}
