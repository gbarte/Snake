package entities;

import org.junit.jupiter.api.Test;


public class MushroomTest extends FoodTest {

    @Override
    protected Food getFood() {
        return new MushroomPowerUp();
    }

    @Test
    void mushroomActionTest() {
        /*
        PlayState playState = Mockito.mock(PlayState.class);
        MushroomPowerUp mushroom = new MushroomPowerUp();
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        Mockito.when(playState.getShapeRenderer()).thenReturn(shapeRenderer);

        mushroom.action(playState);

        Mockito.verify(shapeRenderer).setColor(Color.PURPLE);
        Mockito.verify(playState).setMoveTime(PlayState.DEFAULT_MOVE_TIME / 2);
         */
    }

}
