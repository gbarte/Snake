package entities;

import org.junit.jupiter.api.Test;


public class DoubleScorePowerUpTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new DoubleScorePowerUp();
    }

    @Test
    void mushroomActionTest() { //TODO test this!!!
        /*
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

         */
    }

}
