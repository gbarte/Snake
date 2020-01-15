package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;


import models.Score;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import entities.snake.SnakeBody;
import states.PlayState;


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
        SnakeBody snake = Mockito.mock(SnakeBody.class);
        Mockito.when(playState.getSnake()).thenReturn(snake);

        Apple apple = new Apple();
        apple.action(playState);

        assertEquals(initialScore + Apple.DEFAULT_SCORE, score.getValue());
        Mockito.verify(snake).growSnake();
    }

}