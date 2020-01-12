package objects.base;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import states.PlayState;


public class MushroomTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new MushroomPowerUp();
    }

    @Test
    void mushroomActionTest() {
        PlayState playState = Mockito.mock(PlayState.class);
        MushroomPowerUp mushroom = new MushroomPowerUp();
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        Mockito.when(playState.getShapeRenderer()).thenReturn(shapeRenderer);

        mushroom.action(playState);

        Mockito.verify(shapeRenderer).setColor(Color.PURPLE);
        Mockito.verify(playState).setSpeed(PlayState.DEFAULT_SPEED / 2);
    }

}
