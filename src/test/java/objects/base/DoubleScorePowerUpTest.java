package objects.base;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import gamelogic.DoubleScore;
import gamelogic.Score;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snake.SnakeBody;
import states.PlayState;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleScorePowerUpTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new MushroomPowerUp();
    }

    @Test
    void mushroomActionTest() {
        PlayState playState = Mockito.mock(PlayState.class);
        DoubleScorePowerUp powerUp = new DoubleScorePowerUp();
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        Mockito.when(playState.getShapeRenderer()).thenReturn(shapeRenderer);

        Score score = new DoubleScore();
        int initialScore = 30;
        score.setValue(initialScore);
        Mockito.when(playState.getScore()).thenReturn(score);

        powerUp.action(playState);

        SnakeBody snake = Mockito.mock(SnakeBody.class);
        Mockito.when(playState.getSnake()).thenReturn(snake);
        Apple apple = new Apple();
        apple.action(playState);

        Mockito.verify(shapeRenderer).setColor(Color.RED);
        assertEquals(initialScore + Apple.DEFAULT_SCORE * 2, score.getValue());
    }

}
