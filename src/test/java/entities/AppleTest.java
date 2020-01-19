package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import entities.snake.SnakeBody;
import models.Score;
import org.junit.jupiter.api.Test;
import world.GameMap;

class AppleTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new Apple();
    }

    @Test
    void appleActionTest() {
        GameMap fakeMap = mock(GameMap.class);
        SnakeBody snakeBody = mock(SnakeBody.class);
        Apple goldenApple = new Apple();
        Score score = new Score();
        int initial = 30;
        score.setValue(initial);
        when(fakeMap.getScore()).thenReturn(score);
        when(fakeMap.getSnake()).thenReturn(snakeBody);

        goldenApple.action(fakeMap);
        assertEquals(score.getValue(), initial + Apple.DEFAULT_SCORE);
        verify(fakeMap.getSnake()).growSnake();
    }

}