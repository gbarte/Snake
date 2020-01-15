package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import entities.snake.SnakeBody;
import models.DoubleScore;
import models.Score;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import states.PlayState;


public class DoubleScorePowerUpTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new DoubleScorePowerUp();
    }

    @Test
    void mushroomActionTest() {
        PlayState playState = Mockito.mock(PlayState.class);
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        Mockito.when(playState.getShapeRenderer()).thenReturn(shapeRenderer);

        Score score = new DoubleScore();
        int initialScore = 30;
        score.setValue(initialScore);
        Mockito.when(playState.getScore()).thenReturn(score);

        DoubleScorePowerUp powerUp = new DoubleScorePowerUp();
        powerUp.action(playState);

        SnakeBody snake = Mockito.mock(SnakeBody.class);
        Mockito.when(playState.getSnake()).thenReturn(snake);
        Apple apple = new Apple();
        apple.action(playState);

        Mockito.verify(shapeRenderer).setColor(Color.RED);
        assertEquals(initialScore + Apple.DEFAULT_SCORE * 2, score.getValue());
    }

}
