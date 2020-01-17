package entities;

import org.junit.jupiter.api.Test;


public class GoldenAppleTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new GoldenApple();
    }

    @Test
    void appleActionTest() { // TODO test this!!!
        /*
        PlayState playState = Mockito.mock(PlayState.class);

        Score score = new Score();
        int initialScore = 20;
        score.setValue(initialScore);

        Mockito.when(playState.getScore()).thenReturn(score);
        SnakeBody snake = Mockito.mock(SnakeBody.class);
        Mockito.when(playState.getSnake()).thenReturn(snake);

        GoldenApple apple = new GoldenApple();
        apple.action(playState);

        assertEquals(initialScore + GoldenApple.DEFAULT_SCORE, score.getValue());
        Mockito.verify(snake).growSnake(2);
         */
    }

}
