package entities;

import models.Score;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snake.SnakeBody_BASE_39152;
import states.PlayState;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AppleTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new Apple();
    }

    @Test
    void appleActionTest() {
        PlayState playState = Mockito.mock(PlayState.class);

        Score score = new Score();
        int initialScore = 20;
        score.setValue(initialScore);

        Mockito.when(playState.getScore()).thenReturn(score);
        SnakeBody_BASE_39152 snake = Mockito.mock(SnakeBody_BASE_39152.class);
        Mockito.when(playState.getSnake()).thenReturn(snake);

        Apple apple = new Apple();
        apple.action(playState);

        assertEquals(initialScore + Apple.DEFAULT_SCORE, score.getValue());
        Mockito.verify(snake).growSnake();
    }

}