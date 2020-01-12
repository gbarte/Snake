package objects.base;

import gamelogic.Score;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snake.SnakeBody;
import states.PlayState;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoldenAppleTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new GoldenApple();
    }

    @Test
    void appleActionTest() {
        GoldenApple apple = new GoldenApple();
        PlayState playState = Mockito.mock(PlayState.class);

        Score score = new Score();
        int initialScore = 20;
        score.setValue(initialScore);

        Mockito.when(playState.getScore()).thenReturn(score);
        SnakeBody snake = Mockito.mock(SnakeBody.class);
        Mockito.when(playState.getSnake()).thenReturn(snake);

        apple.action(playState);

        assertEquals(initialScore + GoldenApple.DEFAULT_SCORE, score.getValue());
        Mockito.verify(snake).growSnake(2);
    }

}
